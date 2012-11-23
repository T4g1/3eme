#include "ApplicMateriel.h"

#include <iostream>
#include <string>

#define PORT_VILLAGE	27016


ApplicMateriel::ApplicMateriel()
{
	// Initialisation du reseau
	if(net_init() != 0) {
		cout << "Erreur d'initialisation du réseau pour le processus ..." << endl;
		return;
	}
}

ApplicMateriel::~ApplicMateriel()
{
	// Fermeture du reseau
	net_close();
}

/** Menu du materiel */
void ApplicMateriel::start()
{
	char buffer[BUFFER_SIZE];
	strcpy(buffer, "SALUT");

	// Connexion au serveur
	if(!connect())
		return;

	// Boucle infinie
	//while(true) {
		send(socket, buffer, strlen(buffer));
	//}
}

/** Initialise la connexion au serveur */
bool ApplicMateriel::connect()
{
	char* ip = "127.0.0.1";

	// Création du socket
	if((socket = socketClient(ip, PORT_VILLAGE)) == INVALID_SOCKET) {
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