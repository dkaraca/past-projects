#include "crrbinomial.h"
#include <time.h>
#include <limits>
using namespace std;
int main(){
typedef std::numeric_limits< double > dbl;
ofstream results;
results.open("results.txt");
results<<"Question 2:"<<endl;
results.precision(dbl::max_digits10);

CRRBinomial b;
double o;
clock_t t;
for(int i =0; i<50; i++){
	t = clock();
	o = b.Binomial('C',100,1,100,0.2,0.05,0.04,i,'E');
	t = clock()-t;	
	results<<i<<","<<o<<","<<t<<endl;
}
for(int i = 50; i<100; i=i+5){
	t = clock();
	o = b.Binomial('C',100,1,100,0.2,0.05,0.04,i,'E');
	t = clock()-t;	
	results<<i<<","<<o<<","<<t<<endl;
}
for(int i = 100; i<1000; i = i+25){
	t = clock();
	o = b.Binomial('C',100,1,100,0.2,0.05,0.04,i,'E');
	t = clock()-t;	
	results<<i<<","<<o<<","<<t<<endl;
}
for(int i = 1000; i<2000; i = i+100){
	t = clock();
	o = b.Binomial('C',100,1,100,0.2,0.05,0.04,i,'E');
	t = clock()-t;	
	results<<i<<","<<o<<","<<t<<endl;
}
for(int i = 2000; i<=5000; i = i+1000){
	t = clock();
	o = b.Binomial('C',100,1,100,0.2,0.05,0.04,i,'E');
	t = clock()-t;	
	results<<i<<","<<o<<","<<t<<endl;
}

results<<"Question 4:"<<endl;
CRRBinomial american;
double out;

double Z[13][610];


double S[13][610];

int c = 0;


results<<"S* values, q = 0"<<endl;

for(int i = 0; i<=12; i++){
	results<<"i = "<<i<<endl;
	c = 0;
	for(double j = 70; j<=130; j=j+0.1){
		out = american.Binomial('P',100,(double) i/12, j, 0.2,0.05,0,500, 'A');
		Z[i][c] = out;
		S[i][c] = max(0.0,100-j);
		c++;
	}
	for(int k = 0; k<610; k++){
		if(Z[i][k]>(S[i][k]+0.005)){
			results<<(70+(k*0.1))<<endl;
			break;
		}
	}
}

for(int i = 0; i<610; i++)
	results<<Z[12][i]<<","<<S[12][i]<<endl;

results<<"S* values, q = 0.04"<<endl;

for(int i = 0; i<=12; i++){
	results<<"i = "<<i<<endl;
	c = 0;
	for(double j = 70; j<=130; j=j+0.1){
		out = american.Binomial('P',100,(double) i/12, j, 0.2,0.05,0.04,500, 'A');
		Z[i][c] = out;
		S[i][c] = max(0.0,100-j);
		c++;
	}
	for(int k = 0; k<610; k++){
		if(Z[i][k]>(S[i][k]+0.005)){
			results<<(70+(k*0.1))<<endl;
			break;
		}
	}
}

for(int i = 0; i<610; i++)
	results<<Z[12][i]<<","<<S[12][i]<<endl;

double Q[13][1210];
double W[13][1210];

for(int i = 0; i<=12; i++){
	results<<"i = "<<i<<endl;
	c = 0;
	for(double j = 40; j<=160; j=j+0.1){
		out = american.Binomial('C',100,(double) i/12, j, 0.2,0.05,0.04,500, 'A');
		Q[i][c] = out;
		W[i][c] = max(0.0,j-100);
		c++;
	}
	for(int k = 0; k<610; k++){
		if(Q[i][k]<(W[i][k]+0.005)){
			results<<(70+(k*0.1))<<endl;
			break;
		}
	}
}

for(int i = 0; i<610; i++)
	results<<Z[12][i]<<","<<S[12][i]<<endl;

results<<"S* values, q = 0.04"<<endl;

for(int i = 0; i<=12; i++){
	results<<"i = "<<i<<endl;
	c = 0;
	for(double j = 40; j<=160; j=j+0.1){
		out = american.Binomial('C',100,(double) i/12, j, 0.2,0.05,0.08,500, 'A');
		Q[i][c] = out;
		W[i][c] = max(0.0,j-100);
		c++;
	}
	for(int k = 0; k<1210; k++){
		if(Q[i][k]<(W[i][k]+0.005)){
			results<<(70+(k*0.1))<<endl;
			break;
		}
	}
}

for(int i = 0; i<610; i++)
	results<<Z[12][i]<<","<<S[12][i]<<endl;


results.close();

return 0;
}
