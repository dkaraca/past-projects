//UIUC CS125 SPRING 2014 MP. File: MovieSurvey.java, CS125 Project: Challenge2-Hollywood, Version: 2014-02-07T20:36:00-0600.953783000
/**
 * A program to run a simple survey and report the results. See MovieSurvey.txt
 * for more information. TODO: add your netid to the line below
 * 
 * @author dkaraca2
 */
public class MovieSurvey {
	public static void main(String[] arg) {
		// TODO: Write your program here
		// Hints :
		// Formatted output
		// http://math.hws.edu/javanotes/c2/s4.html#basics.4.4
		
		// Don't just copy paste the expected output
		// For grading purposes your code may be tested
		// with other input values.
	
	System.out.println("Welcome. We're interested in how people are watching movies this year.\nThanks for your time. - The WRITERS GUILD OF AMERICA.\nPlease tell us about how you've watched movies in the last month.\nHow many movies have you seen at the cinema?\nHow many movies have you seen using a DVD or VHS player?\nHow many movies have you seen using a Computer?");
	
	int cinema= TextIO.getlnInt();
	int dvd_player= TextIO.getlnInt();
	int computer= TextIO.getlnInt();
	
	TextIO.putln("Summary: " + cinema + " Cinema movies, " + dvd_player + " DVD/VHS movies, " + computer + " Computer movies");
	
	int total= cinema + dvd_player + computer; 
	
	TextIO.putln("Total: " + total + " movies");
	
	
	double cinema_fraction = (double) cinema / (double) total;
	cinema_fraction = cinema_fraction * 100;
	TextIO.put("Fraction of movies seen at a cinema: ");
	TextIO.putf("%.2f", cinema_fraction);
	TextIO.putln("%");
	
	double fraction = (double) (dvd_player + computer) / (double) total;
	fraction = fraction * 100;
	TextIO.put("Fraction of movies seen outside of a cinema: ");
	TextIO.putf("%.2f", fraction);
	TextIO.putln("%");
	
	
	
	
	
	
	
	}
}
