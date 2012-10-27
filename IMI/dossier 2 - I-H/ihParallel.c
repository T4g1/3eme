#include <stdio.h>
#include <stdlib.h>         /* pour EXIT_* */
#include <stdint.h>         /* pour uintptr_t */
#include <hw/inout.h>       /* pour les fonctions in*() et out*()*/
#include <sys/neutrino.h>   /* for ThreadCtl() */
#include <sys/syspage.h>    /* for for cycles_per_second */
#include <sys/mman.h>       /* for mmap_device_io() */

/* The Neutrino IO port used here corresponds to a single register, which is
* one byte long */
#define PORT_LENGTH 1 

/* The first parallel port usually starts at 0x378. Each parallel port is
* three bytes wide. The first byte is the Data register, the second byte is
* the Status register, the third byte is the Control register. */
#define CTRL_ADDRESS 0x37a
/* bit 2 = printer initialisation (high to initialise)
* bit 4 = hardware IRQ (high to enable) */
#define INIT_BIT 0x04
#define INTR_BIT 0x10

#define LOW 0x00
#define HIGH 0xFF
#define DATA_ADDRESS 0x378
#define CTRL_ADDRESS 0x37a

#define PARALLELIRQ 0x07
#define MAX_COUNT 1000
#define NB_MESURES 100

void* int_thread(void* arg);
extern const struct sigevent* interrupt_handler(void*, int);

struct sigevent event;

uint64_t clock_cycles[3];

int main(void)
{
    uint64_t cps;
    int intr_id, nb_mesure;
    float temps_envoi;
    float temps_total;
    float moyenne_envoi = 0;
    float moyenne_total = 0;

    pthread_t th;

    /* Give this thread root permissions to access the hardware */
    if(ThreadCtl( _NTO_TCTL_IO, NULL ) == -1) {
        printf( "Can't get root permissions\n" );
        return -1;
    }

    if(pthread_create(&th,NULL, int_thread,NULL) != 0){
        printf("Impossible de créer le thread\n");
    }

    /* Tell the kernel to attach an interrupt signal event to this thread */
    event.sigev_notify = SIGEV_INTR;
    if(intr_id = InterruptAttach(PARALLELIRQ, interrupt_handler, NULL, 0, _NTO_INTR_FLAGS_END) == -1) {
        printf( "Couldn't attach event to IRQ %d\n", PARALLELIRQ );
        return EXIT_FAILURE;
    }
    
    /* Find out how many CPU cycles there are per second */
    cps = SYSPAGE_ENTRY(qtime)->cycles_per_sec;

    for(nb_mesure=0; nb_mesure < NB_MESURES; nb_mesure++) {
        InterruptWait(0,NULL) ;
        
        // Temps de réaction
        clock_cycles[2] = ClockCycles();
        
        temps_envoi = clock_cycles[1] - clock_cycles[0];
        temps_envoi = (float)temps_envoi/cps;
        temps_envoi*=1000000;
        
        temps_total = clock_cycles[2] - clock_cycles[0];
        temps_total  = (float)temps_total/cps;
        temps_total *=1000000;
        
        moyenne_envoi += temps_envoi;
        moyenne_total += temps_total;
        
        printf("%d: -> %.2f; = %.2f\n", nb_mesure, temps_envoi, temps_total);
    }
    
    moyenne_envoi /= NB_MESURES;
    moyenne_total /= NB_MESURES;
    
    printf("Moyenne: -> %.2f; = %2f\n", moyenne_envoi, moyenne_total);
    printf("-> envoi, = total\n");

    /* Remove the attachment from this thread */
    InterruptDetach(intr_id);

    return EXIT_SUCCESS;
}

/**
 * Thread générant des interruptions
 */ 
void* int_thread(void* arg){
    int k = 0;
    
    uintptr_t data_handle;
    uintptr_t ctrl_handle;
    
    /* Get a handle to the parallel port's control register */
    ctrl_handle = mmap_device_io( PORT_LENGTH, CTRL_ADDRESS );
    /* Initialise the parallel port */
    out8( ctrl_handle, INIT_BIT );
    /* Enable interrupts on the parallel port */
    out8( ctrl_handle, INTR_BIT );

    /* prend le contrôle du registre de données du port */
    data_handle = mmap_device_io( PORT_LENGTH, DATA_ADDRESS );

    // Génére des interruptions
    while(1) {
        out8( data_handle, LOW );
        for(k=0;k<0xFFFFF;k++);
        
        // Temps d'envoi
        clock_cycles[0] = ClockCycles();
        
        // Interruption
        out8( data_handle, HIGH );
        for(k=0;k<0xFFFFF;k++);
    }
    return NULL;
}

/**
 * Gestionnaire d'interruption
 */
extern const struct sigevent *interrupt_handler(void * area,int id)
{
    // Temps de reception
    clock_cycles[1] = ClockCycles();
    
    return (&event);
}