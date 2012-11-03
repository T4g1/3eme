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

// Mutex de la station
pthread_mutex_t mutex_received;
pthread_mutex_t mutex_give;

int pieceReceived = FALSE;
int canGivePiece = FALSE;

void initLink();
void closeLink();

int getCapteur(int bit);
void setActuateur(int bit, int value);

void wait(int bit, int value);
int waitTime(unsigned long milisec);

void initSignal();
void handlerEnd(int signo);

#endif