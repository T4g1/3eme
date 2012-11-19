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
    initSignal();
    
    // Cree un thread qui ecoutera ce que la station 2 et 4 lui dira
    if(pthread_create(&receive_2, NULL, receiveFrom2, NULL) != 0 ||
       pthread_create(&receive_4, NULL, receiveFrom4, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    // Attend que le serveur d'~coute soit lanc~
    printf("Appuyez sur une touche lorsque le serveur station 2 d'ecoute sera lance ...\n");
    while(kbhit() == 0);
    
    initSend(&station_2, ADDR_STATION_2, PORT_LISTEN_2_1);
    
    initLink();
    
    // Debut du traitement
    setPieceReceived(TRUE);
    
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
    waitBit(PIECE_ASPIRE, FALSE);
    
    // Place le bras a gauche pour debloquer la station 2
    printf("Deplace le bras vers la gauche ...\n");
    setActuateur(BRAS_POSITION, OFF);
    waitBit(BRAS_GAUCHE, TRUE);
    
    // Place le poussoir principal en position rentree
    printf("Rentre le poussoir principal ...\n");
    setActuateur(MAIN_P, OFF);
    waitBit(MAIN_P_RENTRE, TRUE);
    
    // Rentre tout les poussoir secondaires
    printf("Rentre tout les poussoirs secondaire ...\n");
    setActuateur(PP1_OUT, OFF);
    setActuateur(PP2_OUT, OFF);
    setActuateur(PP3_OUT, OFF);
    setActuateur(PP1_IN, ON);
    setActuateur(PP2_IN, ON);
    setActuateur(PP3_IN, ON);
    waitBit(PP1_RENTRE, TRUE);
    waitBit(PP2_RENTRE, TRUE);
    waitBit(PP3_RENTRE, TRUE);
}

/**
 * Traite une piece et l'ammenne vers la station 2
 */
void processPiece()
{
    // Attend que la station 4 ai finit son traitement
    printf("Attend que la station 4 ai finit son traitement ...\n");
    while(!getPieceReceived())
        waitTime(500);
        
    // Attend que la station 2 soit en attente de piece
    printf("Attend que la station 2 soit en attente de piece ...\n");
    while(!getCanGivePiece())
        waitTime(500);
    
    setPieceReceived(FALSE);
    
    // Amenne le bras a droite
    setActuateur(BRAS_POSITION, ON);
    waitBit(BRAS_DROITE, TRUE);
    
    printf("Attend une piece dans l'un des tubes ...\n");
    while(!getCapteur(PIECE_1) && !getCapteur(PIECE_2) )
    	waitTime(10);
    
    // Piece dans le tube 1
    if(getCapteur(PIECE_1)) {
        printf("Pousse la piece du tube 1 ...\n");
        setActuateur(PP1_IN, OFF);
        setActuateur(PP1_OUT, ON);
        waitBit(PP1_SORTIT, TRUE);
        
        setActuateur(PP1_OUT, OFF);
        setActuateur(PP1_IN, ON);
        waitBit(PP1_RENTRE, TRUE);
    }
    // Piece dans le tube 2
    else if(getCapteur(PIECE_2)) {
        printf("Pousse la piece du tube 2 ...\n");
        setActuateur(PP2_IN, OFF);
        setActuateur(PP2_OUT, ON);
        waitBit(PP2_SORTIT, TRUE);
        
        setActuateur(PP2_OUT, OFF);
        setActuateur(PP2_IN, ON);
        waitBit(PP2_RENTRE, TRUE);
    }
    // Piece dans le tube 3
    /*else if(getCapteur(PIECE_3)) {
        printf("Pousse la piece du tube 3 ...\n");
        setActuateur(PP3_IN, OFF);
        setActuateur(PP3_OUT, ON);
        waitBit(PP3_SORTIT, TRUE);
        
        setActuateur(PP3_OUT, OFF);
        setActuateur(PP3_IN, ON);
        waitBit(PP3_RENTRE, TRUE);
    }*/ // ERREUR STATION 3 - Capteur piece
    
    // Active le poussoir principal
    printf("Active le poussoir principal ...\n");
    setActuateur(MAIN_P, ON);
    waitBit(MAIN_P_SORTIT, TRUE);
    
    // Place le bras a gauche
    printf("Place le bras a gauche ...\n");
    setActuateur(BRAS_POSITION, OFF);
    waitBit(BRAS_GAUCHE, TRUE);
    
    // Aspire la piéce
    printf("Active l'aspiration ...\n");
    setActuateur(ASPIRATION, ON);
    waitBit(PIECE_ASPIRE, TRUE);
    
    // Place le bras a droite
    printf("Place le bras a droite ...\n");
    setActuateur(BRAS_POSITION, ON);
    waitBit(BRAS_DROITE, TRUE);
    
    // Rentre le poussoir principal
    printf("Rentre le poussoir principal ...\n");
    setActuateur(MAIN_P, OFF);
    waitBit(MAIN_P_RENTRE, TRUE);
    
    // Desactive l'aspiration
    printf("Desactive l'aspiration ...\n");
    setActuateur(ASPIRATION, OFF);
    waitBit(PIECE_ASPIRE, FALSE);
    
    // Place le bras a gauche
    printf("Place le bras a gauche ...\n");
    setActuateur(BRAS_POSITION, OFF);
    waitBit(BRAS_GAUCHE, TRUE);
    
    // Previens la station 2 qu'elle a une piece
    printf("Previens la station 2 qu'on lui donne une piece ...\n");
    SendTo(station_2, ADDR_STATION_2, PORT_LISTEN_2_1, "DONNE_PIECE", 11);
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
    
    initListen(&sockRecv, &addr, PORT_LISTEN_1_2);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Recu de la station 2: %s\n", buffer);
            
            // Indique que la station 2 attend une piece
            if(strcmp("ATTEND_PIECE", buffer) == 0) {
                printf("La station 2 attend une piece ...\n", buffer);
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
            if(strcmp("FIN_PIECE", buffer) == 0) {
                printf("La station 4 a fini ...\n", buffer);
                setPieceReceived(TRUE);
            }
        }
    }
}