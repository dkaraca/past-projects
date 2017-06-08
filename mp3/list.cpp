/**
 * @file list.cpp
 * Doubly Linked List (MP 3).
 *
 * @author Chase Geigle
 * @date (created) Fall 2011
 * @date (modified) Spring 2012, Fall 2012
 *
 * @author Jack Toole
 * @date (modified) Fall 2011
 */

/**
 * Destroys the current List. This function should ensure that
 * memory does not leak on destruction of a list.
 */
template <class T>
List<T>::~List()
{
clear();
    /// @todo Graded in MP3.1
}

/**
 * Destroys all dynamically allocated memory associated with the current
 * List class.
 */
template <class T>
void List<T>::clear()
{
    /// @todo Graded in MP3.1
	while(head != NULL){
		ListNode *tmp = head->next; 
		delete head;
		head = tmp;
	}
}

/**
 * Inserts a new node at the front of the List.
 * This function **SHOULD** create a new ListNode.
 *
 * @param ndata The data to be inserted.
 */
template <class T>
void List<T>::insertFront(T const & ndata)
{
    /// @todo Graded in MP3.1
	ListNode *l = new ListNode(ndata);
	if(length == 0){
		head = l;
		tail = l;
		length++;
	}
	else{
		l->next = head; 
		l->prev = NULL;
		head->prev = l;
		head = l;
		length++;
	}
}

/**
 * Inserts a new node at the back of the List.
 * This function **SHOULD** create a new ListNode.
 *
 * @param ndata The data to be inserted.
 */
template <class T>
void List<T>::insertBack( const T & ndata )
{
    /// @todo Graded in MP3.1
	ListNode *l = new ListNode(ndata);
	if(length == 0){
		tail = l;
		head = l;
		length++;
	}
	else{
		l->prev = tail;
		l->next = NULL;
		tail->next = l;
		tail = l;
		length++;
	}
}


/**
 * Reverses the current List.
 */
template <class T>
void List<T>::reverse()
{	if(length != 0){
		ListNode *tmpHead = head;
		ListNode *tmpTail = tail;
    reverse(head, tail);
		head = tmpTail;
		tail = tmpHead;
	}
	else return;
}

/**
 * Helper function to reverse a sequence of linked memory inside a List,
 * starting at startPoint and ending at endPoint. You are responsible for
 * updating startPoint and endPoint to point to the new starting and ending
 * points of the rearranged sequence of linked memory in question.
 *
 * @param startPoint A pointer reference to the first node in the sequence
 *  to be reversed.
 * @param endPoint A pointer reference to the last node in the sequence to
 *  be reversed.
 */
template <class T>
void List<T>::reverse( ListNode * & startPoint, ListNode * & endPoint )
{
    /// @todo Graded in MP3.1

	if(startPoint == endPoint) return; 
	ListNode *before, *after, *curr, *start; 
start = startPoint;
	if(startPoint->prev != NULL) before = startPoint->prev;
		else before = NULL;
	if(endPoint->next != NULL) after = endPoint->next;
		else after = NULL;
	curr = startPoint; 
	while(curr != endPoint){
		ListNode *tmp = curr->next;
		if(curr == startPoint)
		 //if(after != NULL) 
		 curr->next = after;
		// else curr->next = NULL; 
		
		 
		else curr->next = curr->prev;
		curr->prev = tmp;
		curr = curr->prev;
	}
	if(curr == endPoint){
		ListNode *tmp = curr->prev;
		if(before != NULL) curr->prev = before; 
		else curr->prev = NULL;
		curr->next = tmp;
	}
	
    // if(after != NULL) after->prev = start;
	
}


/**
 * Reverses blocks of size n in the current List. You should use your
 * reverse( ListNode * &, ListNode * & ) helper function in this method!
 *
 * @param n The size of the blocks in the List to be reversed.
 */
template <class T>
void List<T>::reverseNth( int n )
{
if(length <= 0) return;
if(n<length && n>0){
ListNode *tmp, *one, *two, *prevOne, *nextTmp; 
	one = tmp = two = prevOne = head; 
	int c = 0;
	
	while(c < length){
		if((length - c) < n) n = (length-c);
		
		for(int i = 0; i<n; i++){
			tmp = two; 
			if(two->next != NULL) two = two->next;
		}
		nextTmp = tmp;
		if(tmp->next != NULL){
			for(int i = 0; i<n; i++){
		       	if(nextTmp->next != NULL) nextTmp = nextTmp->next;
			}
		}
		 else nextTmp = NULL;

		prevOne = one;


		reverse(one,tmp);

		if(c >= 0){ if(nextTmp != NULL)prevOne->next = nextTmp; 
			else prevOne->next = NULL;}
		
		if(tmp->prev == NULL) head = tmp;
		if(one->next == NULL) tail = one;
		c+=n;
		if(c>=length) c = length;		
		one = tmp = two;
	}
}
else if (n >= length) reverse(head,tail);
else if(n<=0) return;
    /// @todo Graded in MP3.1

}
/**
 * Modifies the List using the waterfall algorithm.
 * Every other node (starting from the second one) is removed from the
 * List, but appended at the back, becoming the new tail. This continues
 * until the next thing to be removed is either the tail (**not necessarily
 * the original tail!**) or NULL.  You may **NOT** allocate new ListNodes.
 * Note that since the tail should be continuously updated, some nodes will
 * be moved more than once.
 */
