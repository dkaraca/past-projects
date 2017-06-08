/**
 * @file kdtree.cpp
 * Implementation of KDTree class.
 */
#include <math.h>
template<int Dim>
bool KDTree<Dim>::smallerDimVal(const Point<Dim> & first, const Point<Dim> & second, int curDim) const
{
    /**
     * @todo Implement this function!
     */
	
	if(first[curDim] == second[curDim]) 
		return first<second;
	return first[curDim]<second[curDim];
	
}


template<int Dim>
bool KDTree<Dim>::shouldReplace(const Point<Dim> & target, const Point<Dim> & currentBest, const Point<Dim> & potential) const
{
    /**
     * @todo Implement this function!
     */
	int dist1 = 0;
	int dist2 = 0;
	for(int i = 0; i<Dim; i++){
		dist1+= pow(potential[i]-target[i],2);
		dist2+= pow(currentBest[i]-target[i],2);
	}
	if(dist1==dist2)
		return potential.operator<(currentBest);
	return dist1<dist2;
}

template<int Dim>
KDTree<Dim>::KDTree(const vector< Point<Dim>> & newPoints)
{
    /**
     * @todo Implement this function!
     */
	points = newPoints;
	
	if(points.size()==0)
		return;
	
	else
		KDTree_helper(0,points.size()-1,0);
}

template<int Dim>
void KDTree<Dim>::KDTree_helper(int left, int right, int curDim){

	if(left == right) 
		return;
	else{
		int median = (left+right)/2;
		quickselect(left,right,median,curDim);
		if(right>median)
			KDTree_helper(median+1,right,(curDim+1)%Dim);
		if(left<median)
			KDTree_helper(left,median-1,(curDim+1)%Dim);
		
	}
}

template<int Dim>
int KDTree<Dim>::partition(int left, int right, int pivotInd, int curDim){
	Point<Dim> pivotVal = points[pivotInd];
	Point<Dim> tmp = points[right];
	points[right] = points[pivotInd];
	points[pivotInd] = tmp;
	
	int storeInd = left;
	for(int i = left; i<right; i++){
		if(points[i] == pivotVal || smallerDimVal(points[i],pivotVal,curDim)){
			tmp = points[i];
			points[i] = points[storeInd];
			points[storeInd] = tmp;
			storeInd++;
		}
	}
	tmp = points[right];
	points[right] = points[storeInd];
	points[storeInd] = tmp;
	return storeInd;
}

template<int Dim>
void KDTree<Dim>::quickselect(int left, int right, int k, int curDim){
	while(left!=right){
		int pivotInd = partition(left,right,k,curDim);
		if(pivotInd == k)
			return;
		else if(k<pivotInd)
			right = pivotInd-1;
		else 
			left = pivotInd+1;
	}
}

template<int Dim>
Point<Dim> KDTree<Dim>::findNearestNeighbor(const Point<Dim> & query) const
{
    /**
     * @todo Implement this function!
     */

	Point<Dim> retVal = findNearestNeighborHelper(query,0,points.size()-1,0,points[(points.size()-1)/2]);

   return retVal;
}

template<int Dim>
Point<Dim> KDTree<Dim>::findNearestNeighborHelper(const Point<Dim> & query, int left, int right, int curDim, const Point<Dim> &curBest) const{

	Point<Dim> retVal = curBest;
	bool leftTarget = true;
	if(left == right){
		if(shouldReplace(query,curBest,points[left])){
			retVal = points[left];
			return retVal;
		}
	return retVal;
	}
	int med = (left+right)/2;
	//int med2;

	if(smallerDimVal(query,points[med],curDim) && left<med){
		leftTarget = true;
		//med2 = (right+med)/2;
		retVal = findNearestNeighborHelper(query,left,med-1,(curDim+1)%Dim,curBest);
	}

	if(smallerDimVal(points[med],query,curDim) && right>med){
		leftTarget = false;
		//med2 = (left+med-1)/2;
		retVal = findNearestNeighborHelper(query,med+1,right,(curDim+1)%Dim,curBest);
	}
	 
	if(shouldReplace(query,retVal,points[med]))
		retVal = points[med];
	
	Point<Dim> mid = points[med];
	
	int dist = 0;
	for(int i = 0; i<Dim; i++)
		dist+=pow(query[i]-retVal[i],2);
	
	if(pow(mid[curDim]-query[curDim],2)<=dist){
		if(!leftTarget && left<med)
			retVal = findNearestNeighborHelper(query,left,med-1,(curDim+1)%Dim,retVal);
		if(leftTarget && right>med)
			retVal = findNearestNeighborHelper(query,med+1,right,(curDim+1)%Dim,retVal);
	}
	return retVal;
}
