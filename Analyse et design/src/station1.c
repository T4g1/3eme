#include "station1.h"

#include "common.h"


int main(void)
{
    init();
    
    // Boucle principale
    while(1) {
        // Met le bras a droite
        setActuateur(BRAS_POSITION, OFF);
        
        wait(PIECE_1, TRUE);
    }
    
    close();
    
    return 0;
}