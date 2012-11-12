#include "station3.h"

#include "common.h"
#include "network.h"

#include <stdio.h>

void reinitialise();
void processPiece();
void* receiveFrom3(void* arg);

int station_3;
int station_1;
pthread_t receive_3;


int main(void)
{
    initSignal(handlerEnd);
    
    // Initialise les mutex
    pthread_mutex_init(&mutex_received, NULL);
    pthread_mutex_init(&mutex_give, NULL);
    
    // Cree un thread qui ecoutera ce que la station 1 et 3 lui dira
    if(pthread_create(&receive_3, NULL, receiveFrom3, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    // Attend que le serveur d'~coute soit lanc~
    printf("Appuyez sur une touche lorsque station 3 et station 1 seront lance ...\n");
    while(kbhit() == 0);
    
    initSend(&station_3, ADDR_STATION_3, PORT_LISTEN_3_4);
    initSend(&station_1, ADDR_STATION_1, PORT_LISTEN_1_4);
    
    initLink();
        
    // Boucle principale
    while(1) {
        reinitialise();
        processPiece();
    }
    
    return 0;
}


/**
 * Passe la station en attente de piece
 */
void reinitialise()
{
}

/**
 * Realise le parcourt avec la piece courante
 */
void processPiece()
{
}

/**
 * Recoit les messages de la station 3
 */
void* receiveFrom3(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_4_3);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 3: %s\n", buffer);
            
            // Indique que la station 3 nous a donne une piece
            if(strcmp("DONNE_PIECE", buffer)) {
                setPieceReceived(TRUE);
            }
        }
    }
}