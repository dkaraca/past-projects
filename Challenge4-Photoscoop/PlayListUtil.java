//UIUC CS125 SPRING 2014 MP. File: PlayListUtil.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-02-28T16:51:13-0600.813671000
/**
 * Add you netid here..
 * 
 * @author angrave, dkaraca2
 * 
 */
public class PlayListUtil {

	/**
	 * Debug ME! Use the unit tests to reverse engineer how this method should
	 * work. Hint: Fix the formatting (shift-cmd-F) to help debug the following
	 * code
	 * 
	 * @param list
	 * @param maximum
	 */
	public static void list(String[] list, int maximum) {
		
		for (int i = 0; i < maximum; i++) {
			TextIO.putln((i+1) + ". " + list[i]);
		}
		if(maximum < 0){
			for(int i = 0; i< list.length; i++){
				TextIO.putln((i+1) + ". " + list[i]);
				
			}
		}
	}

	/**
	 * Appends or prepends the title
	 * 
	 * @param list
	 * @param title
	 * @param prepend
	 *            if true, prepend the title otherwise append the title
	 * @return a new list with the title prepended or appended to the original
	 *         list
	 */
	public static String[] add(String[] list, String title, boolean prepend) {
		
		String [] tmp = new String [list.length + 1];
		
		if(prepend == true){
			tmp[0] = title;
			for(int i = 1; i < tmp.length; i++){
				tmp[i] = list[i-1];
			}}
			else if(prepend == false){
				tmp[tmp.length-1] = title;
				for(int i = 0; i< tmp.length -1; i++){
					tmp[i] = list[i];
				}
		
			}
		
		
		
		return tmp;
	}

	/**
	 * Returns a new list with the element at index removed.
	 * 
	 * @param list
	 *            the original list
	 * @param index
	 *            the array index to remove.
	 * @return a new list with the String at position 'index', absent.
	 */
	public static String[] discard(String[] list, int index) {
		
		String [] tmp = new String [list.length - 1];
		for(int i = 0; i<tmp.length; i++){
			tmp[i] = list[i<index ? i : i+1];
		
		}list = tmp;
		
		return list;
		
	}

}
