//UIUC CS125 SPRING 2014 MP. File: SelectionSort.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2014-04-11T09:32:59-0500.857314000
/** @author dkaraca2
 * 
 * @author dkaraca2
 *
 */
public class SelectionSort {
	/**
	 * Sorts the entire array using selection sort
	 * This is just a wrapper method that calls the 
	 * recursive method with the correct parameter lo,hi values.
	 * @param data
	 */
	public static void sort(double[] data) {
	int lo = 0;
	int hi = data.length -1;
	sort(data, lo, hi);
	}

	/** Recursively sorts the sub array lo...hi using selection sort algorithm.*/
	public static void sort(double[] data, int lo, int hi) {
	if(lo<hi) {
		swap(data, lo, findMin(data,lo,hi));
		sort(data, lo+1, hi);
	}
	}

	/** Helper method for selection sort: Swaps values at indices i and j*/
	public static void swap(double[] data, int i, int j) {
		double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * Recursively finds the position of the smallest value of the values lo...hi (inclusive). 
	 * @param data
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int findMin(double[] data, int lo, int hi) {
		if(lo==hi) return lo;
		int result = findMin(data, lo+1, hi);
		if(data[result] < data[lo]) return result;
		return lo;
	}

}
