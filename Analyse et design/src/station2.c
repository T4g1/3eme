#include "station2.h"

int fd;
int capteurs;
int actuateurs;


int main(void)
{
    // Connexion a la station 2
    int fd = open("/dev/pio_d48", O_RDWR);
    
    // Boucle principale
    while(1) {
        reinitialise();
        processPiece();
    }
    
    close(fd);
    
    return 0;
}


/**
 * Passe la station en attente de piéce
 */
void reinitialise()
{
    capteurs = readCapteurs(fd);
    
    // Coussin d'air éteint ?
    // TODO
    
    // Position du pousseur de piéce
    // TODO
    
    // Position de l'ascenseur
    // TODO
    
    // Préviens la station 1 qu'on attend une piéce
    // TODO
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