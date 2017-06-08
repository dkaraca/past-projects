#include "image.h"

void Image::flipleft(){


size_t tgtX = this->width();
size_t tgtY = this->height();
	for(size_t x = 0; x<tgtX/2; x++){
	 for(size_t y = 0; y<tgtY; y++){
		int srcY = y;
		int srcX = tgtX - x;
		int temp_red = this->operator()(x,y)->red;
		int temp_green = this->operator()(x,y)->green;
		int temp_blue = this->operator()(x,y)->blue;
		this->operator()(x,y)->red = this->operator()(srcX,srcY)->red;
		this->operator()(x,y)->green = this->operator()(srcX,srcY)->green;
		this->operator()(x,y)->blue = this->operator()(srcX,srcY)->blue;
		this->operator()(srcX,srcY)->red = temp_red; 
		this->operator()(srcX,srcY)->green = temp_green; 
		this->operator()(srcX,srcY)->blue = temp_blue; 
	 }
	}

}

void Image::adjustbrightness(int r, int g, int b){
	
	for(size_t x = 0; x<this->width(); x++){
	 for(size_t y = 0; y<this->height(); y++){
		int curr_red = this->operator()(x,y)->red;
		int curr_green = this->operator()(x,y)->green;
		int curr_blue = this->operator()(x,y)->blue;
		curr_red = curr_red + r;
		if(curr_red > 255) curr_red = 255; 
		if(curr_red < 0) curr_red = 0; 
		curr_green = curr_green + g;
		if(curr_green > 255) curr_green = 255; 
		if(curr_green < 0) curr_green = 0; 
		curr_blue = curr_blue + b; 
		if(curr_blue > 255) curr_blue = 255; 
		if(curr_blue < 0) curr_blue = 0; 
		this->operator()(x,y)->red = curr_red;
		this->operator()(x,y)->green = curr_green; 
		this->operator()(x,y)->blue = curr_blue; 
	 }
	}

}

void Image::invertcolors(){

	for(size_t x = 0; x<this->width(); x++){
	 for(size_t y = 0; y<this->height(); y++){
		this->operator()(x,y)->red = 255 - this->operator()(x,y)->red;
		this->operator()(x,y)->green = 255 - this->operator()(x,y)->green;
		this->operator()(x,y)->blue = 255 - this->operator()(x,y)->blue;
	 }
	}
}

