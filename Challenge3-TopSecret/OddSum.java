//UIUC CS125 SPRING 2014 MP. File: OddSum.java, CS125 Project: Challenge3-TopSecret, Version: 2014-02-14T21:53:34-0600.232432000
/**
 * Prints sum of odd numbers in a specific format.
 * TODO: add your netid to the line below
 * @author @dkaraca2
 */
public class OddSum { 
/**
Example output if user enters 10:
Max?
1 + 3 + 5 + 7 + 9 = 25
25 = 9 + 7 + 5 + 3 + 1

Example output if user enters 11:
Max?
1 + 3 + 5 + 7 + 9 + 11 = 36
36 = 11 + 9 + 7 + 5 + 3 + 1

 */
 public static void main(String[] args) { 
	 
	 TextIO.putln("Max?");
	 int max = TextIO.getlnInt();

	 int count = 1, total = 0;
	int i = 1;
	int a = 0; 
	int b = 0;
	 for(count=0; i<=max; count++){
		 total = total + i;
		 TextIO.put(i + " "); 
		 b = 1;
		 
		 
		 i = i+2;
		 if(i<=max){TextIO.put("+ ");}
		 
	 }
		 TextIO.putln("= " + total);
		 TextIO.put(total + " = ");
		 i = i-2;
		for(count= 0; i>0; count++){
			TextIO.put(i);
			i = i-2;
			if(i>=1){TextIO.put(" + ");}
		}

  } // end of main method
} // end of class 
