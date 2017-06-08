//UIUC CS125 SPRING 2014 MP. File: BinarySearch.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2014-04-11T09:32:59-0500.857314000
/** @author dkaraca2
 * 
 * @author dkaraca2
 *
 */
public class BinarySearch {
	/** Wrapper method. Just runs the recursive search method below for the entire array*/
	public static boolean search(int[] array, int key) {
		return search(array, key, 0, array.length - 1);
	}

	/**
	 * Recursive search using Divide and Conquer approach:
	 * The given array is already sorted so we can very quickly discover if the key is in the array or not.
	 * Hint: For the recursive case check the value at (lo+hi)/2
	 * and proceed accordingly.
	 */
	static boolean search(int[] array, int key, int lo, int hi) {
	 
	if(lo > hi) return false;
	int midpt = (lo+hi)/2;
	int z = array[midpt];
	if(z == key) return true;
	if(z > key) return search(array, key, lo, midpt-1);
	return search(array, key, midpt+1, hi);
		
	}
}
