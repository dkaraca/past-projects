package correlation;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Order {
	int[] items;
	int[] xCoordinates;
	int[] yCoordinates;
	int[] improvedXCoordinates;
	int[] improvedYCoordinates;

	//constructor for Order class, creates an order with specified items, improved and current X and Y coordinates
	public Order(int[] items, int[] xCoordinates, int[] yCoordinates, int[] improvedXCoordinates, int[] improvedYCoordinates){
		this.items = items;
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		this.improvedXCoordinates = improvedXCoordinates;
		this.improvedYCoordinates = improvedYCoordinates;
	}
}
