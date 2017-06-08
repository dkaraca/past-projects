//UIUC CS125 SPRING 2014 MP. File: CaesarCipher.java, CS125 Project: Challenge3-TopSecret, Version: 2014-02-14T21:53:34-0600.232432000
/**
 * A program to search for to encrypt and decrypt lines of text. See
 * CaesarCipher.txt
 * Hints: line.charAt( int ) is useful.
 * You'll need loops, and conditions and variables
 * You'll need to read the Test cases to understand how your program should work.
 * Good Programming Hints: "DRY: Don't Repeat Yourself"
 * Try to make your program as short as possible.
 * TODO: add your netid to the line below
 * @author dkaraca2
 */
public class CaesarCipher {

	public static void main(String[] strings) {
		//TODO: Delete the following line and write your implementation here- 
		char output;

		boolean go = true;
		TextIO.putln("Please enter the shift value (between -25..-1 and 1..25)");
		int offset = TextIO.getlnInt();
	
		while(offset == 0 || (offset < 999 && offset > 25) ||  (offset > -999 && offset < -25)){
		
			TextIO.putln(offset + " is not a valid shift value.");
			TextIO.putln("Please enter the shift value (between -25..-1 and 1..25)");
			offset = TextIO.getlnInt();
		}
		

		
		if(offset == 999 ){
		
		TextIO.putln("Using position shift");
		while(go){
		TextIO.putln("Please enter the source text (empty line to quit)");
		String source = TextIO.getlnString();
		if(source.length() == 0){go = false; TextIO.put("Bye."); break;}
		else{
		TextIO.putln("Source   :" + source);
		source = source.toUpperCase();
		int i = 0;
		TextIO.put("Processed:");
		for(i= 0; i<source.length(); i++){
			
			
			offset = -i;
			
			char c = source.charAt(i);
			
			if(c == ' '){TextIO.put(" ");}
			else if( c == '.'){TextIO.put(".");}
			else if( c == ','){TextIO.put(",");}
			else if( c == ';'){TextIO.put(";");}
			else if( c == ':'){TextIO.put(":");}
			else if( c == '('){TextIO.put("(");}
			else if( c == ')'){TextIO.put(")");}
			else if( c == '?'){TextIO.put("?");}
			else if( c == '!'){TextIO.put("!");}
			else if( c == '\''){TextIO.put("'");}
			else if( c == '-'){TextIO.put("-");}
			else if( c == '0'){TextIO.put("0");}
			else if( c == '1'){TextIO.put("1");}
			else if( c == '2'){TextIO.put("2");}
			else if( c == '3'){TextIO.put("3");}
			else if( c == '4'){TextIO.put("4");}
			else if( c == '5'){TextIO.put("5");}
			else if( c == '6'){TextIO.put("6");}
			else if( c == '7'){TextIO.put("7");}
			else if( c == '8'){TextIO.put("8");}
			else if( c == '9'){TextIO.put("9");}
			
			else{
				
				
			int letter = c - 'A';
			int encryptedDelta = (26 + letter - offset) % 26;
			output = (char) (encryptedDelta + 'A');
			TextIO.put(output);
			}
			
		}	TextIO.putln();
		} }
		}
		
		
		else if(offset == -999){
			TextIO.putln("Using position shift");
			
			while(go){
				TextIO.putln("Please enter the source text (empty line to quit)");
				String source = TextIO.getlnString();
				if(source.length() == 0){go = false; TextIO.put("Bye."); break;}
				else{
			TextIO.putln("Source   :" + source);
			source = source.toUpperCase();
			int i = 0;
			TextIO.put("Processed:");
			for(i= 0; i<source.length(); i++){
				
				
				offset = i;
				
				char c = source.charAt(i);
				
				if(c == ' '){TextIO.put(" ");}
				else if( c == '.'){TextIO.put(".");}
				else if( c == ','){TextIO.put(",");}
				else if( c == ';'){TextIO.put(";");}
				else if( c == ':'){TextIO.put(":");}
				else if( c == '('){TextIO.put("(");}
				else if( c == ')'){TextIO.put(")");}
				else if( c == '?'){TextIO.put("?");}
				else if( c == '-'){TextIO.put("-");}
				else if( c == '\''){TextIO.put("'");}
				else if( c == '!'){TextIO.put("!");}
				else if( c == '0'){TextIO.put("0");}
				else if( c == '1'){TextIO.put("1");}
				else if( c == '2'){TextIO.put("2");}
				else if( c == '3'){TextIO.put("3");}
				else if( c == '4'){TextIO.put("4");}
				else if( c == '5'){TextIO.put("5");}
				else if( c == '6'){TextIO.put("6");}
				else if( c == '7'){TextIO.put("7");}
				else if( c == '8'){TextIO.put("8");}
				else if( c == '9'){TextIO.put("9");}
				
				else{
					
					
				int letter = c + 'A';
				int encryptedDelta = (26 + letter - offset) % 26;
				output = (char) (encryptedDelta + 'A');
				TextIO.put(output);
				}
				
			}TextIO.putln();
			}
		
			}}
		
		else {
			TextIO.putln("Using shift value of " + offset);
			
			while(go){
				TextIO.putln("Please enter the source text (empty line to quit)");
				String source = TextIO.getlnString();
				if(source.length() == 0){go = false; TextIO.put("Bye."); break;}
				else{
			TextIO.putln("Source   :" + source);
			source = source.toUpperCase();
			int i = 0;
			TextIO.put("Processed:");	
			for(i= 0; i<source.length(); i++){
				
				
				char c = source.charAt(i);
				if(c == ' '){TextIO.put(" ");}
				else if( c == '.'){TextIO.put(".");}
				else if( c == ','){TextIO.put(",");}
				else if( c == ';'){TextIO.put(";");}
				else if( c == ':'){TextIO.put(":");}
				else if( c == '('){TextIO.put("(");}
				else if( c == ')'){TextIO.put(")");}
				else if( c == '?'){TextIO.put("?");}
				else if( c == '!'){TextIO.put("!");}
				else if( c == '\''){TextIO.put("'");}
				else if( c == '-'){TextIO.put("-");}
				else if( c == '0'){TextIO.put("0");}
				else if( c == '1'){TextIO.put("1");}
				else if( c == '2'){TextIO.put("2");}
				else if( c == '3'){TextIO.put("3");}
				else if( c == '4'){TextIO.put("4");}
				else if( c == '5'){TextIO.put("5");}
				else if( c == '6'){TextIO.put("6");}
				else if( c == '7'){TextIO.put("7");}
				else if( c == '8'){TextIO.put("8");}
				else if( c == '9'){TextIO.put("9");}
				
				else {
				
				int letter = c - 'A';
				int encryptedDelta = (26 + letter + offset) % 26;
				output = (char) (encryptedDelta + 'A');
				TextIO.put(output);
				}
			
				
		
	}TextIO.putln();	 }
}
		
		}
	
	}
}





				
			


