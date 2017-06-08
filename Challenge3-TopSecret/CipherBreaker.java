//UIUC CS125 SPRING 2014 MP. File: CipherBreaker.java, CS125 Project: Challenge3-TopSecret, Version: 2014-02-14T21:53:34-0600.232432000
/**
 * See CipherBreaker.txt for instructions.
 * TODO: add your netid to the line below
 * 
 * @author @dkaraca2
 */
public class CipherBreaker {

	public static void main(String[] args) {
		
		TextIO.putln("Text?");
		String line = TextIO.getln();
		TextIO.putln(line);
		
		int i = 0;
		int count_A = 0; int count_B = 0; int count_C = 0; int count_D = 0; int count_E = 0; int count_F = 0; int count_G = 0; int count_H = 0; int count_I = 0; int count_J = 0; int count_K = 0; int count_L = 0; int count_M = 0; int count_N = 0; int count_O = 0; int count_P = 0; int count_R = 0; int count_S = 0; int count_T = 0; int count_U = 0; int count_V = 0; int count_W = 0; int count_X = 0; int count_Y = 0; int count_Z = 0; int count_Q = 0; 
		int count_SPACES = 0; int count_digits = 0; int count_punc = 0;
		for(i= 0; i<line.length(); i++){
			line = line.toUpperCase();
			char c = line.charAt(i);
			if(c == 'A'){count_A = count_A + 1;}
			else if(c == 'B'){count_B = count_B + 1;}
			else if(c == 'C'){count_C = count_C + 1;}
			else if(c == 'D'){count_D = count_D + 1;}
			else if(c == 'E'){count_E = count_E + 1;}
			else if(c == 'F'){count_F = count_F + 1;}
			else if(c == 'G'){count_G = count_G + 1;}
			else if(c == 'H'){count_H = count_H + 1;}
			else if(c == 'I'){count_I = count_I + 1;}
			else if(c == 'J'){count_J = count_J + 1;}
			else if(c == 'K'){count_K = count_K + 1;}
			else if(c == 'L'){count_L = count_L + 1;}
			else if(c == 'M'){count_M = count_M + 1;}
			else if(c == 'N'){count_N = count_N + 1;}
			else if(c == 'O'){count_O = count_O + 1;}
			else if(c == 'P'){count_P = count_P + 1;}
			else if(c == 'R'){count_R = count_R + 1;}
			else if(c == 'S'){count_S = count_S + 1;}
			else if(c == 'T'){count_T = count_T + 1;}
			else if(c == 'U'){count_U = count_U + 1;}
			else if(c == 'V'){count_V = count_V + 1;}
			else if(c == 'Y'){count_Y = count_Y + 1;}
			else if(c == 'Q'){count_Q = count_Q + 1;}
			else if(c == 'X'){count_X = count_X + 1;}
			else if(c == 'W'){count_W = count_W + 1;}
			else if(c == 'Z'){count_Z = count_Z + 1;}
			else if(c == ' '){count_SPACES = count_SPACES + 1;}
			else if(c == '0'){count_digits = count_digits + 1;}
			else if(c == '1'){count_digits = count_digits + 1;}
			else if(c == '2'){count_digits = count_digits + 1;}
			else if(c == '3'){count_digits = count_digits + 1;}
			else if(c == '4'){count_digits = count_digits + 1;}
			else if(c == '5'){count_digits = count_digits + 1;}
			else if(c == '6'){count_digits = count_digits + 1;}
			else if(c == '7'){count_digits = count_digits + 1;}
			else if(c == '8'){count_digits = count_digits + 1;}
			else if(c == '9'){count_digits = count_digits + 1;}
			else if(c == '\''){count_punc = count_punc + 1;}
			else if(c == '"'){count_punc = count_punc + 1;}
			else if(c == '.'){count_punc = count_punc + 1;}
			else if(c == ','){count_punc = count_punc + 1;}
			else if(c == '!'){count_punc = count_punc + 1;}
			else if(c == '-'){count_punc = count_punc + 1;}																									
						
		}
		
		if(count_A > 0){TextIO.putln("A:" + count_A);}
		if(count_B > 0){TextIO.putln("B:" + count_B);}
		if(count_C > 0){TextIO.putln("C:" + count_C);}
		if(count_D > 0){TextIO.putln("D:" + count_D);}
		if(count_E > 0){TextIO.putln("E:" + count_E);}
		if(count_F > 0){TextIO.putln("F:" + count_F);}
		if(count_G > 0){TextIO.putln("G:" + count_G);}
		if(count_H > 0){TextIO.putln("H:" + count_H);}
		if(count_I > 0){TextIO.putln("I:" + count_I);}
		if(count_J > 0){TextIO.putln("J:" + count_J);}
		if(count_K > 0){TextIO.putln("K:" + count_K);}
		if(count_L > 0){TextIO.putln("L:" + count_L);}
		if(count_M > 0){TextIO.putln("M:" + count_M);}
		if(count_N > 0){TextIO.putln("N:" + count_N);}
		if(count_O > 0){TextIO.putln("O:" + count_O);}
		if(count_P > 0){TextIO.putln("P:" + count_P);}
		if(count_Q > 0){TextIO.putln("Q:" + count_Q);}
		if(count_R > 0){TextIO.putln("R:" + count_R);}
		if(count_S > 0){TextIO.putln("S:" + count_S);}
		if(count_T > 0){TextIO.putln("T:" + count_T);}
		if(count_U > 0){TextIO.putln("U:" + count_U);}
		if(count_V > 0){TextIO.putln("V:" + count_V);}
		if(count_W > 0){TextIO.putln("W:" + count_W);}
		if(count_X > 0){TextIO.putln("X:" + count_X);}
		if(count_Y > 0){TextIO.putln("Y:" + count_Y);}
		if(count_Z > 0){TextIO.putln("Z:" + count_Z);}
		if(count_digits > 0){TextIO.putln("DIGITS:" + count_digits);}
		if(count_SPACES > 0){TextIO.putln("SPACES:" + count_SPACES);}
		if(count_punc > 0){TextIO.putln("PUNCTUATION:" + count_punc);}
}	}
