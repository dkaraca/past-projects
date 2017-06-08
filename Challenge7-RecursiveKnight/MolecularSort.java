//UIUC CS125 SPRING 2014 MP. File: MolecularSort.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2014-04-11T09:32:59-0500.857314000
/** @author dkaraca2
 * 
 * @author dkaraca2
 *
 */
public class MolecularSort {

	/** Sorts each xyz coordinate using it's Z value (coord[i][2] <= coord[j][2] for i<j). */
	static void sortCoordsByZ(double[][] coords) {
		// TODO: Implement this wrapper method.
		//All the work is performed by recursiveSort
		int lo = 0;
		int hi = coords.length -1;
		recursiveSort(coords, lo, hi);
	}

	/**
	 * recursively sorts coordinates entries by their z value. Entries between
	 * lo and hi inclusive are considered.
	 */
	static void recursiveSort(double[][] coords, int lo, int hi) {
		// TODO: write the four lines of a recursive selection sort here.
		if(lo<hi) {
			swap(coords, lo, findIndexOfZMinimum(coords,lo,hi));
			recursiveSort(coords, lo+1, hi);
		}
	}

	/**
	 * returns the index of the entry with the lowest Z value. Entries between
	 * lo and hi inclusive are considered.
	 */
	static int findIndexOfZMinimum(double[][] coords, int lo, int hi) {
		if(lo==hi) return lo;
		int result = findIndexOfZMinimum(coords, lo+1, hi);
		if(coords[result][2] < coords[lo][2]) return result;
		
		return lo; // TODO: Replace this with your three lines of recursive code
	}
	

	/* Swaps the (x,y and z) values of the i-th and j-th coordinates.*/
	static void swap(double[][] coords, int i, int j) {
		// TODO: write your swap implementation here
		double[] temp = coords[i];
		coords[i] = coords[j];
		coords[j] = temp;
	}
}
