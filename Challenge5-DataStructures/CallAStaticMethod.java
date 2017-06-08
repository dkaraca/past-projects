//UIUC CS125 SPRING 2014 MP. File: CallAStaticMethod.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000
/**
 * Prints out only lines that contain an email address Each printed line is
 * justified to right by prepending the text with '.' characters The minimum
 * width of the line including padding is 40 characters. See the test case for
 * example input and expected output.
 * @author dkaraca2
 */
class CallAStaticMethod {

	public static void main(String[] args) {

		while (!TextIO.eof()) {
			String line = TextIO.getln();
			
			if(ExampleClassMethods.isEmailAddress(line)== true){
				TextIO.put(ExampleClassMethods.createPadding('.', 40-line.length()).toString());
				
			// Use ExampleClassMethods
			// 'isEmailAddress' and 'createPadding' to complete this method
			TextIO.putln(line);}
		}

	}
}
