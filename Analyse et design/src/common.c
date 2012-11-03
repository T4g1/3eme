#ifndef NOT_REALENV
#include "lib/pioD48_qnx_v2.2.h"
#include "lib/appio.h"
#endif

#include <time.h>           /* nanosleep */
#include <fcntl.h>          /* O_RDWR */
#include <stdio.h>

#include "common.h"
#include "BitManager.h"     /* Manipulation et interrogation des station via des entiers */
#include "IOManager.h"      /* Manipulation de bit sur des variables entiere */

int fd;


/**
 * Initialise la connexion a la station
 */
void initLink()
{
#ifndef PROFIBUS
    #ifdef NOT_REALENV
    write(1, "O_RDWR", 6);
    #else
    fd = open("/dev/pio_d48", O_RDWR);
    #endif
#else
    short status;
    
    #ifdef NOT_REALENV
    write(1, "INIT", 4);
    #else
    IO_Init(1, &status);
    #endif
#endif
}


/**
 * Termine la connexion a la station
 */
void closeLink()
{
#ifndef PROFIBUS
    close(fd);
#else
    short status;
    
    #ifdef NOT_REALENV
    write(1, "EXIT", 4);
    #else
    IO_Exit(1, &status);
    #endif
#endif
}

/**
 * Lit la valeur des capteurs
 *
 * @param bit           Capteurs que l'on veut
 * @return              Valeur du capteur demande
 */
int getCapteur(int bit)
{
#ifndef PROFIBUS
    int capteurs = readStation(fd);
#else
    int capteurs = readPROFIBUS();
#endif
    
    return getBit(capteurs, bit);
}

/**
 * Modifie la valeur de l'actuateurs donne
 *
 * @param bit           Actuateur que l'on veut modifier
 * @param value         Valeur de l'actuateur
 */
void setActuateur(int bit, int value)
{
    static int actuateurs = 0;
   
    setBit(&actuateurs, bit, value);
    
#ifndef PROFIBUS
    writeStation(fd, actuateurs);
#else
    writePROFIBUS(actuateurs);
#endif
}

/**
 * Attend que le capteur prenne la valeur demandee
 *
 * @param bit       Capteurs que l'on surveille
 * @param value     Valeur que l'on attend
 */
void wait(int bit, int value)
{
    while(getCapteur(bit) != value)
        waitTime(10);
}

/**
 * Attend un certain temps donne
 *
 * @param milisec     Milliseconde a attendre
 */
int waitTime(unsigned long milisec)
{
    struct timespec req= {0};
    
    time_t sec = (int)(milisec / 1000);
    milisec = milisec - (sec * 1000);
    
    req.tv_sec = sec;
    req.tv_nsec = milisec * 1000000L;
    
    while(nanosleep(&req, &req) == -1)
        continue;
     
    return 1;
}

/**
 * Prepare l'armement du signal
 *
 * @param handler       Fonction handler
 */
void initSignal(void (*handler)())
{
    struct sigaction act;
    sigset_t set;
    // Arme le SIGINT
    sigemptyset(set);
    sigaddset(set, SIGINT);
    
    act->sa_flags = 0;
    act->sa_mask = *set;
    act->sa_handler = handler;
    sigaction(SIGINT, &act, NULL);
}

/**
 * Ferme le programme proprement
 *
 * @param signo     Numero de l'intteruption
 */
void handlerEnd(int signo)
{
    closeLink();
    
    // Detruit les mutex
    pthread_mutex_destroy(&mutex_received);
    pthread_mutex_destroy(&mutex_give);
}

/**
 * Acces a pieceReceived
 */
int getPieceReceived()
{
    int temp;
    
    pthread_mutex_lock(&mutex_received);
    temp = pieceReceived;
    pthread_mutex_unlock(&mutex_received);
    
    return temp;
}
void setPieceReceived(int value)
{
    pthread_mutex_lock(&mutex_received);
    pieceReceived = value; 
    pthread_mutex_unlock(&mutex_received);
}

/**
 * Acces a canGivePiece
 */
int getCanGivePiece()
{
    int temp;
    
    pthread_mutex_lock(&mutex_give);
    temp = canGivePiece;
    pthread_mutex_unlock(&mutex_give);
    
    return temp;
}
void setCanGivePiece(int value)
{
    pthread_mutex_lock(&mutex_give);
    canGivePiece = value; 
    pthread_mutex_unlock(&mutex_give);
}