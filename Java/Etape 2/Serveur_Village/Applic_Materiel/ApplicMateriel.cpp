#include "ApplicMateriel.h"


ApplicMateriel::ApplicMateriel() : config(), l_materiel(), l_action()
{
	// Initialisation du reseau
	if(net_init() != 0) {
		cout << "Erreur d'initialisation du réseau pour le processus ..." << endl;
		return;
	}

	config.load("../config.csv");
}

ApplicMateriel::~ApplicMateriel()
{
	// Fermeture du reseau
	net_close();
}

/** Menu du materiel */
void ApplicMateriel::start()
{
	string login, password;

	// Lance le thread d'urgence
	int port_urgence = config.getInt("PORT_URGENCE");
	if(pthread_create(&idThUrgence, NULL, urgence, &port_urgence) != 0)
		cout << "Création du thread pour les urgences echouée ..." << endl;
	else
		cout << "Thread pour les urgences lancé ..." << endl;

	// Connexion au serveur
	if(!connect())
		return;

	// Connection du client
	do {
		cout << "login: ";
		cin >> login;

		cout << "password: ";
		cin >> password;
	} while(!doLogin(login, password));

	// Boucle infinie
	string input;
	bool isRunning = true;
	while(isRunning) {
		// Menu
		cout << endl << "ACTION:" << endl;
		cout << "1) LIVRER" << endl;
		cout << "2) REPARER" << endl;
		cout << "3) DECLASSER" << endl;
		cout << "4) VOIR MES ACTIONS" << endl;
		cout << "5) AJOUTER" << endl;
		cout << "6) QUITTER LE PROGRAMME" << endl;
		cout << "Votre choix: ";
		cin >> input;

		// Gestion du choix
		if(input.compare("4") == 0) {
			showAction();
			if(l_action.size() == 0) {
				cout << "Rien a annuler ..." << endl;
				pause(); continue;
			}

			cout << "Annuler quelle action: ";
			cin >> input;

			// Saisie de l'action
			int idAction = atoi(input.c_str());
			if(idAction <= 0 || l_action.find(idAction) == l_action.end()) {
				cout << "Saisie invalide ..." << endl;
				pause(); continue;
			}

			cmat(idAction);
		} else if(input.compare("5") == 0) {
			string name, description, marque, prix, accessoire;

			cout << "Ajouter quel element: ";
			cin >> name;

			cout << "Description: ";
			cin >> description;

			cout << "Marque: ";
			cin >> marque;

			cout << "Prix: ";
			cin >> prix;

			cout << "Accessoires: ";
			cin >> accessoire;

			askmat(name, description, marque, prix, accessoire);
		} else if(input.compare("6") == 0) {
			isRunning = false;
		} else {
			string action = "";
			if(input.compare("1") == 0) action = "LIVRER";
			else if(input.compare("2") == 0) action = "REPARER";
			else if(input.compare("3") == 0) action = "DECLASSER";
			else continue;

			showCatalogue();
			if(l_materiel.size() == 0) {
				cout << "Rien a commander ..." << endl;
				pause(); continue;
			}

			cout << "Commander quel element: ";
			cin >> input;

			// Saisie de l'article
			vector<string> l_key = getKeyFrom(l_materiel);
			int idArticle = atoi(input.c_str());
			if(idArticle <= 0 || (unsigned int)idArticle - 1 >= l_key.size()) {
				cout << "Saisie invalide ..." << endl;
				pause(); continue;
			}

			string article = l_key[idArticle];

			// Saisie de la date
			time_t date = askDate();
			if(date == DATE_INVALIDE) {
				cout << "Saisie de date invalide ..." << endl;
				pause(); continue;
			}
			else if(date == DATE_ANTERIEURE) {
				cout << "Vous devez saisir une date dans le futur ..." << endl;
				pause(); continue;
			}

			bmat(action, article, date);
		}
	}
}

/** Initialise la connexion au serveur */
bool ApplicMateriel::connect()
{
	char* ip = "127.0.0.1";

	// Création du socket
	if((socket = socketClient(ip, config.getInt("PORT_VILLAGE"))) == INVALID_SOCKET) {
		cout << "Création du socket échouée ..." << endl;
		return false;
	} else if(socket == SOCKET_ERROR) {
		cout << "Bind échoué ..." << endl;
		return false;
	} else if(socket == -2) {
		cout << "Addresse IP incorrecte ..." << endl;
		return false;
	}

	return true;
}

/** Login du client */
bool ApplicMateriel::doLogin(string login, string password)
{
	string reply;

	send(socket, "LOGIN:" + login + ":" + password);
	recv(socket, &reply);

	if(reply.compare("LOGIN_SUCCES") == 0)
		return true;
	else if(reply.compare("SERVER_INPAUSE") == 0)
		cout << "Serveur en pause" << endl;
	else
		cout << "Login ou password incorrect" << endl;

	return false;
}

/** Affiche le catalogue de materiel */
void ApplicMateriel::showCatalogue()
{
	string buffer = "ASK_CATALOGUE";

	send(socket, buffer);
	recv(socket, &buffer);

	string commande = getCommande(buffer);
	if(commande.compare("CATALOGUE") != 0) return;

	// Parcourt la réponse
	int i = 0;
	l_materiel.clear();
	while(true) {
		string key = getArg(buffer, i++);
		if(key == "") break;

		l_materiel[key] = 1;
	}

	cout << endl << endl;
	cout << "MATERIEL DISPONNIBLE:" << endl;
	// Affiche le catalogue
	i = 1;
	map<string, int>::iterator it;
	for(it=l_materiel.begin(); it!=l_materiel.end(); it++) {
		cout << i++ << ") " << it->first << endl;
	}
}

