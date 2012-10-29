#include "station2.h"

#include "common.h"
#include "network.h"

void* receiveFrom1(void* arg);

int sockSend;


int main(void)
{
    int s;
    
    // Cree un thread qui ecoutera ce que la station 1 lui dira
    pthread_t th;
    if(pthread_create(&th, NULL, receiveFrom1, NULL) != 0) {
        write(1, "Erreur thread\n", 40);
        return 1;
    }
    
    // Socket pour envoyer des messages
    if((sockSend = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
        write(1, "Creation de socket echouee\n", 40);
        return 1;
    }
    for(;;){}
    initLink();
    
    // Boucle principale
    while(1) {
        reinitialise();
        processPiece();
    }
    
    closeLink();
    
    return 0;
}


/**
 * Passe la station en attente de piece
 */
void reinitialise()
{
    // Coussin d'air eteint ?
    setActuateur(COUSSIN_AIR, OFF);
    
    // Position du pousseur de piece
    setActuateur(PP, OFF);
    wait(PP_RENTRE, TRUE);
    
    // Position de l'ascenseur
    setActuateur(ASC_MONTE, OFF);
    setActuateur(ASC_DESCEND, ON);
    wait(ASC_BAS, TRUE);
    setActuateur(ASC_DESCEND, OFF);
    
    // Previens la station 1 qu'on attend une piece
    SendTo(sockSend, ADDR_STATION_1, PORT_LISTEN_1_2, "ATTEND", 7);
}

/**
 * Realise le parcourt avec la piece courante
 */
void processPiece()
{
    // TODO
    
    // Previens la station 3 qu'on lui donne une piece
    // TODO
}

/**
 * Recoit les messages de la station 1
 */
void* receiveFrom1(void* arg)
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len;
    
    initListen(&sockRecv, &addr, PORT_LISTEN_2_1);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            write(1, buffer, BUFFER_SIZE);
        }
    }
}