EXENAME = crr
OBJS = crrbinomial.o crr.o

CXX = clang++ 
CXXFLAGS = -std=c++1y -stdlib=libc++ -c -g -O0 -Wall -Wextra -pedantic
LD = clang++
LDFLAGS = -std=c++1y -stdlib=libc++ -lpng -lc++abi


all : $(EXENAME)

$(EXENAME) : $(OBJS)
	$(LD) $(OBJS) $(LDFLAGS) -o $(EXENAME)

crr.o : crr.cpp crrbinomial.cpp crrbinomial.h
	    $(CXX) $(CXXFLAGS) crr.cpp

crrbinomial.o : crrbinomial.cpp crrbinomial.h crr.cpp
	$(CXX) $(CXXFLAGS) crrbinomial.cpp 

clean :
	-rm -f *.o $(EXENAME)
