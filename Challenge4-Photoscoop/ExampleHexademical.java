//UIUC CS125 SPRING 2014 MP. File: ExampleHexademical.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-02-28T16:51:13-0600.813671000
public class ExampleHexademical {
	// Important idea: 2 Hex digits represents exactly 8 bits.
	// So it's a useful and compact notation to represent larger integers.
	// You can read the bits directly, just by reading the hex digits
	public static void main(String[] args) {
		while (true) {
			TextIO.putln("Enter a value. Try: 256,255,0xff,0xf,65535,0xff00ff,55170,-1, or garbage to cause an exception.");
			String input = TextIO.getln();
			int base = 10;
			if (input.indexOf("0x") == 0) {
				base = 16;
				input = input.substring(2); // drop '0x' from the string
			}
			int num = Integer.valueOf(input, base);
			TextIO.putln(num + " in hex is 0x" + Integer.toHexString(num));
			TextIO.putln(num + " in binary is " + Integer.toBinaryString(num));
		}
	}
}
