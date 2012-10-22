#include <stdio.h>
#include <signal.h>
#include <sys/neutrino.h>
#include <sys/siginfo.h>
#include <sys/trace.h>
#include <hw/inout.h>

// IRQ 1 = KeyBoard
#define IRQ1 1

const struct sigevent* isr_handler(void *arg, int id);

struct sigevent event;
int interruptID;


int main(void)
{
    TraceEvent(_NTO_TRACE_STOP);
   
    // Demande les permisisons pour effectuer les IO
    if(ThreadCtl( _NTO_TCTL_IO, 0) == -1) {
        printf("Permissions d'IO refusées\n");
        return 1;
    }
    
    // Attache le handler d'interruption
    SIGEV_INTR_INIT(&event);
    interruptID = InterruptAttach(IRQ1, isr_handler, NULL, 0, _NTO_INTR_FLAGS_END);
    
    // Boucle principale
    //while(1) {
        // Attente d'une saisie d'une interruption
        InterruptWait(NULL, NULL);
        
        printf("Le thread a recu l'interruption\n");
    //}
    
    TraceEvent(_NTO_TRACE_STOP);
    InterruptDetach(interruptID);
    printf("Fin du programme\n");
}

/**
 * Handler pour les ISR
 */
const struct sigevent* isr_handler(void *arg, int id)
{
    TraceEvent();
    
    return &event;
}