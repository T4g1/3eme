#ifndef __NETWORK__
#define __NETWORK__

#include <iostream>
#include <string>
#include <winsock2.h>

#define IP_SIZE		20

#define BUFFER_SIZE		500

typedef int socklen_t;

using namespace std;


int net_init();
void net_close();
int socketServer(int port);
int socketClient(char* ip, int port);
int accept(int socket, char* ip);
int send(int socket, string buffer);
int recv(int socket, string* buffer);

#endif