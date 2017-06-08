//UIUC CS125 SPRING 2014 MP. File: Factorial.java, CS125 Project: Challenge1-DebugMe, Version: 2014-02-03T03:20:36-0600.507150000

/**
 * A program to calculate a factorial. The given code may contain errors. Fix the
 * given code and add additional code to calculate a factorial and pass the unit
 * tests. Hint sometimes an 'int' just 'aint big enough.
 * 
 * @see Factorial-ReadMe.txt for details on how to complete this program.
 * @author dkaraca2
 */
public class Factorial {
	public static void main(String[] args) {
		int max = 0;
		
		
		System.out.println("Enter a number between 1 and 20 inclusive.");
		max = TextIO.getlnInt();
		
		while(max < 1 || max > 20){
			TextIO.putln("Enter a number between 1 and 20 inclusive.");
			max = TextIO.getlnInt();
		}
		int max_i = max;
		if (max >= 1 && max <= 20) {
			
			if(max == 1){
				TextIO.putln("1! = " + 1);
				return;
			}else{
					
					long answer = 1;
					for(int i = 0; i < max_i; i++){
						answer = answer * max;
						max = max-1;
					}
				TextIO.putln(max_i + "!" + " = " + answer);	
				
				return;}
			
					
			}
			
		}
		
	}
