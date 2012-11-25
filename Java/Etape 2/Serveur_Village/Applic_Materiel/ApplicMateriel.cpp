#include "ApplicMateriel.h"

#include "../lib/common.h"

#include <iostream>
#include <string>
#include <sstream>


ApplicMateriel::ApplicMateriel() : config(), l_materiel()
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
			cout << "Commander quel element: ";
			cin >> input;

			// Saisie de l'article
			vector<string> l_key = getKeyFrom(l_materiel);
			int article = atoi(input.c_str());
			if(article <= 0 || (unsigned int)article - 1 >= l_key.size()) {
				cout << "Saisie invalide ..." << endl;
				pause(); continue;
			}

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

/** Action BMAT */
void ApplicMateriel::bmat(string action, int article, time_t date)
{
	stringstream sdate;
	sdate << date;
	
	// Evoit de la requete
	string buffer = action + ":" + itoa(article) + ":" + sdate.str();
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
}