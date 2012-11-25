#ifndef __APPLICMATERIEL__
#define __APPLICMATERIEL__

#include <pthread.h>
#include <string>
#include <map>
#include <stack>
#include <vector>
#include <iostream>
#include <sstream>

#include "../lib/network.h"
#include "../lib/CSVParser.h"
#include "../lib/common.h"

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
	void showAction(); 
	void bmat(string action, string article, time_t date);
	void cmat(int id);
	void askmat(string name, string description, string marque, string prix, string accessoire);
	
	CSVParser config;
	SOCKET socket;
	map<string, int> l_materiel;
	map<int, Action> l_action;
};

#endif