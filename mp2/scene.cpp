#include "image.h"
#include "scene.h"

void Scene::copy(Scene const &other){
	max = other.max;
	xcoordinate = new int[max];
	ycoordinate = new int[max];
	imageArray = new Image *[max];
	for(int i = 0; i<other.max; i++){
	 if(other.imageArray[i] != NULL){		
		imageArray[i] = new Image(*other.imageArray[i]);
		xcoordinate[i] = other.xcoordinate[i];
		ycoordinate[i] = other.ycoordinate[i];
	 }
	 else{
		xcoordinate[i] = NULL;
		ycoordinate[i] = NULL;
		imageArray[i] = NULL;
	 }	
	}
}

void Scene::clear(){
	delete [] ycoordinate;
	ycoordinate = NULL;
	delete [] xcoordinate;
	xcoordinate = NULL;
	for(int i = 0; i<max; i++){
		if(imageArray[i] != NULL){
			delete imageArray[i];
			imageArray[i] = NULL;
		}
	}
	delete [] imageArray;
	imageArray = NULL;
}

Scene::Scene(int max){
	this->max = max;	
	imageArray = new Image *[max];
	xcoordinate = new int[max];
	ycoordinate = new int[max];
	for(int i = 0; i<max; i++){
		imageArray[i] = NULL;
		xcoordinate[i] = NULL;
		ycoordinate[i] = NULL;
	}	
	
}

Scene::Scene(Scene const & source){
	copy(source);

}

Scene const & Scene::operator=(Scene const & source){
	if(this != &source){
		clear();
		copy(source);
	}
	return *this;

}

Scene::~Scene(){
	clear();
}

void Scene::changemaxlayers(int newmax){
	if(newmax == max) return; 
	if(newmax > max){
		Image ** tmp = new Image *[newmax];
			for(int i = 0; i<max; i++){
				tmp[i] = imageArray[i];
			}
			for(int i = max; i<newmax; i++){
				tmp[i] = NULL;
			}
		this->imageArray = tmp;
	}
	if(newmax < max){
		bool imagelost = false;
		for(int i = newmax; i<max; i++){
			if(imageArray[i] != NULL) imagelost = true;
		}
		if(!imagelost){
			Image ** tmp = new Image *[newmax];
			for(int i = 0; i<newmax; i++){
				tmp[i] = imageArray[i];
			}
			this->imageArray = tmp;
		}
		if(imagelost) std::cout<<"invalid newmax"<<std::endl;
	}
}

void Scene::addpicture(const char* FileName, int index, int x, int y){
	if(index<0 || index>max) {
		std::cout<<"index out of bounds"<<std::endl; 
		return;
	}
	if(imageArray[index] != NULL){
		delete imageArray[index];
	}
	this->imageArray[index] = new Image();
	this->imageArray[index]->readFromFile(FileName);
	this->xcoordinate[index] = x;
	this->ycoordinate[index] = y;
}

void Scene::changelayer(int index, int newindex){
	if(index==newindex) return; 
	if(index<0 || index>max) std::cout<<"invalid index"<<std::endl;
	if(newindex<0 || newindex>max) std::cout<<"invalid index"<<std::endl;
	if(imageArray[newindex] != NULL){
		delete imageArray[newindex];
		imageArray[index] = NULL;
	}
	imageArray[newindex] = imageArray[index];
	xcoordinate[newindex] = xcoordinate[index];
	ycoordinate[newindex] = ycoordinate[index];
	imageArray[index] = NULL;
}

void Scene::translate(int index, int xcoord, int ycoord){
	if(index<0 || index>max) std::cout<<"invalid index"<<std::endl;
	if(imageArray[index] == NULL) std::cout<<"invalid index"<<std::endl;
	xcoordinate[index] = xcoord;
	ycoordinate[index] = ycoord;
}

void Scene::deletepicture(int index){
	if(index>max || index<0) std::cout<<"invalid index"<<std::endl;
	if(imageArray[index] == NULL) std::cout<<"invalid index"<<std::endl;
	delete this->imageArray[index];
	imageArray[index] = NULL;

}

Image* Scene::getpicture(int index) const{
	if(index>max || index<0) std::cout<<"invalid index"<<std::endl;
	Image * x = imageArray[index];
	return x;

}

Image Scene::drawscene() const{
	Image result;
	int height = 0;
	int width = 0;
	for(int i = 0; i<max; i++){
	if(imageArray[i] != NULL){
		if((xcoordinate[i] + imageArray[i]->width()) > width) width = xcoordinate[i] + imageArray[i]->width();  
		if((ycoordinate[i] + imageArray[i]->height()) > height) height = ycoordinate[i] + imageArray[i]->height();  
	}
	}
	result.resize(width, height);
	for(int i = 0; i<max; i++){
	 if(imageArray[i] != NULL){
	int tgtW = imageArray[i]->width();
	int tgtH = imageArray[i]->height();
	 for(int x = 0; x<tgtW; x++){
	  for(int y = 0; y<tgtH; y++){
		result.operator()(x + xcoordinate[i], y + ycoordinate[i])->red = imageArray[i]->operator()(x,y)->red;
		result.operator()(x + xcoordinate[i], y + ycoordinate[i])->green = imageArray[i]->operator()(x,y)->green;
		result.operator()(x + xcoordinate[i], y + ycoordinate[i])->blue = imageArray[i]->operator()(x,y)->blue;
		result.operator()(x + xcoordinate[i], y + ycoordinate[i])->alpha = imageArray[i]->operator()(x,y)->alpha;
	   }
	  }
	 }
	}
	return result;
}

