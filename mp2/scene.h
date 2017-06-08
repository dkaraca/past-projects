#ifndef SCENE_H 
#define SCENE_H
#include "png.h"
#include "image.h"
#include <ostream>
#include <cstdint>

class Scene{

 public:
	Scene(int max);
	~Scene();
	Scene(Scene const &source);
	Scene const & operator=(Scene const &source);
	void changemaxlayers(int newmax);
	void addpicture(char const *FileName, int index, int x, int y);
	void changelayer(int index, int newindex);
	void translate(int index, int xcoord, int ycoord);
	void deletepicture(int index);
	Image* getpicture(int index) const;
	Image drawscene() const;
	int max;
	Image **imageArray;
private:
	int *xcoordinate;
	int *ycoordinate;
	void copy(Scene const &other);
	void clear();
};

#endif

