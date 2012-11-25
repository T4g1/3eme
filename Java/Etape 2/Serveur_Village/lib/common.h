#ifndef __COMMON__
#define __COMMON__

#include <string>
#include <iostream>
#include <map>
#include <vector>
#include <ctime>
#include <sstream>

#define DATE_INVALIDE	-1
#define DATE_ANTERIEURE	-2

using namespace std;

typedef struct Action {
	string action;	// Action
	string article;	// Article demande
	time_t date;	// Date de realisation
	string user;	// Login du createur
} Action;

void pause();
time_t askDate();
string itoa(int value);
int nfind(string str, string needle, int n);
string getCommande(string request);
string getArg(string request, int index);

/** Donne les clé d'une map donnée */
template<typename T1, typename T2>
vector<T1> getKeyFrom(map<T1, T2> elements)
{
	std::vector<T1> l_key;

	for(map<T1, T2>::iterator it = elements.begin();
		it != elements.end(); ++it)
	{
		l_key.push_back(it->first);
	}

	return l_key;
}

#endif