#include "station4.h"

#include "common.h"
#include "network.h"

#define ENTREE  0
#define TUBE1   1
#define TUBE2   2
#define TUBE3   3

#include <stdio.h>

void reinitialise();
void processPiece();
void* receiveFrom3(void* arg);
void handlerEnd4(int signo);

int station_3;
int station_1;
pthread_t receive_3;

pthread_mutex_t mutex_pieceType;
int pieceType = METAL;


int main(void)
{    
    struct sigaction act;
    sigset_t set;
    
    initSignal();
    
    // Initialise les mutex
    pthread_mutex_init(&mutex_received, NULL);
    pthread_mutex_init(&mutex_give, NULL);
    
    // Cree un thread qui ecoutera ce que la station 1 et 3 lui dira
    if(pthread_create(&receive_3, NULL, receiveFrom3, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    // Attend que le serveur d'ecoute soit lance
    printf("Appuyez sur une touche lorsque station 3 et station 1 seront lance ...\n");
    while(kbhit() == 0);
    
    initSend(&station_3, ADDR_STATION_3, PORT_LISTEN_3_4);
    initSend(&station_1, ADDR_STATION_1, PORT_LISTEN_1_4);
    
    initLink();
    
    // Arme le SIGINT
    sigemptyset(&set);
    sigaddset(&set, SIGINT);
    
    act.sa_flags = 0;
    act.sa_mask = set;
    act.sa_handler = handlerEnd4;
    sigaction(SIGINT, &act, NULL);
        
    // Active le mode de positionnement
    printf("Activation du mode de positionnement ...\n");
    setActuateur(AMPLIFICATEUR, ON);
    setActuateur(REGULATEUR, ON);
    
    // Calibrage
    printf("Calibrage ...\n");
    waitTime(5000);
    
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
    // Place le bras au dessus de la station 3
    deplacerBras(ENTREE);
    
    // Ouvre la pince
    printf("Ouvre la pince ...\n");
    setActuateur(PINCE, ON);
    waitTime(500);
    
    // Remonte le bras
    printf("Remonte le bras ...\n");
    setActuateur(BRAS_V, OFF);
    waitBit(BRAS_V_RENTRE, TRUE);
    
    // Rentre le bras
    printf("Rentre le bras ...\n");
    setActuateur(SORTIR_BRAS_H, OFF);
    setActuateur(RENTRER_BRAS_H, ON);
    waitBit(BRAS_H_RENTRE, TRUE);
    
    // Previens la station 3 qu'on attend une piece
    printf("Previens la station 3 que l'on attend une piece ...\n");
    SendTo(station_3, ADDR_STATION_3, PORT_LISTEN_3_4, "ATTEND_PIECE", 12);
    setPieceReceived(FALSE);
}

/**
 * Realise le parcourt avec la piece courante
 */
void processPiece()
{
    // Attend que la station 3 nous ai donne une piece
    printf("Attend une piece de la station 3 ...\n");
    while(!getPieceReceived())
        waitTime(500);
    
    // Ouvre la pince
    printf("Ouvre la pince ...\n");
    setActuateur(PINCE, ON);
    waitTime(500);
    
    // Abaisser le bras
    printf("Abaisse le bras ...\n");
    setActuateur(BRAS_V, ON);
    waitBit(BRAS_V_SORTIT, TRUE);
    
    // Prendre la piece
    printf("Prend la piece ...\n");
    setActuateur(PINCE, OFF);
    waitTime(500);
    
    // Remonte le bras
    printf("Remonte le bras ...\n");
    setActuateur(BRAS_V, OFF);
    waitBit(BRAS_V_RENTRE, TRUE);
    
    switch(getPieceType()) {
        case METAL:
            // Place le bras au dessus du tube 1
            deplacerBras(TUBE1);
            break;
        
        case ROUGE:
            // Place le bras au dessus du tube 2
            deplacerBras(TUBE2);
            break;
            
        default:    // case NOIRE:
            // Place le bras au dessus du tube 3
            deplacerBras(TUBE3);
    }
    
    // Sort le bras
    setActuateur(RENTRER_BRAS_H, OFF);
    setActuateur(SORTIR_BRAS_H, ON);
    waitBit(BRAS_H_SORTIT, TRUE);
    
    // Lache la piece
    printf("Lache la piece ...\n");
    setActuateur(PINCE, ON);
    waitTime(500);
    
    // Rentre le bras
    printf("Rentre le bras ...\n");
    setActuateur(SORTIR_BRAS_H, OFF);
    setActuateur(RENTRER_BRAS_H, ON);
    waitBit(BRAS_H_RENTRE, TRUE);
    
    // Previens la station 1 qu'on a finit
    printf("Previens la station 1 qu'on a finit le traitement ...\n");
    SendTo(station_1, ADDR_STATION_1, PORT_LISTEN_1_4, "FIN_PIECE", 11);
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
            if(strcmp("DONNE_PIECE", buffer) == 0) {
                printf("Piece recue de la station 3\n", buffer);
                setPieceReceived(TRUE);
            }
            if(strcmp("PIECE_ROUGE", buffer) == 0) {
            	printf("Piece rouge en transit\n");
                setPieceType(ROUGE);
            }
            if(strcmp("PIECE_METAL", buffer) == 0) {
            	printf("Piece mettalique en transit\n");
                setPieceType(METAL);
            }
            if(strcmp("PIECE_NOIRE", buffer) == 0) {
            	printf("Piece noire en transit\n");
                setPieceType(NOIRE);
            }
        }
    }
}

