//UIUC CS125 SPRING 2014 MP. File: Stack.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000
/**
 * 
 * @author dkaraca2
 *
 */
public class Stack {
	private  String[] list = new String[0];
	
	/** Adds a value to the top of the stack.*/
	public void push(String value){
		
		String[] p = new String[list.length + 1];
		p[0] = value;
		for(int i =1; i<p.length; i++){
			p[i] = list[i-1];
		}
		list = p;
		
		
	}
	
	/** Removes the topmost string. If the stack is empty, returns null. */
	public String pop() {
		if(list.length == 0){return null;}
		
		else{
			int a = list.length - 1;
			String b = list[0];
			String[] p = new String[a];
			for(int i = 0; i<a; i++){
				p[i] = list[i+1];
			}list = p; return b;
		}
	}
	
	/** Returns the topmost string but does not remove it. If the stack is empty, returns null. */
	public String peek() {
		if(list.length == 0) {return null;}
		else {return list[0];}
	}
	
	/** Returns true iff the stack is empty */
	public boolean isEmpty() {
		return list.length ==0;
	}

	/** Returns the number of items in the stack. */
	public int length() {
		return list.length;
	}
	
	/** Returns a string representation of the stack. Each string is separated by a newline. Returns an empty string if the stack is empty. */
	public String toString() {
		StringBuffer s = new StringBuffer();
		
			for(int a = list.length-1; a>=0; a--){
			
			s.append(list[a]);
			s.append("\n");
		
		
	}return s.toString();
	}
}
