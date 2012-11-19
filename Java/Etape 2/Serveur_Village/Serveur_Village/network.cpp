#include "network.h"

/**
 * Initialise le contexte reseau
 */
int net_init()
{
	WSADATA WSAData;

    return WSAStartup(MAKEWORD(2,2), &WSAData);
}

/**
 * Ferme le contexte réseau
 */
void net_close()
{
	WSACleanup();
}

/**
 * Crée un socket de serveur
 */
int socketServer(int port)
{
    SOCKADDR_IN sin;
    SOCKET sock;
    socklen_t recsize = sizeof(sin);
	
	// Création d'une socket
    if((sock = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET)
		return INVALID_SOCKET;

	// Configuration
    sin.sin_addr.s_addr = htonl(INADDR_ANY);	// Adresse IP automatique
    sin.sin_family = AF_INET;					// Protocole familial (IP)
    sin.sin_port = htons(port);					// Listage du port

    if(bind(sock, (SOCKADDR*)&sin, recsize) == SOCKET_ERROR)
		return SOCKET_ERROR;

	return sock;
}

/**
 * Crée un socket client
 */
int socketClient(char* ip, int port)
{
	return 0;
}

int accept(int socket, char* ip)
{
	// Socket et contexte d'adressage du client
    SOCKADDR_IN csin;
    SOCKET csock;
    socklen_t crecsize = sizeof(csin);

    // Démarrage du listage (mode server)
    if(listen(socket, 5) == SOCKET_ERROR)
		return SOCKET_ERROR;
                
    // Attente pendant laquelle le client se connecte
    csock = accept(socket, (SOCKADDR*)&csin, &crecsize);

	strcpy(ip, inet_ntoa(csin.sin_addr));

	return csock;
}

int send(int sock, char* buff, int size)
{
	return 0;
}

int recv(int sock, char* buff)
{
	return 0;
}