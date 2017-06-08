//UIUC CS125 SPRING 2014 MP. File: Example_RecursivePrinter.java, CS125 Project: Challenge6-RecursionSee, Version: 2014-04-04T10:07:09-0500.853918000
public class Example_RecursivePrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Hello!";
		printer(message);
	}

	private static void printer(String s) {
		if(s.length() ==0) {
			System.out.println(" ...Done!"); // Base case
			return;
		}
		// Try swapping the order of the next two lines
		
		System.out.print( s.charAt(0)+","); //First character

		printer(s.substring(1) ); // Recurse on remaining characters 
	}
	
}
