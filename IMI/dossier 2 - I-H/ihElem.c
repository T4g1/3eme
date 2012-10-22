#include <stdio.h>
#include <signal.h>
#include <sys/neutrino.h>
#include <sys/siginfo.h>
#include <hw/inout.h>

// IRQ 1 = KeyBoard
#define IRQ1 1

const struct sigevent* isr_handler(void *arg, int id);

struct sigevent event;
int interruptID;


int main(void)
{
    // Lance un thread dédié a la gestion des interruptions
    pthread_create(NULL, NULL, int_thread, NULL);
    
    // Demande les permisisons pour effectuer les IO
    if(ThreadCtl( _NTO_TCTL_IO, 0) == -1) {
        printf("Permissions d'IO refusées\n");
        return 1;
    }
    
    // Attache le handler d'interruption
    SIGEV_INTR_INIT(&event);
    interruptID = InterruptAttach(IRQ1, isr_handler, &event, sizeof(event), _NTO_INTR_FLAGS_END);
    
    // Boucle principale
    while(1) {
        // Attente d'une saisie d'une interruption
        InterruptWait(NULL, NULL);
        
        printf("Le thread a recu l'interruption\n");
    }
    
    InterruptDetach(interruptID);
    printf("Fin du programme\n");
}

/**
 * Handler pour les ISR
 */
const struct sigevent* isr_handler(void *arg, int id)
{
    printf("Execution du handler d'ISR\n");
    
    // Vide la ligne d'interruption
    out8(IRQ1, 0x00);
    
    return &event;
}