template <class T>
void List<T>::waterfall()
{
    /// @todo Graded in MP3.1
	if(head == NULL || tail == NULL || length == 0) return;
	ListNode *curr = head; 
	ListNode *tmp = curr->next; 
	while(tmp != NULL && tmp != tail){
		curr->next = tmp->next;
		tmp->next = NULL;
		tmp->prev = tail; 
		tail->next = tmp;
		tail = tmp;
		tmp = curr->next;
		if(tmp != NULL){
			tmp->prev = curr;
			curr = tmp;
			tmp = curr->next;
		}
	}
}

/**
 * Splits the given list into two parts by dividing it at the splitPoint.
 *
 * @param splitPoint Point at which the list should be split into two.
 * @return The second list created from the split.
 */
template <class T>
List<T> List<T>::split(int splitPoint)
{
    if (splitPoint > length)
        return List<T>();

    if (splitPoint < 0)
        splitPoint = 0;

    ListNode * secondHead = split(head, splitPoint);

    int oldLength = length;
    if (secondHead == head)
    {
        // current list is going to be empty
        head = NULL;
        tail = NULL;
        length = 0;
    }
    else
    {
        // set up current list
        tail = head;
        while (tail->next != NULL)
            tail = tail->next;
        length = splitPoint;
    }

    // set up the returned list
    List<T> ret;
    ret.head = secondHead;
    ret.tail = secondHead;
    if (ret.tail != NULL)
    {
        while (ret.tail->next != NULL)
            ret.tail = ret.tail->next;
    }
    ret.length = oldLength - splitPoint;
    return ret;
}

/**
 * Helper function to split a sequence of linked memory at the node
 * splitPoint steps **after** start. In other words, it should disconnect
 * the sequence of linked memory after the given number of nodes, and
 * return a pointer to the starting node of the new sequence of linked
 * memory.
 *
 * This function **SHOULD NOT** create **ANY** new List objects!
 *
 * @param start The node to start from.
 * @param splitPoint The number of steps to walk before splitting.
 * @return The starting node of the sequence that was split off.
 */
template <class T>
typename List<T>::ListNode * List<T>::split(ListNode * start, int splitPoint)
{
    /// @todo Graded in MP3.2
    // change me!
	ListNode *two, *before;
	two = before = start;
	for(int i = 0; i<splitPoint; i++){
		before = two;
		two = two->next;
	}
	before->next = NULL;
	two->prev = NULL;
	return two;
}

/**
 * Merges the given sorted list into the current sorted list.
 *
 * @param otherList List to be merged into the current list.
 */
template <class T>
void List<T>::mergeWith(List<T> & otherList)
{
    // set up the current list
    head = merge(head, otherList.head);
    tail = head;

    // make sure there is a node in the new list
    if(tail != NULL)
    {
        while (tail->next != NULL)
            tail = tail->next;
    }
    length = length + otherList.length;

    // empty out the parameter list
    otherList.head = NULL;
    otherList.tail = NULL;
    otherList.length = 0;
}

/**
 * Helper function to merge two **sorted** and **independent** sequences of
 * linked memory. The result should be a single sequence that is itself
 * sorted.
 *
 * This function **SHOULD NOT** create **ANY** new List objects.
 *
 * @param first The starting node of the first sequence.
 * @param second The starting node of the second sequence.
 * @return The starting node of the resulting, sorted sequence.
 */
template <class T>
typename List<T>::ListNode * List<T>::merge(ListNode * first, ListNode * second)
{
    /// @todo Graded in MP3.2
    // change me!
	int i = 0;
	ListNode *head_three, *temp;
	if(first == NULL) return second;
	if(second == NULL) return first;
	if(first->data < second->data){
		head_three = first;
		while(first->next != NULL && second != NULL){
			if(first->next->data < second->data) first = first->next;
			else{
				temp = second->next;
				second->next = first->next; 
				first->next = second;
				second = temp;
				first = first->next;
			}
		}
		if(second != NULL) first->next = second;
	}
	else{
		head_three = second;
		while(second->next != NULL && first != NULL){
			if(second->next->data < first->data) second = second->next;
			else{
				temp = first->next;
				first->next = second->next;
				second->next = first;
				first = temp;
				second = second->next;
			}
		}
		if(first != NULL) second->next = first;
	}
	return head_three;
}

/**
 * Sorts the current list by applying the Mergesort algorithm.
 */
template <class T>
void List<T>::sort()
{
    if (empty())
        return;
    head = mergesort(head, length);
    tail = head;
    while (tail->next != NULL)
        tail = tail->next;
}

/**
 * Sorts a chain of linked memory given a start node and a size.
 * This is the recursive helper for the Mergesort algorithm (i.e., this is
 * the divide-and-conquer step).
 *
 * @param start Starting point of the chain.
 * @param chainLength Size of the chain to be sorted.
 * @return A pointer to the beginning of the now sorted chain.
 */
template <class T>
typename List<T>::ListNode * List<T>::mergesort(ListNode * start, int chainLength)
{
    /// @todo Graded in MP3.2
    // change me!
	if(start->next == NULL) return start;
	else{
		bool isOdd = false;
		isOdd = chainLength%2;
		chainLength = chainLength/2;
		ListNode *mid = split(start, chainLength);
		ListNode *left = mergesort(start, chainLength);
		if(isOdd) chainLength++;
		ListNode *right = mergesort(mid, chainLength);
		start = merge(left, right);
	}
	return start;
}
