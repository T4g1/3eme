#include <iostream>

#include "ServeurVillage.h"

using namespace std;


int main(void)
{
	ServeurVillage* serveurVillage = new ServeurVillage();

	serveurVillage->start();
	serveurVillage->wait();

	system("pause");
	return EXIT_SUCCESS;
}