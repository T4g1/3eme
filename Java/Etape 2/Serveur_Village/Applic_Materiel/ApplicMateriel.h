#ifndef __APPLICMATERIEL__
#define __APPLICMATERIEL__

#include <pthread.h>
#include <string>
#include <map>
#include <stack>
#include <vector>

#include "../lib/network.h"

#define PORT_VILLAGE	27016

using namespace std;


class ApplicMateriel
{
public:
	ApplicMateriel();
	~ApplicMateriel();

	void start();
private:
	bool connect();

	int socket;
};

#endif