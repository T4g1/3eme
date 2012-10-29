/**
 * Donne des fonctions utilitaires de plus haut niveau
 * pour manipuler le dispositif FESTO
 */
#ifndef __COMMON__
#define __COMMON__

#define ON      1
#define OFF     0

#define TRUE    1
#define FALSE   0

void init();
void close();

int getCapteur(int bit);
void setActuateur(int bit, int value);

void wait(int bit, int value);
int waitTime(int time);

#endif