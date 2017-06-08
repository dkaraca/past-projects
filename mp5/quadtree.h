// **************************************************************
// *		   
// *  quadtree.h
// *		   
// *  Quadtree class
// *		   
// *  CS 225 Spring 2008
// *		   
// **************************************************************

#ifndef QUADTREE_H
#define QUADTREE_H

#include "png.h"

class Quadtree
{
	public:
	
		Quadtree();
		
		Quadtree(PNG const &source, int resolution);
		
		Quadtree(Quadtree const &other);
		
		~Quadtree();
		
		Quadtree const& operator=(Quadtree const &other);
		
		void buildTree(PNG const &source, int resolution);
		
		RGBAPixel getPixel(int x, int y) const;
		
		PNG decompress() const;
		
		void clockwiseRotate();
		
		void prune(int tolerance);

		int pruneSize(int tolerance)const;

		int idealPrune(int numLeaves)const;

		

		
	
	
	private:
		
		

	// A simple class representing a single node of a Quadtree.
	// You may want to add to this class; in particular, it could probably
	// use a constructor or two...
	class QuadtreeNode
	{
		public:
		QuadtreeNode* nwChild;  // pointer to northwest child
		QuadtreeNode* neChild;  // pointer to northeast child
		QuadtreeNode* swChild;  // pointer to southwest child
		QuadtreeNode* seChild;  // pointer to southeast child

		RGBAPixel element;  // the pixel stored as this node's "data"
	};
	
	QuadtreeNode* root;    // pointer to root of quadtree
	
	int res;
	
	/**** Functions added for testing/grading                ****/
	/**** Do not remove this line or copy its contents here! ****/
	#include "quadtree_given.h"
		void clear(QuadtreeNode* r);
		
		Quadtree::QuadtreeNode* copy(const Quadtree::QuadtreeNode* c);

		QuadtreeNode* buildTreeHelper(const PNG& source, int xCoord, int yCoord, int distance)const;

		RGBAPixel getPixelHelper(QuadtreeNode* r, int xCoord, int yCoord, int distanceX, int distanceY, int distance)const;
		
		void decompressHelper(QuadtreeNode* r, PNG& image, int xCoord, int yCoord, int distance)const;
};

#endif
