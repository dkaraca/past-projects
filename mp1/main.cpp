#include <algorithm>
#include <iostream>
#include "rgbapixel.h"
#include "png.h"
using namespace std;

PNG rotateImage(PNG original){

 PNG target;
 target.resize(original.width(), original.height() );
 
 for(size_t xi = 0; xi < target.width(); xi++){
   for(size_t yi = 0; yi < target.height(); yi++){
     int sourceY = target.height() - 1 - yi;
     int sourceX = target.width() -1 -xi;
     target.operator()(xi,yi)->red = original.operator()(sourceX,sourceY)->red;
     target.operator()(xi,yi)->green = original.operator()(sourceX,sourceY)->green;
     target.operator()(xi,yi)->blue = original.operator()(sourceX,sourceY)->blue;
     target.operator()(xi,yi)->alpha = original.operator()(sourceX,sourceY)->alpha;
   }
 }
 
 return target;
}

int main(){

    PNG image("in.png");
	

    rotateImage(image).writeToFile("out.png");

    

    return 0;
}
