#ifndef CRRBINOMIAL_H 
#define CRRBINOMIAL_H
#include <iostream>
#include <fstream>
#include <cstdint>
#include "math.h"
#include "time.h"

class CRRBinomial{

 public: 
	double Binomial(char Option, double K, double T, double S, double sigma, double r, double q, double N, char Exercise);
	
 private:
	
};



#endif
