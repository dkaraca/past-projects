#include "crrbinomial.h"
#include <vector>
using namespace std;

double CRRBinomial::Binomial(char Option, double K, double T, double S, double sigma, double r, double q, double N, char Exercise){



if(N == 0) return S;
 else{

 vector<vector<double>>stock(N+1,vector<double>(N+1));

 double deltat =  T/N;
 double u =  exp(sigma * sqrt(deltat));
 double d =  1 / u;
 double p =  (exp((r-q) * deltat) - d) / (u - d);

 int size = stock.size();

 for(int i = 0; i<size ; i++){
  for(int j = 0; j<size; j++){
	stock[i][j] = S * pow(u,j) * pow(d,(i-j));
  }
 }
 vector<vector<double>>z(N+1,vector<double>(N+1));
 if(Exercise == 'E'){
	
	
	for(int i = N; i>=0; i--){
	 for(int j = N; j>=0; j--){
		if(i == N){
		 if(Option == 'C')
			z[i][j] = max<double>(0.0,stock[i][j]-K);
		 else if(Option == 'P') 
			z[i][j] = max<double>(0.0,K-stock[i][j]);
		}
		else 
			z[i][j] = exp((-r)*deltat)*(p*z[i+1][j+1] + (1-p)*z[i+1][j]);
	 }
  	}
 }

 else{
	vector<vector<double>>f(N+1,vector<double>(N+1));
	if(Option == 'C'){
		for(int i = 0; i<size; i++)
		 for(int j = 0; j<size; j++)
			f[i][j] = max<double>(0,stock[i][j]-K);
	}
	if(Option == 'P'){
		for(int i = 0; i<size; i++)
		 for(int j = 0; j<size; j++)
			f[i][j] = max<double>(0,K-stock[i][j]);
	}
	
	for(int j = 0; j<=N; j++)
		z[N][j] = f[N][j];
	for(int j = 0; j<N; j++){
		 z[N-1][j] = exp((-r)*deltat)*(p*z[N][j+1] + (1-p)*z[N][j]);
	}

	for(int i = N-2; i>=0; i--){
	 for(int j = 0; j<=i; j++)
		z[i][j] = exp((-r)*deltat)*(p*max<double>(f[i+1][j+1],z[i+1][j+1]) + (1-p)*max<double>(f[i+1][j],z[i+1][j]));
	}
 }

 return z[0][0];

 }
}
