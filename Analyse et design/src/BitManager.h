/**
 * Permet d'accéder a n'importe quel bit d'une variable de type
 * entiére (int) aussi bien en lecture qu'en écriture.
 */
#ifndef __BITMANAGER__
#define __BITMANAGER__

int getBit(int value, int bit);
int setBit(int* value, int bit, int bitValue);

#endif