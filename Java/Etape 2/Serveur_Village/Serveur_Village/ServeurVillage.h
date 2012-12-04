#ifndef __SERVEURVILLAGE__
#define __SERVEURVILLAGE__

#include <pthread.h>
#include <stack>

#include "../lib/CSVParser.h"
#include "../lib/common.h"
#include "../lib/network.h"

using namespace std;

typedef struct Client {
	string ip;
	string login;
	SOCKET urgence;
} Client;

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

	static void processRequest(SOCKET client, string request);
	static bool isEnPause();
	static void setPause(bool pause);
	static void sendCatalogue(SOCKET client);
	static void sendAction(SOCKET client);
	static vector<string> getConnectedLogin();
	static void sendUrgence(string txt);
	
	static map<int, Action>* l_action;
	static map<SOCKET, Client>* l_client;
	static stack<SOCKET>* l_clientSocket;

	pthread_t idThServeurFHMP;
	pthread_t idThServeurFHMPA;

	// Gestion du pool de client
	static pthread_mutex_t* mutex_pause;
	static pthread_mutex_t* mutex_pool;
	static pthread_mutex_t* mutex_info;
	static pthread_cond_t* cond_pool;

	static bool pause;
	static CSVParser* config;
	static CSVParser* materiel;
	static CSVParser* users;
};

#endif