/**
 * Acces a pieceType
 */
int getPieceType()
{
    int temp;
    
    pthread_mutex_lock(&mutex_pieceType);
    temp = pieceType;
    pthread_mutex_unlock(&mutex_pieceType);
    
    return temp;
}
int setPieceType(int value)
{
    pthread_mutex_lock(&mutex_pieceType);
    pieceType = value; 
    pthread_mutex_unlock(&mutex_pieceType);
    
    return 0;
}

/**
 * Deplace le bras dans la position voulue
 * @param position      Position voulue
 */
void deplacerBras(int position)
{
     // Place les bit 2 et 3 du positionnement a 0
    printf("Desactive les 2 bit non significatif de positionnement ...\n");
    setActuateur(POS_2, OFF);
    setActuateur(POS_3, OFF);
    
    // Donne la position
    switch(position) {
        case ENTREE:
            printf("Place le bras sur l'entree ...\n");
            setActuateur(POS_0, OFF);
            setActuateur(POS_1, OFF);
            break;
        
        case TUBE1:
            printf("Place le bras sur le tube 1 ...\n");
            setActuateur(POS_0, ON);
            setActuateur(POS_1, OFF);
            break;
        
        case TUBE2:
            printf("Place le bras sur le tube 2 ...\n");
            setActuateur(POS_0, OFF);
            setActuateur(POS_1, ON);
            break;
        
        case TUBE3:
            printf("Place le bras sur le tube 3 ...\n");
            setActuateur(POS_0, ON);
            setActuateur(POS_1, ON);
            break;
    }
    
    // Tempo entre le positionnement des bit et le front montant
    waitTime(300);
    
    // Impulsion pour creer le deplacement
    printf("Envoi de l'impulsion pour deplacer le bras ...\n");
    setActuateur(START, OFF);
    waitTime(200);
    setActuateur(START, ON);
    waitTime(1000);
}

/**
 * Ferme le programme proprement
 *
 * @param signo     Numero de l'intteruption
 */
void handlerEnd4(int signo)
{
    // Reception d'un Ctrl + C
    printf("Terminaison de station 4 par Ctrl + C\n");
    
    // Desactive le mode de positionnement
    printf("Desactivation du mode de positionnement ...\n");
    setActuateur(AMPLIFICATEUR, OFF);
    setActuateur(REGULATEUR, OFF);
    
    handlerEnd(signo);
}