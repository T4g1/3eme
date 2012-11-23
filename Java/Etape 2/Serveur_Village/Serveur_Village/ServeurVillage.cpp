#include "ServeurVillage.h"

#include <iostream>

map<string, string>* ServeurVillage::l_clients = new map<string, string>();
stack<SOCKET>* ServeurVillage::l_clientSocket = new stack<SOCKET>();

pthread_mutex_t* ServeurVillage::mutex_pool = new pthread_mutex_t();
pthread_cond_t* ServeurVillage::cond_pool = new pthread_cond_t();


ServeurVillage::ServeurVillage()
{
	if(net_init() != 0) {
		cout << "Erreur d'initialisation du réseau pour le processus ..." << endl;
		return;
	}
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
	pthread_t l_thread[POOL_SIZE];

	SOCKET sock, csock;
	char ip[IP_SIZE];

	// Place le serveur en écoute
	if((sock = socketServer(PORT_VILLAGE)) == INVALID_SOCKET) {
		cout << "FHMP: Création du socket échouée ..." << endl;
		return NULL;
	} else if(sock == SOCKET_ERROR) {
		cout << "FHMP: Bind échoué ..." << endl;
		return NULL;
	}

	cout << "FHMP: La socket " << sock << " est maintenant ouverte en mode TCP/IP" << endl;

	pthread_mutex_init(mutex_pool, NULL);
	pthread_cond_init (cond_pool, NULL);
	
	// Lance les thread client
	for(int i=0; i<POOL_SIZE; i++) {
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

		pthread_cond_signal(cond_pool);
		pthread_mutex_unlock(mutex_pool);
	}

	// Fermeture de la socket client et de la socket serveur
    cout << "FHMP: Fermeture de la socket serveur" << endl;
    closesocket(sock);

	pthread_mutex_destroy(mutex_pool);
	pthread_cond_destroy(cond_pool);

	return NULL;
}

/** Gestion d'un client Applic_Materiel */
void* ServeurVillage::ThClientFHMP(void* data)
{
	char buffer[BUFFER_SIZE];
	SOCKET csock;

	while(true) {
		pthread_mutex_lock(mutex_pool);
		while(l_clientSocket->empty())
			pthread_cond_wait(cond_pool, mutex_pool);
		
		csock = l_clientSocket->top();
		l_clientSocket->pop();
		pthread_mutex_unlock(mutex_pool);

		// Ecoute le client
		while(true) {
			if(recv(csock, buffer) < 0) {
				cout << "Erreur lors de la récéption du message, fermeture du client ..." << endl;
				break;
			}

			cout << "Recu du client " << csock << ": " << buffer << endl;
		}
		
		cout << "FHMP: Fermeture de la socket client: " << csock << endl;
		closesocket(csock);
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

	if((sock = socketServer(PORT_ADMIN)) == INVALID_SOCKET) {
		cout << "FHMPA: Création du socket échouée ..." << endl;
		return NULL;
	} else if(sock == SOCKET_ERROR) {
		cout << "FHMPA: Bind échoué ..." << endl;
		return NULL;
	}
	
	cout << "FHMPA: La socket " << sock << " est maintenant ouverte en mode TCP/IP" << endl;

    if((csock = accept(sock, ip)) == SOCKET_ERROR) {
		cout << "FHMPA: Listen échoué ..." << endl;
		return NULL;
	}

    cout << "FHMPA: Un client se connecte avec la socket " << csock << " de " << ip << endl;
	        
    // Fermeture de la socket client et de la socket serveur
    cout << "FHMPA: Fermeture de la socket client et serveur" << endl;
    closesocket(csock);
    closesocket(sock);

	return NULL;
}