//UIUC CS125 SPRING 2014 MP. File: GeocacheList.java, CS125 Project: Challenge5-DataStructures, Version: 2014-03-07T20:15:57-0600.005593000
/**
 * Complete the following GeocacheList, to ensure all unit tests pass.
 * There are several errors in the code below
 * @author dkaraca2
 * Hint: Get the Geocache class working and passing its tests first.
 */
public class GeocacheList {
	private Geocache[] data = new Geocache[0];
	private int size = 0;

	public Geocache getGeocache(int i) {
		return data[i];
	}

	public int getSize() {
		return size;
	}

	public GeocacheList() {
	
		}
		
		
		
	
	public GeocacheList(GeocacheList other, boolean deepCopy) {
		if(deepCopy){
			GeocacheList result = new GeocacheList();
			for(int i = 0; i<other.size; i++){
				Geocache[] tmp = new Geocache[other.size];
				tmp[i] = other.data[i];
				result.data = tmp;
				size++;
			}
		}else{
			GeocacheList result = new GeocacheList();
			result.data = other.data; 
			size++;
		}
			
				
	
		
		}
	

	public void add(Geocache p) {
		size++;
		if (size > data.length) {
			Geocache[] old = data;
			data = new Geocache[size * 2];
			for (int i = 0; i < old.length; i++)
				data[i] = old[i];
		}
		data[size-1] = p;
	}

	public void removeFromTop() {
		int a = data.length -1;
		size--;
		Geocache[] p = new Geocache[a];
		for(int i = 0; i< a; i++){
			p[i] = data[i+1];
		}data = p;
		
		
	}

	public String toString() {
		StringBuffer s = new StringBuffer("GeocacheList:");
		for (int i = 0; i < size; i++) {
			if (i > 0)
				s.append(',');
			s.append(data[i]);
		}
		return s.toString();
}	}
