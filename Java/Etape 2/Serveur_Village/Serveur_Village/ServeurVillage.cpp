#include "ServeurVillage.h"

#include <iostream>
#include <sstream>

map<int, Action>* ServeurVillage::l_action = new map<int, Action>();
map<SOCKET, Client>* ServeurVillage::l_client = new map<SOCKET, Client>();
stack<SOCKET>* ServeurVillage::l_clientSocket = new stack<SOCKET>();

CSVParser* ServeurVillage::config = new CSVParser();
CSVParser* ServeurVillage::materiel = new CSVParser();
CSVParser* ServeurVillage::users = new CSVParser();

pthread_mutex_t* ServeurVillage::mutex_pause = new pthread_mutex_t();
pthread_mutex_t* ServeurVillage::mutex_pool = new pthread_mutex_t();
pthread_mutex_t* ServeurVillage::mutex_info = new pthread_mutex_t();
pthread_cond_t* ServeurVillage::cond_pool = new pthread_cond_t();

bool ServeurVillage::pause = false;


ServeurVillage::ServeurVillage()
{
	if(net_init() != 0) {
		cout << "Erreur d'initialisation du réseau pour le processus ..." << endl;
		return;
	}

	pause = false;

	config->load("../config.csv");
	materiel->load("../materiel.csv");
	users->load("../users.csv");
}

ServeurVillage::~ServeurVillage()
{
	net_close();
}

void ServeurVillage::start()
{
	// Lance le thread du serveur pour les client
	if(pthread_create(&idThServeurFHMP, NULL, ThServeurFHMP, NULL) != 0)
		cout << "Création du thread pour les clients échouée ..." << endl;
	else
		cout << "Thread pour les clients lancé ..." << endl;
	
	// Lance le thread du serveur d'administration
	if(pthread_create(&idThServeurFHMPA, NULL, ThServeurFHMPA, NULL) != 0)
		cout << "Création du thread d'administration échouée ..." << endl;
	else
		cout << "Thread d'administration lancé ..." << endl;
}

void ServeurVillage::wait()
{
	// Attend la fin des thread
	if(pthread_join(idThServeurFHMP, NULL))
		cout << "Attente de la fin du thread pour les clients échouée ..." << endl;
	else
		cout << "Thread pour les clients terminé ..." << endl;

	if(pthread_join(idThServeurFHMPA, NULL) != 0)
		cout << "Attente de la fin du thread d'administration échouée ..." << endl;
	else
		cout << "Thread d'administration terminé ..." << endl;
}

/**
 * Thread pour les clients
 */
void* ServeurVillage::ThServeurFHMP(void* data)
{
	pthread_t* l_thread = new pthread_t[config->getInt("POOL_SIZE")];

	SOCKET sock, csock;
	char ip[IP_SIZE];

	// Place le serveur en écoute
	if((sock = socketServer(config->getInt("PORT_VILLAGE"))) == INVALID_SOCKET) {
		cout << "FHMP: Création du socket échouée ..." << endl;
		return NULL;
	} else if(sock == SOCKET_ERROR) {
		cout << "FHMP: Bind échoué ..." << endl;
		return NULL;
	}

	cout << "FHMP: La socket " << sock << " est maintenant ouverte en mode TCP/IP" << endl;
	
	pthread_mutex_init(mutex_pool, NULL);
	pthread_mutex_init(mutex_pause, NULL);
	pthread_mutex_init(mutex_info, NULL);
	pthread_cond_init (cond_pool, NULL);
	
	// Lance les thread client
	for(int i=0; i<config->getInt("POOL_SIZE"); i++) {
		pthread_create(&l_thread[i], NULL, ThClientFHMP, NULL);
	}

	while(true) {
		// Attend un client
		if((csock = accept(sock, ip)) == SOCKET_ERROR) {
			cout << "FHMP: Listen échoué ..." << endl;
			return NULL;
		}

		cout << "FHMP: Un client se connecte avec la socket " << csock << " de " << ip << endl;
		pthread_mutex_lock(mutex_pool);
		l_clientSocket->push(csock);

		// Enregistre l'ip
		(*l_client)[csock].ip = ip;

		pthread_cond_signal(cond_pool);
		pthread_mutex_unlock(mutex_pool);
	}

	// Fermeture de la socket client et de la socket serveur
    cout << "FHMP: Fermeture de la socket serveur" << endl;
    closesocket(sock);
	
	pthread_mutex_destroy(mutex_pause);
	pthread_mutex_destroy(mutex_pool);
	pthread_mutex_destroy(mutex_info);
	pthread_cond_destroy(cond_pool);

	return NULL;
}

