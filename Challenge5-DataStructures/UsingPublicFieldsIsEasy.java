//UIUC CS125 SPRING 2014 MP. File: UsingPublicFieldsIsEasy.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000

/**
 * Complete the class method 'analyze' that takes a SimplePublicPair object as an argument
 * and returns a new SimplePublicTriple object.
 * The SimplePublicTriple needs to set up as follows:
 * x = the minimum value of 'a' and 'b'
 * y = the maximum value of 'a' and 'b'
 * description:a*b=M 
 *   where a,b, and M are replaced with the numerical values of a, b and the multiplication of a and b.
 * Your code will create a SimplePublicTriple, initializes the three fields and return a reference to the SimplePublicTriple object.
 * 
 * @author dkaraca2
 */
public class UsingPublicFieldsIsEasy {
	public static int a = 0;
	public static int b = 0;
	public static int description = 0;
	
	
	
	public static SimplePublicTriple analyze(SimplePublicPair in) {
		
		SimplePublicTriple result = new SimplePublicTriple();
		
		result.x = in.a;
		result.y = in.b;
		result.description = result.x + "*" + result.y + "=" + result.x*result.y ;
		return result;
		
		
	}
}
