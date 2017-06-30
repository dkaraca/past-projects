package correlation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OrderList {

	@SuppressWarnings({ "deprecation", "null" })
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "Same Transaction IDs.xls";
		FileInputStream fs = new FileInputStream(new File(filename));
		
		Workbook wb = new HSSFWorkbook(fs);
		Sheet sh = wb.getSheetAt(1);
		Iterator<Row> iterator = sh.iterator();
		
		int[] partNumbers = new int[sh.getLastRowNum()+1];
		int[] count = new int[sh.getLastRowNum()+1];
		
        //read in order frequency data from excel sheet
		while(iterator.hasNext()){			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				
				
				switch(cell.getColumnIndex()){
				case 0: partNumbers[cell.getRowIndex()] = (int) cell.getNumericCellValue();
				break;
				case 1: count[cell.getRowIndex()] = (int) cell.getNumericCellValue();
				break;
				}
			}	
		}
		
        //sort the items by order frequency in descending order
		quickSort(count,partNumbers,0,count.length-1);
		
		int[] listByFreq = new int[partNumbers.length];
		for(int i = 0; i<partNumbers.length; i++)
			listByFreq[i] = partNumbers[i];
		
        //initialize and calculate frequencies and cumulative frequencies of items
        //189 items for fTop and cfTop to cover 70% of all orders
		double[] f = new double[count.length];
		double[] cf = new double[count.length];
		double[] fTop = new double[189];
		double[] cfTop = new double[189];
		
		int totalNoOrders = 0;
		for(int i = 0; i<count.length; i++)
			totalNoOrders+=count[i];
		
		int totalNoTopOrders = 0;
		for(int i = 0; i<189; i++)
			totalNoTopOrders+=count[i];
		
		for(int i = 0; i<count.length; i++)
			f[i] = (double) count[i]/totalNoOrders;
		
		for(int i = 0; i<189; i++)
			fTop[i] = (double) count[i]/totalNoTopOrders;
		
		for(int i = 0; i<f.length; i++){
			if(i == 0)
				cf[i] = f[i];
			else
				cf[i] = cf[i-1]+f[i];
		}
		
		for(int i = 0; i<fTop.length; i++){
			if(i == 0)
				cfTop[i] = fTop[i];
			else
				cfTop[i] = cfTop[i-1] + fTop[i];
		}
		
        //create a first ticket for each simulated order depending on cumulative frequency of each order to be picked
		int[] firstTickets = new int[1000];
		int orderCount = 0;
		
		while(orderCount<1000){
			double rand = Math.random();
			for(int i = 0; i<cfTop.length; i++){
				if(rand<=cfTop[i]){
					
						firstTickets[orderCount] = partNumbers[i];
						//System.out.println(orderCount + " " + partNumbers[i] + " " + cfTop[i] + " " + rand);
						orderCount++;
						break;
					
				}
			}
		}
		
		
		Sheet sh2 = wb.getSheetAt(2);
		Iterator<Row> iterator2 = sh2.iterator();
		
		int rowNum = sh2.getLastRowNum()+1;
		int colNum = 189;
		
        //read in the correlation data 
		int[][] correlation = new int[rowNum][colNum];
		
		for(int i = 0; i<rowNum; i++){
			HSSFRow row = (HSSFRow) sh2.getRow(i);
			for(int j = 0; j<colNum; j++){
				HSSFCell cell = row.getCell(j);
				if(cell!=null){
				String value =  String.valueOf(cell);
				correlation[i][j] = (int) Double.parseDouble(value);
				}
			}
		}
				
		int[][]allOrders = new int[1000][50];
		int ordersCompleted = 0;
		
        
        //add items to each order by using correlation data and each correlated cumulative frequency
		while(ordersCompleted<1000){
			allOrders[ordersCompleted][0] = firstTickets[ordersCompleted];
			int firstTotal = 0;
			for(int i = 0; i<count.length; i++){
				if(partNumbers[i] == firstTickets[ordersCompleted])
					firstTotal = count[i];
			}
			
			int rowIndex = 0;
			int nextOrder = 1;
			for(int i = 0; i<correlation.length; i++){
				if(firstTickets[ordersCompleted] == correlation[i][0])
					rowIndex = i;
			}
			for(int i = 1; i<correlation[rowIndex].length; i++){
				double r = Math.random();
				if((r<= ((double) correlation[rowIndex+1][i]/firstTotal)) && allOrders[ordersCompleted][0]!=correlation[rowIndex][i]){
					allOrders[ordersCompleted][nextOrder] = correlation[rowIndex][i];
					nextOrder++;
				}				
			}
			ordersCompleted++;
		}
		
		Vector orders = new Vector();
		
		for(int i = 0; i<allOrders.length; i++)
			orders.add(new Vector());
		
		for(int i = 0; i<allOrders.length; i++){
			for(int j = 0; j<allOrders[i].length; j++){
				if(allOrders[i][j] != 0){
					((Vector) orders.get(i)).add(allOrders[i][j]);
				}
			}
		}
		
		wb.close();
		fs.close();
		
		Workbook wb2 = new HSSFWorkbook();
		CreationHelper ch = wb2.getCreationHelper();
		Sheet sh3 = wb2.createSheet("Simulation Order List");
		
		for(int i = 0; i<orders.size(); i++){
			Row row = sh3.createRow(i);
			for(int j = 0; j<((Vector) orders.get(i)).size(); j++){
				Cell cell = row.createCell(j);
				cell.setCellValue( (int) ((Vector) orders.get(i)).get(j));;
			}
		}
		
		Sheet sh4 = wb2.createSheet("List By Frequencies");
		for(int i = 0; i<listByFreq.length; i++){
			Row row = sh4.createRow(i);
			Cell cell= row.createCell(0);
			cell.setCellValue((int) listByFreq[i]);
		}
		
		FileOutputStream fo = new FileOutputStream("Simulation Order List.xls");
		wb2.write(fo);
		fo.close();
		
	}

    //helper function for quicksort algorithm
	static int partition(int arr[], int arr1[], int left, int right)
	{
	      int i = left, j = right;
	      int tmp;
	      int tmp1;
	      int pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] > pivot)
	                  i++;
	            while (arr[j] < pivot)
	                  j--;
	            if (i <= j) {
	                  tmp = arr[i];
	                  tmp1 = arr1[i];
	                  arr[i] = arr[j];
	                  arr1[i] = arr1[j];
	                  arr[j] = tmp;
	                  arr1[j] = tmp1;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
    
    //helper function to sort items
	static void quickSort(int arr[], int arr1[], int left, int right) {
	      int index = partition(arr, arr1, left, right);
	      if (left < index - 1)
	            quickSort(arr, arr1, left, index - 1);
	      if (index < right)
	            quickSort(arr, arr1, index, right);
	}

    
	static boolean contains(int[] arr, int number){
		boolean result = false;
		for(int i = 0; i<arr.length; i++){
			if(arr[i] == number)
				result = true;
		}
		return result;
	}	

	
	public static boolean containsItem(int[] arr, int n){
		boolean result = false;
		
		for(int i = 0; i<arr.length; i++){
			if(arr[i] == n)
				result = true;
		}
		return result;
	}
}


