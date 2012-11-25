#include "common.h"


/** Attend que l'utilisateur soit pret */
void pause()
{
	string pause;

	system("pause");

	cout << endl << endl;
}

/** Demande une date a l'utilisateur */
time_t askDate()
{
	string input;
	time_t epoch, now;

	cout << "Entrez la date souhaitée (jj/mm/aaaa): ";
	cin >> input;

	// Extrait ce qui nous interesse de la date saisie
	int first = input.find("/");
	if(first < 0) return DATE_INVALIDE;

	int second = input.rfind("/");
	if(second < 0) return DATE_INVALIDE;
	
	int j = atoi(input.substr(0, first).c_str());
	int m = atoi(input.substr(first + 1, second).c_str());
	int a = atoi(input.substr(second + 1, input.length()).c_str());

	if(j <= 0 || m <= 0 || a <= 0) return DATE_INVALIDE;
	
	// COnvertir en timestamp
	time(&now);
	struct tm* tm = localtime(&now);
	tm->tm_mday = j;
	tm->tm_mon = m - 1;
	tm->tm_year = a - 1900;
	epoch = mktime(tm);

	if(epoch <= now) return DATE_ANTERIEURE;

	return epoch;
}

/** itoa avec des string */
string itoa(int value)
{
	stringstream str;
	str << value;

	return str.str();
}

/** Trouve le n-ieme needle dans le str donné */
int nfind(string str, string needle, int n)
{
	int pos = -1;
	do {
		pos = str.find(needle, pos + 1);
	} while(n--);

	return pos;
}

/** Donne la commande dans la requete recue */
string getCommande(string request)
{
	int pos = request.find(':');
	if(pos >= 0)
		return request.substr(0, pos);

	return request;
}

/** Donne l'argument demandé */
string getArg(string request, int index)
{
	int pos = request.find(':');
	if(pos >= 0) {
		string argList = request.substr(pos, request.length());
		int first = nfind(argList, ":", index);
		int last = nfind(argList, ":", index + 1);

		// Argument trouvé
		if(first >= 0) {
			// Ce n'est pas le dernier
			if(last >= 0)
				return argList.substr(first + 1, last - first - 1);
			
			// C'est le dernier
			return argList.substr(first + 1, argList.length());
		}

		return argList;
	}

	return "";
}