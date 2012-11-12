#include "station2.h"

#include "common.h"
#include "network.h"

#include <stdio.h>

void reinitialise();
void processPiece();
void* receiveFrom1(void* arg);
void* receiveFrom3(void* arg);

int station_1;
int station_3;
pthread_t receive_1, receive_3;


int main(void)
{
    initSignal();
    
    // Initialise les mutex
    pthread_mutex_init(&mutex_received, NULL);
    pthread_mutex_init(&mutex_give, NULL);
    
    // Cree un thread qui ecoutera ce que la station 1 et 3 lui dira
    if(pthread_create(&receive_1, NULL, receiveFrom1, NULL) != 0 ||
       pthread_create(&receive_3, NULL, receiveFrom3, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    // Attend que le serveur d'~coute soit lanc~
    printf("Appuyez sur une touche lorsque station 1 et station 3 seront lance ...\n");
    while(kbhit() == 0);
    
    //initSend(&station_1, ADDR_STATION_1, PORT_LISTEN_1_2);
    initSend(&station_3, ADDR_STATION_3, PORT_LISTEN_3_2);
    
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
    // Coussin d'air eteint ?
    printf("Eteint le coussin d'air ...\n");
    setActuateur(COUSSIN_AIR, OFF);
    
    // Position du pousseur de piece
    printf("Rentre le pousseur de piece ...\n");
    setActuateur(PP, OFF);
    waitBit(PP_RENTRE, TRUE);
    
    // Position de l'ascenseur
    printf("Descend l'ascensseur ...\n");
    setActuateur(ASC_MONTE, OFF);
    setActuateur(ASC_DESCEND, ON);
    waitBit(ASC_BAS, TRUE);
    setActuateur(ASC_DESCEND, OFF);
    
    // Previens la station 1 qu'on attend une piece
    printf("Previens la station 1 que l'on attend une piece ...\n");
    //SendTo(station_1, ADDR_STATION_1, PORT_LISTEN_1_2, "ATTEND_PIECE", 12);
    setPieceReceived(FALSE);
}

/**
 * Realise le parcourt avec la piece courante
 */
void processPiece()
{
    // Attend que la station 1 nous ai donne une piece
    printf("Attend que la station 1 nous ai donne une piece ...\n");
    //while(!getPieceReceived())
    //    waitTime(500);
    
    printf("Attend que la piece soit en place ...\n");
    waitBit(PIECE, TRUE);
    waitTime(1000);

    // Monte l'ascenseur
    printf("Monte l'ascensseur ...\n");
    setActuateur(ASC_DESCEND, OFF);
    setActuateur(ASC_MONTE, ON);
    waitBit(ASC_HAUT, TRUE);
    setActuateur(ASC_MONTE, OFF);
    
    // Attend que la station 3 soit en reception
    printf("Attend que la station 3 soit prete a recevoir une piece ...\n");
    while(!getCanGivePiece())
        waitTime(500);
    
    // Active le pousseur
    printf("Active le pousseur de piece ...\n");
    setActuateur(PP, ON);
    waitTime(1000);
    setActuateur(PP, OFF);
    
    // Active le coussin
    printf("Active le coussin d'air ...\n");
    setActuateur(COUSSIN_AIR, ON);
    waitTime(2000);
    setActuateur(COUSSIN_AIR, OFF);
    
    // Previens la station 3 qu'on lui donne une piece
    printf("Previens la station 3 qu'on lui donne une piece ...\n");
    SendTo(station_3, ADDR_STATION_3, PORT_LISTEN_3_2, "DONNE_PIECE", 11);
    setCanGivePiece(FALSE);
}

/**
 * Recoit les messages de la station 1
 */
void* receiveFrom1(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_2_1);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 1: %s\n", buffer);
            
            // Indique que la station 1 nous a donne une piece
            if(strcmp("DONNE_PIECE", buffer)) {
                setPieceReceived(TRUE);
            }
        }
    }
}

/**
 * Recoit les messages de la station 3
 */
void* receiveFrom3(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_2_3);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 3: %s\n", buffer);
            
            // Indique que la station 3 attend une piece
            if(strcmp("ATTEND_PIECE", buffer) == 0) {
            	printf("Station 3 attend piece\n");
                setCanGivePiece(TRUE);
            }
        }
    }
}