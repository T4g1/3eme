// thread3.c
// 
// Last edit:   08 Octobre 2012
// Van Gysegem Thomas <thomasvangysegem@gmail.com>

#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <termios.h>
#include <curses.h>
#include <pthread.h>

#define NB_THREAD       3

void* threadBody(void);


int main ()
{
    int i, count;
    
    pthread_t handleThread[NB_THREAD];
    
    // Initialise les thread
    for(i=0; i<NB_THREAD; i++) {
        pthread_create(
                &handleThread[i], 
                NULL, 
                (void*(*)(void*))threadBody, 
                NULL
        );
    }
    
    // Attend la fin des thread
    for(i=0; i<NB_THREAD; i++) {
        pthread_join(handleThread[i], NULL);
    }
    
    return 1;
}

void* threadBody(void)
{
    printf("Van Gysegem Thomas et Delsaux Kevin\n");
}