/** Gestion d'un client Applic_Materiel */
void* ServeurVillage::ThClientFHMP(void* data)
{
	string buffer;
	SOCKET csock;

	while(true) {
		pthread_mutex_lock(mutex_pool);
		while(l_clientSocket->empty())
			pthread_cond_wait(cond_pool, mutex_pool);
		
		csock = l_clientSocket->top();
		l_clientSocket->pop();
		pthread_mutex_unlock(mutex_pool);

		SOCKET urgence;
			
		// Création du socket d'urgence
		string ip = (*l_client)[csock].ip;
		if((urgence = socketClient(ip.c_str(), config->getInt("PORT_URGENCE"))) == INVALID_SOCKET) {
			cout << "Connection urgence: Création du socket échouée ..." << endl;
		} else if(urgence == SOCKET_ERROR) {
			cout << "Connection urgence: Bind échoué ..." << endl;
		} else if(urgence == -2) {
			cout << "Connection urgence: Addresse IP incorrecte ..." << endl;
		}

		// Enregistre l'urgence
		(*l_client)[csock].urgence = urgence;

		// Ecoute le client
		while(true) {
			if(recv(csock, &buffer) < 0) {
				cout << "Erreur lors de la récéption du message, fermeture du client ..." << endl;
				break;
			}

			cout << "Recu du client " << csock << ": " << buffer << endl;

			// Traitement du message
			processRequest(csock, buffer);
		}
		
		cout << "FHMP: Fermeture de la socket client d'urgence: " << urgence << endl;
		closesocket(urgence);
		cout << "FHMP: Fermeture de la socket client: " << csock << endl;
		closesocket(csock);
		l_client->erase(csock);
	}

	return NULL;
}

/**
 * Thread d'administration
 */
void* ServeurVillage::ThServeurFHMPA(void* data)
{
    SOCKET sock, csock;
	char ip[IP_SIZE];

	int port = config->getInt("PORT_ADMIN");
	if((sock = socketServer(port)) == INVALID_SOCKET) {
		cout << "FHMPA: Création du socket échouée ..." << endl;
		return NULL;
	} else if(sock == SOCKET_ERROR) {
		cout << "FHMPA: Bind échoué ..." << endl;
		return NULL;
	}
	
	cout << "FHMPA: La socket " << sock << 
			" est maintenant ouverte en mode TCP/IP sur le port " << port << endl;

	while(true) {
		if((csock = accept(sock, ip)) == SOCKET_ERROR) {
			cout << "FHMPA: Listen échoué ..." << endl;
			return NULL;
		}

		cout << "FHMPA: Un client se connecte avec la socket " << csock << " de " << ip << endl;
	    
		string buffer;
		bool logged = false;
		while(true) {
			if(recv(csock, &buffer) < 0) {
				cout << "Erreur lors de la récéption du message, fermeture du client ..." << endl;
				break;
			}

			cout << "Recu de l'admin " << csock << ": " << buffer << endl;

			// Traitement du message
			string commande = getCommande(buffer);
			if(commande.compare("LOGINA") == 0) {
				string password = getArg(buffer, 0);
				if(password.compare(users->get("admin")) == 0) {
					logged = true;
					cout << "Login reussit de l'admin" << endl;
					send(csock, "LOGIN_SUCCESS");
				} else {
					logged = false;
					cout << "Login échoué de l'admin" << endl;
					send(csock, "LOGIN_FAILED");
					break;
				}
			}
			else if(logged) {
				if(commande.compare("LCLIENTS") == 0) {
					string reply = "";

					vector<string> l_login = getConnectedLogin();
					vector<string>::iterator it;
					for(it=l_login.begin(); it!=l_login.end();it++)
						reply += *it + ":";

					send(csock, reply);
				}
				else if(commande.compare("STOP") == 0) {
					sendUrgence("CLOSE:10");
					send(csock, "STOP_OK");
				}
				else if(commande.compare("PAUSE") == 0) {
					sendUrgence("PAUSE");
					send(csock, "PAUSE_OK");
					setPause(true);
				}
				else if(commande.compare("UNPAUSE") == 0) {
					sendUrgence("UNPAUSE");
					send(csock, "UNPAUSE_OK");
					setPause(false);
				}
			}
		}

		// Fermeture de la socket client et de la socket serveur
		cout << "FHMPA: Fermeture de la socket client et serveur" << endl;
		closesocket(csock);
		closesocket(sock);

		Sleep(10000);
		exit(0);
	}

	return NULL;
}

/** Envois de message d'urgence */
vector<string> ServeurVillage::getConnectedLogin()
{
	vector<string> l_login;
	
	pthread_mutex_lock(mutex_info);
	map<SOCKET, Client>::iterator it;
	for(it=l_client->begin(); it!=l_client->end(); it++) {
		l_login.push_back(it->second.login); 
	}
	pthread_mutex_unlock(mutex_info);

	return l_login;
}

/** Envois de message d'urgence */
void ServeurVillage::sendUrgence(string txt)
{
	pthread_mutex_lock(mutex_info);
	map<SOCKET, Client>::iterator it;
	for(it=l_client->begin(); it!=l_client->end(); it++)
		send(it->second.urgence, txt);
	pthread_mutex_unlock(mutex_info);
}

