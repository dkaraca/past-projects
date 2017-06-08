/**
 * @file quackfun.cpp
 * This is where you will implement the required functions for the
 *  stacks and queues portion of the lab.
 */

/**
 * Sums items in a stack.
 * @param s A stack holding values to sum.
 * @return The sum of all the elements in the stack, leaving the original
 *  stack in the same state (unchanged).
 *
 * @note You may modify the stack as long as you restore it to its original
 *  values.
 * @note You may use only two local variables of type T in your function.
 *  Note that this function is templatized on the stack's type, so stacks of
 *  objects overloading the + operator can be summed.
 * @note We are using the Standard Template Library (STL) stack in this
 *  problem. Its pop function works a bit differently from the stack we
 *  built. Try searching for "stl stack" to learn how to use it.
 * @hint Think recursively!
 */
template <typename T>
T QuackFun::sum(stack<T> & s)
{
	//stack<T> temp;
	//while(!s.empty()){
	//	temp.push(s.top());
	//	s.pop();
	//}
	//T total = 0;
	//while(!temp.empty()){
	//	s.push(temp.top());
	//	total+=temp.top();
	//	temp.pop();
	//}
    // Your code here
    //return total; // stub return value (0 for primitive types). Change this!
                // Note: T() is the default value for objects, and 0 for
                // primitive types
	//if(s.empty()) return 0;
	//else{
	//	T ttl = s.top();
	//	s.pop();
	//	s.push(ttl);
	//	return ttl + sum(s);
		
	//}
	
	queue<T> tmpQ;
	int size = s.size();
	T total = 0;
	for(int i = 0; i<size; i++){
		total+=s.top();
		tmpQ.push(s.top());
		s.pop();
	}
	for(int i = 0; i<size; i++){
		s.push(tmpQ.front());
		tmpQ.pop();
	}
	for(int i = 0; i<size; i++){
		tmpQ.push(s.top());
		s.pop();
	}
	for(int i = 0; i<size; i++){
		s.push(tmpQ.front());
		tmpQ.pop();
	}
	return total;

	
	//int arr_size = s.size();
	//T total = 0;
	//T arr_tmp[arr_size];
	//for(int i = 0; i<arr_size; i++){
	//	arr_tmp[i] = s.top();
	//	total+=s.top();
	//	s.pop();
	//}
	//for(int i = arr_size-1; i>=0; i--)
	//	s.push(arr_tmp[i]);
}

/**
 * Reverses even sized blocks of items in the queue. Blocks start at size
 * one and increase for each subsequent block.
 * @param q A queue of items to be scrambled
 *
 * @note Any "leftover" numbers should be handled as if their block was complete.
 * @note We are using the Standard Template Library (STL) queue in this
 *  problem. Its pop function works a bit differently from the stack we
 *  built. Try searching for "stl stack" to learn how to use it.
 * @hint You'll want to make a local stack variable.
 */
template <typename T>
void QuackFun::scramble(queue<T> & q)
{
    stack<T> s;
    // optional: queue<T> q2;

    // Your code here
	queue<T> tempQ;
	int i = 1;
	int c = q.size();
	int a = i;
	while(c != 0){
		if((c-a) <0) {
			if(i%2 == 0){ i = q.size();
		
			for(int j = 0; j<i; j++){
				s.push(q.front());
				q.pop();
			}
			while(!s.empty()){
				tempQ.push(s.top());
				s.pop();
			}c = 0;
			}
			else{
				for(int l = 0; l<i; l++){
				tempQ.push(q.front());
				q.pop();
			}c = 0; }			
}
		else if(i%2 == 0){
			//c-=i;
			for(int j = 0; j<i; j++){
				s.push(q.front());
				q.pop();
			}
			while(!s.empty()){
				tempQ.push(s.top());
				s.pop();
			}std::cout<< a << std::endl; i++; a+=i; 
		}
		else{
			//c-=i;
			for(int l = 0; l<i; l++){
				tempQ.push(q.front());
				q.pop();
			} std::cout<< a << std::endl; i++; a+=i;
		}
	}
	while(!tempQ.empty()){
		q.push(tempQ.front());
		tempQ.pop();
	} std::cout<< a << std::endl;
}

/**
 * @return true if the parameter stack and queue contain only elements of exactly
 *  the same values in exactly the same order; false, otherwise.
 *
 * @note You may assume the stack and queue contain the same number of items!
 * @note There are restrictions for writing this function.
 * - Your function may not use any loops
 * - In your function you may only declare ONE local boolean variable to use in your return statement,
 *      and you may only declare TWO local variables of parametrized type T to use however you wish.
 *   No other local variables can be used.
 * - After execution of verifySame, the stack and queue must be unchanged. Be sure to comment your code VERY well.
 */
template <typename T>
bool QuackFun::verifySame(stack<T> & s, queue<T> & q)
{
    bool retval = true; // optional
    //T temp1; // rename me
    //T temp2; // rename :)
    
    return retval;
}

