#ifndef __SERVEURVILLAGE__
#define __SERVEURVILLAGE__

#include <pthread.h>
#include <string>
#include <map>
#include <stack>
#include <vector>

#include "../lib/network.h"

#define PORT_ADMIN		27015
#define PORT_VILLAGE	27016

#define POOL_SIZE		5

using namespace std;


class ServeurVillage
{
public:
	ServeurVillage();
	~ServeurVillage();

	void start();
	void wait();

private:
	static void* ThServeurFHMP(void* data);
	static void* ThClientFHMP(void* data);
	static void* ThServeurFHMPA(void* data);

	static map<string, string>* l_clients;
	static stack<SOCKET>* l_clientSocket;

	pthread_t idThServeurFHMP;
	pthread_t idThServeurFHMPA;

	// Gestion du pool de client
	static pthread_mutex_t* mutex_pool;
	static pthread_cond_t* cond_pool;
};

#endif