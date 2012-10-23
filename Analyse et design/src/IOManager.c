#include<piod_qnx_v2.2.h>
#include<appio.h>

/**
 * Lit les capteurs pour la station 1
 *
 * @return          Valeur des capteurs
 */
int read()
{
    int value;
    int byte0, byte1;
    
    IO_RefreshOutput(1, &status);
    
    // Récupére les deux bytes de données
    IO_ReadIByte(1, 64, 0, 1, &byte0, &status);
    IO_ReadIByte(1, 64, 1, 1, &byte1, &status);
    
    // Concaténe les deux bytes
    value = (byte1 << 8) + byte0;
    
    return value;
}

/**
 * Ecrit les actuateurs pour la station 1
 *
 * @param value     Nouvelle valeur des actuateurs
 */
void write(int value)
{
    int status;
    
    int byte0 = value & 0xff;                   // Valeur que l'on veut écrire sur le byte 0
    int byte1 = (value >> 8) & 0xff;            // Valeur que l'on veut écrire sur le byte 1
    
    IO_WriteQByte(1, 64, 0, 1, &byte0, &status);
    IO_WriteQByte(1, 64, 1, 1, &byte1, &status);
    IO_RefreshOutput(1, &status);
}


/**
 * Lit les capteurs pour les station 2, 3 et 4
 *
 * @param fd        Descripteur correspondant a la station voulue
 *
 * @return          Valeur des capteurs
 */
int read(int fd)
{
    int capteurs = 0;
    
    devctl(fd, READ_CAPTEUR, &capteurs, sizeof(capteurs), NULL);
    
    return capteurs;
}

/**
 * Ecrit les actuateurs pour les station 2, 3 et 4
 *
 * @param fd        Descripteur correspondant a la station voulue
 * @param value     Nouvelle valeur des actuateurs
 */
void write(int fd, int value)
{
    devctl(fd, WRITE_ACTUATEUR, &value, sizeof(value), NULL);
}