#include "network.h"

/**
 * Initialise le socket en ecoute sur le port donne
 *
 * @param s         Socket
 * @param addr      Informations de connection
 * @param port      Port d'ecoute
 *
 * @return          1 si succes
 */
int initListen(int *s, struct sockaddr_in *addr, int port)
{
    // Ouverture du socket
    if((*s = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        write(1, "Erreur de socket\n", 40);
        return 0;
    }
    
    memset(addr, 0, sizeof(*addr));
    addr->sin_family = AF_INET;
    addr->sin_addr.s_addr = INADDR_ANY;
    addr->sin_port = htons(port);

    if(bind(*s, (struct sockaddr *)addr, sizeof(*addr)) == -1) {
        write(1, "Erreur de bind\n", 40);
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