/** Affiche les action */
void ApplicMateriel::showAction()
{
	string buffer = "ASK_ACTION";

	send(socket, buffer);
	recv(socket, &buffer);

	string commande = getCommande(buffer);
	if(commande.compare("ACTION") != 0) return;

	// Parcourt la réponse
	int i = 0;
	l_action.clear();
	while(true) {
		int id = atoi(getArg(buffer, i).c_str());
		if(id <= 0) break;
		
		string action = getArg(buffer, i+1);
		string article = getArg(buffer, i+2);
		
		Action act;
		act.action = action;
		act.article = article;
		//act.date = date;
		act.user = "";

		l_action[id] = act;

		i += 3;
	}

	cout << endl << endl;
	cout << "ACTION ENREGISTREE:" << endl;
	// Affiche les actions
	map<int, Action>::iterator it;
	for(it=l_action.begin(); it!=l_action.end(); it++) {
		cout << it->first << ") " << it->second.action << " " << it->second.article << endl;
	}
}

/** Action BMAT */
void ApplicMateriel::bmat(string action, string article, time_t date)
{
	stringstream sdate;
	sdate << date;
	
	// Evoit de la requete
	string buffer = action + ":" + article + ":" + sdate.str();
	send(socket, buffer);
	recv(socket, &buffer);
	cout << "Recu du serveur: " << buffer << endl;

	string commande = getCommande(buffer);
	if(commande.compare("DATE_INVALID") == 0) 
		cout << "Date saisie non reconnue par le serveur ..." << endl;
	else if(commande.compare("ARTICLE_NOT_FOUND") == 0) 
		cout << "Article specifié inexistant ..." << endl;
	else if(commande.compare("DEMANDE_OK") == 0) {
		string id = getArg(buffer, 0);
		if(id == "")
			cout << "Erreur de commande, pas d'id recu ..." << endl;
		else
			cout << "Demande enregistrée, id: " << id << endl;
	}
	else
		cout << "Reponse innatendue recue, demande echouée ..." << endl;

	pause();
}

/** Action CMAT */
void ApplicMateriel::cmat(int id)
{
	string buffer = "CMAT:" + itoa(id);
	send(socket, buffer);
	recv(socket, &buffer);

	string commande = getCommande(buffer);
	if(commande.compare("NOT_YOURS") == 0)
		cout << "Impossible de supprimer l'action, elle ne nous appartient pas ..." << endl;
	else if(commande.compare("NOT_FOUND") == 0)
		cout << "Impossible de supprimer l'action, elle n'existe pas ..." << endl;
	else if(commande.compare("SUCCESS_CMAT") == 0)
		cout << "Action supprimée" << endl;
	else 
		cout << "Reponse innatendue recue, demande echouée ..." << endl;

	pause();
}

/** Action ASKMAT */
void ApplicMateriel::askmat(string name, string description, string marque, string prix, string accessoire)
{
	string buffer = "ASKMAT:" + name + ":" + description + ":" + marque + ":" + prix + ":" + accessoire;
	send(socket, buffer);
	recv(socket, &buffer);

	string commande = getCommande(buffer);
	if(commande.compare("ASK_FAILED") == 0) 
		cout << "Impossible de demander ce materiel ..." << endl;
	else if (commande.compare("ASK_SUCCESS") == 0) 
		cout << "Demande reussie" << endl;
	else
		cout << "Reponse innatendue recue, demande echouée ..." << endl;

	pause();
}

/** Serveur d'ecoute pour des urgences */
void* urgence(void* param)
{
	int port = *(int*)param;
	SOCKET sock, csock;
	char ip[IP_SIZE];
	string buffer;

	if((sock = socketServer(port)) == INVALID_SOCKET) {
		cout << "URGENCE: Socket d'urgence non créé ..." << endl;
		return NULL;
	} else if(sock == SOCKET_ERROR) {
		cout << "URGENCE: Bind d'urgence échoué ..." << endl;
		return NULL;
	}
	
	cout << "URGENCE: Le socket d'urgence " << sock << " est maintenant ouverte en mode TCP/IP" << endl;

    if((csock = accept(sock, ip)) == SOCKET_ERROR) {
		cout << "URGENCE: Listen sur le socket d'urgence échoué ..." << endl;
		return NULL;
	}
	
	int time;
    cout << "URGENCE: Le serveur se connecte sur le port d'urgence " << csock << " de " << ip << endl;
	
	while(true) {
		recv(csock, &buffer);

		string commande = getCommande(buffer);
		if(commande.compare("PAUSE") == 0)
			cout << "URGENCE: Serveur en pause ..." << endl;
		else if(commande.compare("UNPAUSE") == 0)
			cout << "URGENCE: Le serveur sort de pause ..." << endl;
		else if(commande.compare("CLOSE") == 0) {
			time = atoi(getArg(buffer, 0).c_str());

			cout << "URGENCE: Le serveur se ferme dans " << time << "secondes ..." << endl;
			break;
		}
	}

    // Fermeture de la socket client et de la socket serveur
    cout << "URGENCE: Fermeture des socket client et serveur" << endl;
    closesocket(csock);
    closesocket(sock);

	while(time--) {
		Sleep(1);
		cout << "URGENCE: Fermeture du serveur dans " << time << "seconde(s)" << endl;
	}

	cout << "Serveur eteint, veuillez quitter le programme ...";

	return NULL;
}