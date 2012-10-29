#ifndef NOT_REALENV
#include<piod_qnx_v2.2.h>
#include<appio.h>
#endif

#include <time.h>           /* nanosleep */

#include "BitManager.h"     /* Manipulation et interrogation des station via des entiers */
#include "IOManager.h"      /* Manipulation de bit sur des variables entiére */

int fd;

int actuateurs = 0;


/**
 * Initialise la connexion à la station
 */
void init()
{
#ifndef PROFIBUS
	#ifdef NOT_REALENV
    write(1, "O_RDWR", 6);
    #else
    fd = open("/dev/pio_d48", O_RDWR);
    #endif
#else
    int status;
    
    #ifdef NOT_REALENV
    write(1, "INIT", 4);
    #else
    IO_Init(1, &status);
    #endif
#endif
}


/**
 * Termine la connexion à la station
 */
void close()
{
#ifndef PROFIBUS
    close(fd);
#else
    int status;
    
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
 * @return              Valeur du capteur demandé
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
 * Modifie la valeur de l'actuateurs donné
 *
 * @param bit           Actuateur que l'on veut modifier
 * @param value         Valeur de l'actuateur
 */
void setActuateur(int bit, int value)
{
    setBit(&actuateurs, bit, value);
    
#ifndef PROFIBUS
    writeStation(fd, actuateurs);
#else
    writePROFIBUS(actuateurs);
#endif
}

/**
 * Attend que le capteur prenne la valeur demandée
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
 * Attend un certain temps donné
 *
 * @param millisecondes     Milliseconde à attendre
 */
int waitTime(int millisecondes)
{
    struct timespec time;
    time.tv_sec = 0;
    time.tv_nsec = millisecondes * 1000000;
    
    nanosleep(&time, NULL);
    
    return 0;
}