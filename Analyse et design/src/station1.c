#include "station1.h"

#include "common.h"
#include "network.h"

#include <stdio.h>

void reinitialise();
void processPiece();
void* receiveFrom2(void* arg);
void* receiveFrom4(void* arg);

int station_2;
pthread_t receive_2, receive_4;


int main(void)
{
    initSignal(handlerEnd);
    
    // Cree un thread qui ecoutera ce que la station 2 et 4 lui dira
    if(pthread_create(&receive_2, NULL, receiveFrom2, NULL) != 0 ||
       pthread_create(&receive_4, NULL, receiveFrom4, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    initSend(&station_2, ADDR_STATION_2, PORT_LISTEN_2_1);
    
    initLink();
    
    // Debut de la procedure
    setCanGivePiece(TRUE);
    
    // Met le bras a droite
    printf("1\n");
    wait(PP1_OUT, TRUE);
    printf("2\n");
    
    // Boucle principale
    while(1) {
        reinitialise();
        processPiece();
    }
    
    closeLink();
    
    return 0;
}

/**
 * Place la station en position initiale
 */
void reinitialise()
{
    // Stoppe l'aspiration
    printf("Stoppe l'aspiration de piece ...\n");
    setActuateur(ASPIRATION, OFF);
    wait(PIECE_ASPIRE, FALSE);
    
    // Place le bras a gauche pour debloquer la station 2
    printf("Deplace le bras vers la gauche ...\n");
    setActuateur(BRAS_POSITION, OFF);
    wait(BRAS_GAUCHE, TRUE);
    
    // Place le poussoir principal en position rentree
    printf("Rentre le poussoir principal ...\n");
    setActuateur(MAIN_P, OFF);
    wait(MAIN_P_RENTRE, TRUE);
    
    // Rentre tout les poussoir secondaires
    printf("Rentre tout les poussoirs secondaire ...\n");
    setActuateur(PP1_OUT, OFF);
    setActuateur(PP2_OUT, OFF);
    setActuateur(PP3_OUT, OFF);
    setActuateur(PP1_IN, ON);
    setActuateur(PP2_IN, ON);
    setActuateur(PP3_IN, ON);
    wait(PP1_RENTRE, TRUE);
    wait(PP2_RENTRE, TRUE);
    wait(PP3_RENTRE, TRUE);
}

/**
 * Traite une piece et l'ammenne vers la station 2
 */
void processPiece()
{
    // Attend que la station 2 soit en attente de piece
    printf("Attend que la station 2 soit en attente de piece ...\n");
    while(!getCanGivePiece())
        waitTime(500);
    
    // Amenne le bras a droite
    setActuateur(BRAS_POSITION, ON);
    wait(BRAS_DROITE, TRUE);
    
    // Piece dans le tube 1
    if(getCapteur(PIECE_1)) {
        printf("Pousse la piece du tube 1 ...\n");
        setActuateur(PP1_IN, OFF);
        setActuateur(PP1_OUT, ON);
        wait(PP1_SORTIT, TRUE);
        
        setActuateur(PP1_OUT, OFF);
        setActuateur(PP1_IN, ON);
        wait(PP1_RENTRE, TRUE);
    }
    // Piece dans le tube 2
    else if(getCapteur(PIECE_2)) {
        printf("Pousse la piece du tube 2 ...\n");
        setActuateur(PP2_IN, OFF);
        setActuateur(PP2_OUT, ON);
        wait(PP2_SORTIT, TRUE);
        
        setActuateur(PP2_OUT, OFF);
        setActuateur(PP2_IN, ON);
        wait(PP2_RENTRE, TRUE);
    }
    // Piece dans le tube 3
    else if(getCapteur(PIECE_3)) {
        printf("Pousse la piece du tube 3 ...\n");
        setActuateur(PP3_IN, OFF);
        setActuateur(PP3_OUT, ON);
        wait(PP3_SORTIT, TRUE);
        
        setActuateur(PP3_OUT, OFF);
        setActuateur(PP3_IN, ON);
        wait(PP3_RENTRE, TRUE);
    }
    
    // Active le poussoir principal
}

/**
 * Recoit les messages de la station 2
 */
void* receiveFrom2(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_1_2);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("%s\n", buffer);
        }
    }
}

/**
 * Recoit les messages de la station 2
 */
void* receiveFrom2(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_1_2);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 2: %s\n", buffer);
            
            // Indique que la station 2 attend une piece
            if(strcmp("ATTEND_PIECE", buffer)) {
                setCanGivePiece(TRUE);
            }
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
    
    initListen(&sockRecv, &addr, PORT_LISTEN_1_4);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 4: %s\n", buffer);
            
            // Indique que la station 4 a finit
            if(strcmp("FIN_PIECE", buffer)) {
                setPieceReceived(TRUE);
            }
        }
    }
}