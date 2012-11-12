#ifndef __COMMON__
#define __COMMON__

#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <time.h>
#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <netdb.h>
#include <string.h>
#include <termios.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <ncurses.h>


#define BYTE    unsigned char

#define FILE_PETRA_IN   "/dev/capteursPETRA"
#define FILE_PETRA_OUT  "/dev/actuateursPETRA"

#define ADDR_SUPERVISEUR            "192.168.1.1"
#define PORT_VERS_SUPERVISEUR       27015
#define PORT_DEPUIS_SUPERVISEUR     27016

#define BUFFER_SIZE     100

struct CAPTEURS {
    unsigned L1 : 1;
    unsigned L2 : 1;
    unsigned T  : 1;
    unsigned S  : 1;
    unsigned CS : 1;
    unsigned AP : 1;
    unsigned PP : 1;
    unsigned DE : 1;
};

struct ACTUATEURS  {
    unsigned CP : 2;
    unsigned C1 : 1;
    unsigned C2 : 1;
    unsigned PV : 1;
    unsigned PA : 1;
    unsigned AA : 1;
    unsigned GA : 1;
};

int initSend(int *sock, char* addr, int port);
int initListen(int *s, struct sockaddr_in *addr, int port);
int SendTo (int sock, const char *host, unsigned short int port, const char *buffer, int size);

void setActuateur(int bit, int value);
BYTE getCapteur();

int getBit(int value, int bit);
void setBit(int* value, int bit, int bitValue);

int kbhit(void);

#endif