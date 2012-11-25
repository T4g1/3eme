#include "CSVParser.h"

#include "common.h"

#include <iostream>
#include <fstream>


CSVParser::CSVParser() : elements()
{
	filename = "out.csv";
}

CSVParser::~CSVParser()
{
}

/** Charge le fichier CSV donné */
void CSVParser::load(string filename)
{
	this->filename = filename;

	// Ouverture du fichier
	ifstream fichier(filename, ios::in);
	if(!fichier) return;

	string line, key, value;
	while(!fichier.eof()) {
		getline(fichier, line);

		// Cherche le délimiteur
		int pos = line.find('=');
		if(pos < 0) continue;

		key = line.substr(0, pos);
		value = line.substr(pos + 1, line.size());

		elements[key] = value;
	}

	fichier.close();
}

/** Donne l'élement voulu */
string CSVParser::get(string key)
{
	return elements[key];
}

/** Donne l'élement voulu en entier */
int CSVParser::getInt(string key)
{
	int value = atoi(elements[key].c_str());

	return value;
}

/** Donne les clés existante */
vector<string> CSVParser::getKey()
{
	return getKeyFrom(elements);
}

/** Insére une nouvelle valeur dans le fichier */
void CSVParser::set(string key, string value)
{
	elements[key] = value;
	save();
}

void CSVParser::save()
{
	ofstream fichier(filename, ios::out | ios::trunc);
    if(!fichier) return;

	map<string, string>::iterator it;
	for(it=elements.begin(); it!=elements.end(); it++) {
		fichier << it->first << "=" << it->second << endl;
	}
 
    fichier.close();
}