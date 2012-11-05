#include "station3.h"

#include "common.h"
#include "network.h"

#include <stdio.h>

void reinitialise();
void processPiece();
void* receiveFrom2(void* arg);
void* receiveFrom4(void* arg);

int station_2;
int station_4;
pthread_t receive_2, receive_4;


int main(void)
{
    initSignal(handlerEnd);
    
    // Initialise les mutex
    pthread_mutex_init(&mutex_received, NULL);
    pthread_mutex_init(&mutex_give, NULL);
    
    // Cree un thread qui ecoutera ce que la station 1 et 3 lui dira
    if(pthread_create(&receive_2, NULL, receiveFrom2, NULL) != 0 ||
       pthread_create(&receive_4, NULL, receiveFrom4, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    initSend(&station_2, ADDR_STATION_2, PORT_LISTEN_2_3);
    initSend(&station_4, ADDR_STATION_4, PORT_LISTEN_4_3);
    
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
    // Remonte la fraiseuse
    printf("Remonte la fraiseuse ...\n");
    setActuateur(FRAISE_DESC, OFF);
    setActuateur(FRAISE_MONTE, ON);
    wait(FRAISE_HAUT, TRUE);
    setActuateur(FRAISE_MONTE, OFF);
    
    // Rentre l'ejecteur de piece
    printf("Desactive l'ejecteur de piece ...\n");
    setActuateur(EJECTER, OFF);
    waitTime(1000);
    
    // ?
    printf("? ...\n");
    setActuateur(SOL_H, OFF);
    setActuateur(SOL_V, OFF);
    waitTime(1000);
    
    // Eteint le caroussel
    printf("Eteint le caroussel ...\n");
    setActuateur(CAROUSSEL, OFF);
    wait(CAROUSSEL_STABLE, TRUE);
    
    // Previens la station 2 qu'on attend une piece
    printf("Previens la station 2 que l'on attend une piece ...\n");
    SendTo(station_2, ADDR_STATION_2, PORT_LISTEN_2_3, "ATTEND_PIECE", 12);
    setPieceReceived(FALSE);
}

/**
 * Realise le parcourt avec la piece courante
 */
void processPiece()
{
// TODO FRAISEUSE et SOL_H/V
    // Attend que la station 2 nous ai donne une piece
    printf("Attend une piece de la station 2 ...\n");
    while(!getPieceReceived())
        waitTime(500);
    
    // Attend que la piece soit presente
    printf("Attend la piece dans le slot 1 ...\n");
    wait(PIECE_SLOT_1, TRUE);
    
    // Place la piece en position 2
    printf("Active le caroussel ...\n");
    setActuateur(CAROUSSEL, ON);
    waitTime(100);
    setActuateur(CAROUSSEL, OFF);
    wait(CAROUSSEL_STABLE, TRUE);
    
    // Attend que la piece soit presente
    printf("Attend la piece dans le slot 2 ...\n");
    wait(PIECE_SLOT_2, TRUE);
    
    // Place la piece en position 3
    printf("Active le caroussel ...\n");
    setActuateur(CAROUSSEL, ON);
    waitTime(100);
    setActuateur(CAROUSSEL, OFF);
    wait(CAROUSSEL_STABLE, TRUE);
    
    // Attend que la piece soit presente
    printf("Attend la piece dans le slot 3 ...\n");
    wait(PIECE_SLOT_3, TRUE);
    
    // Place la piece en position 4
    printf("Active le caroussel ...\n");
    setActuateur(CAROUSSEL, ON);
    waitTime(100);
    setActuateur(CAROUSSEL, OFF);
    wait(CAROUSSEL_STABLE, TRUE);
    
    // Ejecte la piece
    printf("Ejecte la piece ...\n");
    setActuateur(EJECTER, ON);
    waitTime(1000);
    setActuateur(EJECTER, OFF);
    
    // Previens la station 4 qu'on lui donne une piece
    printf("Previens la station 4 qu'on lui donne une piece ...\n");
    SendTo(station_4, ADDR_STATION_4, PORT_LISTEN_4_3, "DONNE_PIECE", 11);
    setCanGivePiece(FALSE);
}

/**
 * Recoit les messages de la station 2
 */
void* receiveFrom2(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_3_2);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 2: %s\n", buffer);
            
            // Indique que la station 1 nous a donné une piece
            if(strcmp("DONNE_PIECE", buffer)) {
                setPieceReceived(TRUE);
        }
    }
}

/**
 * Recoit les messages de la station 4
 */
void* receiveFrom4(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_3_4);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 4: %s\n", buffer);
            
            // Indique que la station 4 attend une piece
            if(strcmp("ATTEND_PIECE", buffer)) {
                setCanGivePiece(TRUE);
            }
        }
    }
}