/** Traitement des requetes recues */
void ServeurVillage::processRequest(SOCKET client, string request)
{
	pthread_mutex_lock(mutex_info);

	string commande = getCommande(request);

	// Demande la liste du matériel existant
	if(commande.compare("ASK_CATALOGUE") == 0) {
		cout << "Demande du catalogue recue de " << client << endl;

		sendCatalogue(client);
	}
	// Demande les action enregistree
	else if(commande.compare("ASK_ACTION") == 0) {
		cout << "Demande des action recue de " << client << endl;

		sendAction(client);
	}
	// Demande de login
	else if(commande.compare("LOGIN") == 0) {
		string login = getArg(request, 0);
		string password = getArg(request, 1);
		
		cout << "Login de '" << login << "' avec le password '" << password << "'" << endl;

		// Serveur en pause
		if(isEnPause()) {
			cout << "Login refusé: serveur en pause" << endl;
			send(client, "SERVER_INPAUSE");
		}
		// Verifie le password
		else if(users->get(login).compare(password) == 0) {
			(*l_client)[client].login = login;
			cout << "Login réussit de " << (*l_client)[client].login << endl;
			send(client, "LOGIN_SUCCES");
		}
		else {
			cout << "Login echoué" << endl;
			send(client, "LOGIN_FAILED");
		}
	}
	// Commande
	else if(commande.compare("LIVRER") == 0 ||
			commande.compare("REPARER") == 0 ||
			commande.compare("DECLASSER") == 0)
	{
		string action = commande;
		string article = getArg(request, 0);
		time_t date = atoi(getArg(request, 1).c_str());
		
		time_t now;
		time(&now);

		if(date <= now) {
			cout << "Date de reservation invalide ...";
			send(client, "DATE_INVALID");
		}
		else if(article == "") {
			cout << "Article invalide ...";
			send(client, "ARTICLE_NOT_FOUND");
		}
		else {
			// Ajoute l'action
			vector<int> l_key = getKeyFrom(*l_action);
			int id, lastId = (l_key.size() - 1);

			if(lastId < 0) id = 1;
			else id = l_key[lastId] + 1;
		
			Action act;
			act.action = action;
			act.article = article;
			act.date = date;
			act.user = (*l_client)[client].login;

			(*l_action)[id] = act;
			cout << "Nouvelle action de " << action << " par " << act.user << ", id: " << id << endl;

			string reply = "DEMANDE_OK:" + itoa(id);
			send(client, reply);
		}
	}
	// Demande les action enregistree
	else if(commande.compare("CMAT") == 0) {
		cout << "Demande de suppression d'action " << client << endl;
		
		// Verifie l'id
		int id = atoi(getArg(request, 0).c_str());
		if(id <= 0 || l_action->find(id) == l_action->end()) {
			cout << "Id invalide ...";
			send(client, "NOT_FOUND");
		}

		// Verifie le propriétaire
		else if((*l_action)[id].user.compare((*l_client)[client].login) != 0) {
			cout << "Demande echouee, proprietaire ne correspond pas ...";
			send(client, "NOT_YOURS");
		}
		else {
			l_action->erase(id);
			cout << "Action " << id << " supprimée" << endl;

			send(client, "SUCCESS_CMAT");
		}
	}
	// Demande de materiel
	else if(commande.compare("ASKMAT") == 0) {
		string name = getArg(request, 0);
		if(name == "") {
			cout << "ASKMAT échoué" << endl;
			send(client, "ASK_FAILED");
		}

		materiel->set(name, "1");
		cout << "Ajout de " << name << " dans le catalogue" << endl;
		send(client, "ASK_SUCCESS");
	}
	// Demande non reconnue
	else {
		cout << "Requete recue de " << client << " non reconnue: " << request << endl;
		send(client, "UNRECOGNIZED_REQUEST");
	}

	pthread_mutex_unlock(mutex_info);
}

/** Envois le catalogue au client */
void ServeurVillage::sendCatalogue(SOCKET client)
{
	vector<string> l_materiel = materiel->getKey();
	vector<string>::iterator it;
	string reply = "CATALOGUE:";

	for(it=l_materiel.begin(); it<l_materiel.end(); it++) {
		reply += *it + ":";
	}

	cout << "Envois du catalogue ..." << endl;
	send(client, reply);
}

/** Envois les action au client */
void ServeurVillage::sendAction(SOCKET client)
{
	string reply = "ACTION:";
	
	map<int, Action>::iterator it;
	for(it=l_action->begin(); it!=l_action->end(); it++) {
		string action = it->second.action;
		string article = it->second.article;
		time_t date = it->second.date;

		stringstream sdate;
		sdate << date;

		reply += itoa(it->first) + ":" + action + ":" + article + ":"; // + sdate.str();
	}

	cout << "Envois des action ..." << endl;
	send(client, reply);
}


bool ServeurVillage::isEnPause()
{
	bool temp;

	pthread_mutex_lock(mutex_pause);
	temp = pause;
	pthread_mutex_unlock(mutex_pause);

	return temp;
}

void ServeurVillage::setPause(bool value)
{
	pthread_mutex_lock(mutex_pause);
	pause = value;
	pthread_mutex_unlock(mutex_pause);
}