#ifndef NOT_REALENV
#include "lib/pioD48_qnx_v2.2.h"
#include "lib/appio.h"
#endif

/**
 * Lit les capteurs pour la station 1
 *
 * @return          Valeur des capteurs
 */
int readPROFIBUS()
{
    int value;
  	BYTE byte0 = 0, byte1 = 0;
    short status = 0;
    
    #ifdef NOT_REALENV
    write(1, "IO_ReadIByte", 12);
    #else
    IO_RefreshInput(1, &status);
    
    // Rcupre les deux bytes de donnes
    IO_ReadIByte(1, 64, 0, 1, &byte0, &status);
    IO_ReadIByte(1, 64, 1, 1, &byte1, &status);
    #endif
    
    // Concatne les deux bytes
    value = (byte1 << 8) + byte0;
    
    return value;
}

/**
 * Ecrit les actuateurs pour la station 1
 *
 * @param value     Nouvelle valeur des actuateurs
 */
void writePROFIBUS(int value)
{
    short status;
    
    BYTE byte0 = (unsigned char)(value & 0xff);            // Valeur que l'on veut crire sur le byte 0
    BYTE byte1 = (unsigned char)((value >> 8) & 0xff);     // Valeur que l'on veut crire sur le byte 1
    
    #ifdef NOT_REALENV
    write(1, "IO_WriteQByte", 14);
    #else
    
    IO_RefreshOutput(1, &status);
    IO_WriteQByte(1, 64, 0, 1, &byte0, &status);
    IO_WriteQByte(1, 64, 1, 1, &byte1, &status);
    IO_RefreshOutput(1, &status);
    #endif
}


/**
 * Lit les capteurs pour les station 2, 3 et 4
 *
 * @param fd        Descripteur correspondant a la station voulue
 *
 * @return          Valeur des capteurs
 */
int readStation(int fd)
{
    int capteurs = 0;
    
    #ifdef NOT_REALENV
    write(1, "READ_CAPTEUR", 12);
    #else
    devctl(fd, READ_CAPTEUR, &capteurs, sizeof(capteurs), NULL);
    #endif
    
    return capteurs;
}

/**
 * Ecrit les actuateurs pour les station 2, 3 et 4
 *
 * @param fd        Descripteur correspondant a la station voulue
 * @param value     Nouvelle valeur des actuateurs
 */
void writeStation(int fd, int value)
{
    #ifdef NOT_REALENV
    write(1, "WRITE_ACTUATEUR", 15);
    #else
    devctl(fd, WRITE_ACTUATEUR, &value, sizeof(value), NULL);
    #endif
}