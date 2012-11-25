#ifndef __APPLICMATERIEL__
#define __APPLICMATERIEL__

#include <pthread.h>
#include <stack>

#include "../lib/CSVParser.h"
#include "../lib/common.h"
#include "../lib/network.h"

using namespace std;

void* urgence(void* param);

class ApplicMateriel
{
public:
	ApplicMateriel();
	~ApplicMateriel();

	void start();
private:
	bool connect();
	bool doLogin(string login, string password);
	void showCatalogue(); 
	void showAction(); 
	void bmat(string action, string article, time_t date);
	void cmat(int id);
	void askmat(string name, string description, string marque, string prix, string accessoire);
	
	pthread_t idThUrgence;
	CSVParser config;
	SOCKET socket;
	map<string, int> l_materiel;
	map<int, Action> l_action;
};

#endif