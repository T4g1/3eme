#include "common.h"

void receiveFromSuperviseur();

union {
    struct ACTUATEURS act ;
    unsigned char byte ;
} u_act;

union {
    struct CAPTEURS capt ;
    unsigned char byte ;
} u_capt, new_capt;

struct {
    time_t tv_sec;
    long tv_nsec;
} timespec;

int fdIn, fdOut;
int superviseur;

int main(void)
{
    pthread_t th_receive;
    char buffer[BUFFER_SIZE];
    
    // Initialisation du PETRA en entrée
    if((fdIn = open(FILE_PETRA_IN, O_RDONLY)) == -1) {
        perror("Err. ouvertue petra out");
        return -1;
    }
    
    // Initialisation du PETRA en sortie
    if((fdOut = open(FILE_PETRA_OUT, O_WRONLY)) == -1) {
        perror("Err. ouvertue petra out");
        return -1;
    }
    
    // Thread de reception des messages
    if(pthread_create(&th_receive, NULL, receiveFromSuperviseur, NULL) != 0) {
        printf("Erreur thread\n");
        return 1;
    }
    
    // Attend que le serveur d'écoute soit lancé
    printf("Appuyez sur une touche lorsque le serveur d'ecoute sera lance ...\n");
    while(kbhit() == 0);
    
    initSend(&superviseur, ADDR_SUPERVISEUR, PORT_VERS_SUPERVISEUR);
    
    // Place tout les actuateurs à 0
    u_act.byte = 0x00;
    write(fdOut, &u_act.byte, 1);
    
    // Mise a jour des capteurs
    while(1) {
        read(fdIn, &new_capt.byte, 1);
        
        if(new_capt.byte == u_capt.byte)
            continue;
        
        // Envois des modifications
        u_capt.byte = new_capt.byte;
        sprintf(buffer, "CAPTEUR:%d", u_capt.byte);
        SendTo(superviseur, ADDR_SUPERVISEUR, PORT_VERS_SUPERVISEUR, buffer, BUFFER_SIZE);
    }
    
    // Place tout les actuateurs à 0
    u_act.byte = 0x00;
    write(fdOut, &u_act.byte, 1);
    
    close(fdIn);
    close(fdOut);
    
    return 1;
}

void receiveFromSuperviseur()
{
    char buffer[BUFFER_SIZE];
    struct sockaddr_in addr;
    int v, sockRecv, addr_len, erreur = -1;
    
    initListen(&sockRecv, &addr, PORT_DEPUIS_SUPERVISEUR);
    
    // Reception des donnees
    while(1) {
        addr_len = sizeof(addr);
        if ((v = recvfrom(sockRecv, buffer, BUFFER_SIZE, 0, (struct sockaddr *)&addr, &addr_len)) > 0) {
            printf("Message recu: %s\n", buffer);
        }
    }
}