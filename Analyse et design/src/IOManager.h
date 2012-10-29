/**
 * Permet de manipuler les données lues ou écrites sur n'importe
 * quelle station comme des entiers contenant chaque byte de donnée
 * concaténé les uns derriére les autres.
 *
 * Pour la station 1:
 *      (poid fort) /, /, Byte1, Byte 0 (poid failbe)
 *
 * Pour les station 2, 3 et 4:
 *      (poid fort) /, PA, PB, PC (poid failbe)
 */
#ifndef __IOMANAGER__
#define __IOMANAGER__

// Station 1
int readPROFIBUS();
void writePROFIBUS(int value);

// Station 2, 3 et 4
int readStation(int fd);
void writeStation(int fd, int value);

#endif