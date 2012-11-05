#include "network.h"

#include <stdio.h>


/**
 * Initialise une connexion en sortie
 *
 * @param sock      Socket
 * @param addr      Informations de connection
 * @param port      Port d'ecoute
 *
 * @return          1 si succes
 */
int initSend(int *sock, char* addr, int port)
{
    struct sockaddr_in addr_in;
    
    // Socket pour envoyer des messages
    if((*sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        printf("Cr~ation de socket ~chou~e\n");
        return 0;
    }
    
    memset(&addr_in, 0, sizeof(addr_in));
    addr_in.sin_family = AF_INET;
    addr_in.sin_addr.s_addr = inet_addr(addr);
    addr_in.sin_port = htons(port);
    
    if(connect(*sock, (struct sockaddr *)&addr_in, sizeof(addr_in)) != 0) {
        printf("Erreur connect\n");
        return 0;
    }
    
    return 1;
}

/**
 * Initialise le socket en ecoute sur le port donne
 *
 * @param sock      Socket
 * @param addr      Informations de connection
 * @param port      Port d'ecoute
 *
 * @return          1 si succes
 */
int initListen(int *sock, struct sockaddr_in *addr, int port)
{
    int erreur = 99, addr_len;
    
    // Ouverture du socket
    if((*sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        printf("Erreur de socket\n");
        return 0;
    }
    
    memset(addr, 0, sizeof(*addr));
    addr->sin_family = AF_INET;
    addr->sin_addr.s_addr = INADDR_ANY;
    addr->sin_port = htons(port);

    if(bind(*sock, (struct sockaddr *)addr, sizeof(*addr)) == -1) {
        printf("Erreur de bind\n");
        return 0;
    }
    
    // Attend des connexions
    while(erreur != 0)
        erreur = listen(*sock, 1);
    
    addr_len = sizeof(addr);
    if((*sock = accept(*sock, (struct sockaddr *)&addr, &addr_len)) == -1) {
        printf("Accept: error\n");
        return 0;
    }
    
    return 1;
}

/**
 * Envoit le message donne
 *
 * @param sock      Socket sur lequel envoyer le message
 * @param host      Adresse de la cible
 * @param port      Port de la cible
 * @param buffer    Message
 * @param size      Taille du message
 */
int SendTo(int sock, const char *host, unsigned short int port, const char *buffer, int size)
{
   struct sockaddr_in addr;
   struct hostent *hp;
   int v;

   if ((hp = gethostbyname(host)) == NULL)
      return -1;

   memset(&addr, 0, sizeof(addr));
   addr.sin_family = AF_INET;
   memcpy(&addr.sin_addr, hp->h_addr, hp->h_length);
   addr.sin_port = htons(port);

   return sendto(sock, buffer, size, 0, (struct sockaddr *)&addr, sizeof(addr));
}