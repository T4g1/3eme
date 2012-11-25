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

	void set(string key, string value);
	void save();

private:
	string filename;
	map<string, string> elements;
};

#endif