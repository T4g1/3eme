#include "station2.h"

#include "common.h"

void* receiveFrom1(void* arg);

int sockSend;


int main(void)
{
    int s;
    
    // Crée un thread qui ecoutera ce que la station 1 lui dira
    pthread_t th;
    if(pthread_create(&th, NULL, receiveFromStation1, NULL) != 0) {
        write(1, "Erreur thread\n", 40);
        return 1;
    }
    
    // Socket pour envoyer des messages
    if((sockSend = socket(AF_INET, SOCK_DGRAM, 0)) == -1) {
        write(1, "Creation de socket echouee\n", 40);
        return 1;
    }
    
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
 * Passe la station en attente de piéce
 */
void reinitialise()
{
    // Coussin d'air éteint ?
    setActuateur(COUSSIN_AIR, OFF);
    
    // Position du pousseur de piéce
    setActuateur(PP, OFF);
    wait(PP_RENTRE, TRUE);
    
    // Position de l'ascenseur
    setActuateur(ASC_MONTE, OFF);
    setActuateur(ASC_DESCEND, ON);
    wait(ASC_BAS, TRUE);
    setActuateur(ASC_DESCEND, OFF);
    
    // Préviens la station 1 qu'on attend une piéce
    SendTo(sockSend, ADDR_STATION_1, PORT_LISTEN_1_2, "ATTEND", 7);
}

/**
 * Réalise le parcourt avec la piéce courante
 */
void processPiece()
{
    // TODO
    
    // Préviens la station 3 qu'on lui donne une piéce
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
    
    // Réception des données
    while(1) {
        addr_len = sizeof(addr);
        
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            write(1, buffer, BUFFER_SIZE);
        }
    }
}