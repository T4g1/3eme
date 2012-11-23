#include <iostream>

#include "ApplicMateriel.h"

using namespace std;


int main(void)
{
	ApplicMateriel* applicMateriel = new ApplicMateriel();
	
	applicMateriel->start();

	system("pause");
	return EXIT_SUCCESS;
}