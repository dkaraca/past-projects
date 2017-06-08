//UIUC CS125 SPRING 2014 MP. File: Queue.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000
/**
 * 
 * @author dkaraca2
 *
 */
public class Queue {
	private  double[] q = new double[0];
	
	/** Adds the value to the front of the queue.
	 * Note the queue automatically resizes as more items are added. */
	public void add(double value) {
		
		double[] p = new double[q.length + 1];
		p[0] = value;
		for(int i =1; i<p.length; i++){
			p[i] = q[i-1];
		}
		q = p;
	}
	/** Removes the value from the end of the queue. If the queue is empty, returns 0 */
	public  double remove() {
		if(q.length == 0){return 0;}
		
		else{
			int a = q.length - 1;
			double b = q[q.length-1];
			double[] p = new double[a];
			for(int i = 0; i<a; i++){
				p[i] = q[i];
			}q = p; return b;
		}
		
	}
	
	/** Returns the number of items in the queue. */
	public  int length() {
		return q.length;		
	}
	
	/** Returns true iff the queue is empty */
	public  boolean isEmpty() {
		if(q.length == 0) return true;
		else return false;
	}
	
	/** Returns a comma separated string representation of the queue. */
	public  String toString(){
		StringBuffer s = new StringBuffer();
		
		for(int a = q.length-1; a>=0; a--){
		
		s.append(q[a]);
		if(a >0 ) s.append(',');
	
	
}return s.toString();
		
		
		
	}
}
