#ifndef __CSVPARSER__
#define __CSVPARSER__

#include <map>
#include <string>
#include <vector>

using namespace std;


class CSVParser
{
public:
	CSVParser();
	~CSVParser();

	void load(string filename);
	
	string get(string key);
	int getInt(string key);
	vector<string> getKey();

private:
	map<string, string> elements;
};

#endif