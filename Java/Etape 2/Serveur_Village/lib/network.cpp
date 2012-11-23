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
 * @return		INVALID_SOCKET, SOCKET_ERROR, -2 ou le socket créé
 */
int socketClient(char* ip, int port)
{
	SOCKET sock = socket(AF_INET, SOCK_STREAM, 0);
	SOCKADDR_IN sin = { 0 };
	struct hostent *hostinfo;

	if(sock == INVALID_SOCKET)
		return INVALID_SOCKET;

	hostinfo = gethostbyname(ip);
	if (hostinfo == NULL)
		return -2;

	sin.sin_addr = *(IN_ADDR *) hostinfo->h_addr;
	sin.sin_port = htons(port);
	sin.sin_family = AF_INET;

	if(connect(sock,(SOCKADDR *) &sin, sizeof(SOCKADDR)) == SOCKET_ERROR)
		return SOCKET_ERROR;

   return sock;
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

int send(int socket, char* buffer, int size)
{
	return send(socket, buffer, strlen(buffer), 0);
}

int recv(int socket, char* buffer)
{
	int n = 0;

	if((n = recv(socket, buffer, BUFFER_SIZE - 1, 0)) < 0)
		return n;

	buffer[n] = 0;

	return n;
}