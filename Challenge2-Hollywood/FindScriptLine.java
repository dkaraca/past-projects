//UIUC CS125 SPRING 2014 MP. File: FindScriptLine.java, CS125 Project: Challenge2-Hollywood, Version: 2014-02-07T20:36:00-0600.953783000
/**
 * A program to search for specific lines and print their line number.
 * See FindScriptLine.txt for more details
 * TODO: add your netid to the line below
 * @author dkaraca2
 */
public class FindScriptLine {

	public static void main(String[] args) {
// TODO: Implement the functionality described in FindScriptLine.txt
// TODO: Test your code manually and using the automated unit tests in FindScriptLineTest		
	boolean output = false;
		
		System.out.print("Please enter the word(s) to search for\n");
		String w = TextIO.getln();
		
				
		TextIO.putln("Searching for '" + w + "'");
		

		
		TextIO.readFile("thematrix.txt");
		
		output = false; 
		int count = 0;
		
		
		
		while (false == TextIO.eof()) {
			count = count + 1;
			String line = TextIO.getln();
					
			String line_capital = line.toUpperCase();
			String w_capital = w.toUpperCase();
			
			if (line_capital.indexOf(w_capital)<0) 
				output = false;
			
			if (line_capital.indexOf(w_capital) >= 0)
				output = true; 
			if (output ){						
				
				line = line.trim();
				TextIO.putln(count + " " + "-" + " "  + line);
			}
			
		}
		
		TextIO.putln("Done Searching for '" + w + "'");
	
	}
}

