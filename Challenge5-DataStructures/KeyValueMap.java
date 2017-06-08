//UIUC CS125 SPRING 2014 MP. File: KeyValueMap.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000
/**
 * @author dkaraca2
 */
/**
 * 
 */
import java.awt.Color;

public class KeyValueMap { // aka Dictionary or Map
private String [] map_key = new String[0];
private Color [] map_color = new Color[0];
	/**
	 * Adds a key and value. If the key already exists, it replaces the original
	 * entry.
	 */
	public void add(String key, Color value) {
		String[] a = new String[map_key.length+1];
		a[0] = key;
		for(int i = 1; i< a.length; i++){
			a[i] = map_key[i-1];
		}map_key = a;
		
		Color[] b = new Color[map_color.length+1];
		b[0] = value;
		for(int i = 1; i< a.length; i++){
			b[i] = map_color[i-1];
		}map_color = b;
		
		
		
	}

	/**
	 * Returns particular Color object previously added to this map.
	 */
	public Color find(String key) {
		Color result = null;
		for(int i = 0; i< map_color.length; i++){
		if( map_color[i].equals(key)){result = map_color[i];}
		}return result;
	}

	/**
	 * Returns true if the key exists in this map.
	 */
	public boolean exists(String key) {
		boolean yes = false;
		for(int i = 0; i< map_key.length; i++){
			if(map_key[i].contains(key)) yes = true;
		}return yes;
	}

	/**
	 * Removes the key (and the color) from this map.
	 */
	public void remove(String key) {
		String[] a = new String[map_key.length-1];
		for(int i = 0; i< map_key.length; i++){
			a[i] = map_key[i];
			if(map_key[i].contains(key))a[i] = "";
		}map_key = a;
		
		Color[] b = new Color[map_color.length-1];
		for(int i = 0; i< map_key.length; i++){
			b[i] = map_color[i];
			if(map_key[i].contains(key))b[i] = null;
		}map_color = b;
	}

}
