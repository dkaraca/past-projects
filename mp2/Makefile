EXENAME = testscene
OBJS = scene.o png.o rgbapixel.o image.o testscene.o

CXX = clang++ 
CXXFLAGS = -std=c++1y -stdlib=libc++ -c -g -O0 -Wall -Wextra -pedantic
LD = clang++
LDFLAGS = -std=c++1y -stdlib=libc++ -lpng -lc++abi


all : $(EXENAME)

$(EXENAME) : $(OBJS)
	$(LD) $(OBJS) $(LDFLAGS) -o $(EXENAME)

testscene.o : testscene.cpp scene.cpp png.h rgbapixel.h image.h scene.h
	    $(CXX) $(CXXFLAGS) testscene.cpp

scene.o : scene.cpp png.h rgbapixel.h image.h scene.h testscene.cpp
	$(CXX) $(CXXFLAGS) scene.cpp 

png.o : png.cpp png.h rgbapixel.h
	$(CXX) $(CXXFLAGS) png.cpp

rgbapixel.o : rgbapixel.cpp rgbapixel.h
	$(CXX) $(CXXFLAGS) rgbapixel.cpp

image.o : image.cpp png.h rgbapixel.h
	$(CXX) $(CXXFLAGS) image.cpp

clean :
	-rm -f *.o $(EXENAME)
