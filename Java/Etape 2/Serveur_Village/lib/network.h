#ifndef __NETWORK__
#define __NETWORK__

#include <winsock2.h>
#include <stdio.h>
#include <string.h>

#define IP_SIZE		20

#define BUFFER_SIZE		50

typedef int socklen_t;


int net_init();
void net_close();
int socketServer(int port);
int socketClient(char* ip, int port);
int accept(int socket, char* ip);
int send(int socket, char* buffer, int size);
int recv(int socket, char* buffer);

#endif