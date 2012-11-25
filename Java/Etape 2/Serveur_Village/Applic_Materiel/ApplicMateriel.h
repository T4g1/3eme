#ifndef __APPLICMATERIEL__
#define __APPLICMATERIEL__

#include <pthread.h>
#include <string>
#include <map>
#include <stack>
#include <vector>

#include "../lib/network.h"
#include "../lib/CSVParser.h"

using namespace std;


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
	void bmat(string action, int article, time_t date);
	void askmat(string name, string description, string marque, string prix, string accessoire);
	
	CSVParser config;
	SOCKET socket;
	map<string, int> l_materiel;
};

#endif