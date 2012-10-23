#include "station2.h"

#include "common.h"


int main(void)
{
    init();
    
    // Boucle principale
    while(1) {
        reinitialise();
        processPiece();
    }
    
    close();
    
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