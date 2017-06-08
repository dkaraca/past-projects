/* Your code here! */

#ifndef MAZE_H
#define MAZE_H

#include <iostream>
#include <math.h>
#include <vector>
#include <string>
#include <sstream>
#include <queue>
#include "png.h"
#include "dsets.h"

using namespace std;

class SquareMaze{

	public:
		SquareMaze();
		
		void makeMaze(int width, int height);
		
		bool canTravel(int x, int y, int dir)const; 

		void setWall(int x, int y, int dir, bool exists);
		
		vector<int> solveMaze();
	
		PNG* drawMaze()const;

		PNG* drawMazeWithSolution();


	private:
		int h;
		
		int w;
		
		int exit;
vector<bool> downWalls;

		vector<bool> rightWalls;
		

		int position(int x, int y)const;
	
		int xCoord(int z)const;
			
		int yCoord(int z)const;

};

#endif
