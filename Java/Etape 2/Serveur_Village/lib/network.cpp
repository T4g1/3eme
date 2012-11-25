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
int socketClient(const char* ip, int port)
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

	sprintf_s(ip, IP_SIZE, inet_ntoa(csin.sin_addr));

	return csock;
}

/** Envoi une chaine de caractere vers le serveur */
int send(int socket, string buffer)
{
	return send(socket, buffer.c_str(), buffer.length(), 0);
}

/** Recoit une chaine de caractere depuis le serveur */
int recv(int socket, string* buffer)
{
	char cbuffer[BUFFER_SIZE];
	int n = 0;

	if((n = recv(socket, cbuffer, BUFFER_SIZE, 0)) < 0)
		return n;

	cbuffer[n] = 0;

	*buffer = cbuffer;
	return n;
}