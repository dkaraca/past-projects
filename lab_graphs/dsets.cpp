/* Your code here! */

#include "dsets.h"
#include <iostream>

using namespace std;

void DisjointSets::addelements(int num){
	for(int i = 0; i<num; i++)
		trees.push_back(-1);
}

int DisjointSets::find(int elem){
	if(trees[elem] <0)
		return elem;

	else{
		trees[elem] = find(trees[elem]);
		return trees[elem];
	}

}

void DisjointSets::setunion(int a, int b){
	int r1 = find(a);
	int r2 = find(b);
	
	if(r1 == r2) return;
	else{
		int size = trees[r1] + trees[r2];
		if(trees[r1] <= trees[r2]){
			trees[r2] = r1;
			trees[r1] = size;
		}
		else{
			trees[r1] = r2;
			trees[r2] = size;
		}
	}

}
