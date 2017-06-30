package correlation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Simulation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = "Simulation Order List.xls";
		FileInputStream fs = new FileInputStream(new File(filename));
		Workbook wb = new HSSFWorkbook(fs);
		Sheet sh = wb.getSheetAt(0);
		
		int rowNum = sh.getLastRowNum()+1;
		
        
        //read in all the order list, part list and location data from the excel sheets
		Vector orderData = new Vector();
		for(int i = 0; i<rowNum; i++)
			orderData.add(new Vector());
		
		for(int i = 0; i<rowNum; i++){
			HSSFRow row = (HSSFRow) sh.getRow(i);
			for(int j = 0; j<row.getLastCellNum(); j++){
				HSSFCell cell = row.getCell(j);
				((Vector) orderData.get(i)).add((int) cell.getNumericCellValue());
			}
		}
		
		int[] top50ByFreq = new int[89];
		Sheet sh9 = wb.getSheetAt(1);
		
		for(int i = 0; i<top50ByFreq.length; i++){
			HSSFRow row = (HSSFRow) sh9.getRow(i);
			HSSFCell cell = row.getCell(0);
			top50ByFreq[i] = (int) cell.getNumericCellValue();
		}
		
		wb.close();
		fs.close();
		
		Vector binLocationList = new Vector();
		Vector binLocationListX = new Vector();
		Vector binLocationListY = new Vector();
		
		String filename1 = "bin distances.xlsx";
		FileInputStream fs1 = new FileInputStream(new File(filename1));
		Workbook wb1 = new XSSFWorkbook(fs1);
		Sheet sh1 = wb1.getSheetAt(0);
		Iterator<Row> iterator = sh1.iterator();		
		
		while(iterator.hasNext()){			
			Row nextRow = iterator.next();
			if(nextRow.getRowNum() == 0)
				continue;
			
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();

				switch(cell.getColumnIndex()){
				case 0: binLocationList.add((String) cell.getStringCellValue());
				break;
				case 1: binLocationListX.add((int) cell.getNumericCellValue());
				break;
				case 2: binLocationListY.add((int) cell.getNumericCellValue());
				break;
				}
			}
		}
		
		wb1.close();
		fs1.close();
		
		Vector partList = new Vector();
		Vector partLocationList = new Vector();
		
		String filename2 = "bin.xlsx";
		FileInputStream fs2 = new FileInputStream(new File(filename2));
		Workbook wb2 = new XSSFWorkbook(fs2);
		Sheet sh2 = wb2.getSheetAt(2);
		Iterator<Row> iterator1 = sh2.iterator();		
		
		
		while(iterator1.hasNext()){			
			Row nextRow = iterator1.next();
			if(nextRow.getRowNum() == 0)
				continue;
			
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();

				switch(cell.getColumnIndex()){
				case 0: partList.add((int) cell.getNumericCellValue());
				break;
				case 1: partLocationList.add((String) cell.getStringCellValue());
				break;
				}
			}
		}
        
        //read in assigned improved bin locations that cover top 70% of all orders
		Sheet sh30 = wb2.getSheetAt(3);
		Iterator<Row> it1 = sh30.iterator();
		Vector improvedPartList = new Vector();
		Vector improvedPartLocationList = new Vector();
		while(it1.hasNext()){
			Row nextRow = it1.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				switch(cell.getColumnIndex()){
				case 0: improvedPartList.add((int) cell.getNumericCellValue());
				break;
				case 1: improvedPartLocationList.add((String) cell.getStringCellValue());
				break;
				}
			}
			
		}
		
		wb2.close();
		fs2.close();	
		
		for(int i = 0; i<partList.size(); i++){
			if(partLocationList.elementAt(i).toString().contains("-")){
				String s = remove(partLocationList.elementAt(i).toString(), '-');
				partLocationList.removeElementAt(i);
				partLocationList.add(i, s);
			}	
		}
		
		for(int i = 0; i<binLocationList.size(); i++){
			if(binLocationList.elementAt(i).toString().contains("-")){
				String s = remove(binLocationList.elementAt(i).toString(), '-');
				binLocationList.removeElementAt(i);
				binLocationList.add(i, s);
			}
		}
		
		
		Order[] orderList = new Order[orderData.size()];
		
		int count = 0;
		
        
        //create coordinate arrays in order to calculate individual and total distances for current and improved locations
		while(count<orderData.size()){
			int[] items = new int[((Vector) orderData.get(count)).size()];
			for(int i = 0; i<items.length; i++)
				items[i] = (int) ((Vector) orderData.get(count)).get(i);
			
			int[] x = new int[items.length+1];
			int[] y = new int[items.length+1];
			int[] improvedX = new int[items.length+1];
			int[] improvedY = new int[items.length+1];
			x[0] = 0;
			y[0] = 4;
			improvedX[0] = 0;
			improvedY[0] = 4;
			for(int i = 0; i<items.length; i++){
				for(int j = 0; j<partList.size(); j++){
					if(items[i] == ((int) partList.elementAt(j))){
						for(int k = 0; k<binLocationList.size(); k++){
							if(partLocationList.elementAt(j).equals(binLocationList.elementAt(k))){
								x[i+1] =  (int) binLocationListX.elementAt(k);
								y[i+1] = (int) binLocationListY.elementAt(k);
							}
						}
					}
				}
			}
			
			for(int i = 0; i<items.length; i++){
				for(int j = 0; j<improvedPartList.size(); j++){
					if(items[i] == ((int) improvedPartList.elementAt(j))){
						for(int k = 0; k<binLocationList.size(); k++){
							if(improvedPartLocationList.elementAt(j).equals(binLocationList.elementAt(k))){
								improvedX[i+1] = (int) binLocationListX.elementAt(k);
								improvedY[i+1] = (int) binLocationListY.elementAt(k);
							}
						}
					}
				}
			}
			orderList[count] = new Order(items,x,y,improvedX,improvedY);
			count++;
		}
		
		int totalDistance = 0;
		int improvedTotal = 0;
		int[] distances = new int[orderList.length];
		int[] improvedDistances = new int[orderList.length];
		
		count = 0;
		
        
        //calculate distances using helper functions provided
		while(count<orderList.length){
			distances[count] = distanceTraveled(orderList[count].xCoordinates, orderList[count].yCoordinates);
			totalDistance+=distances[count];
			improvedDistances[count] = distanceTraveled(orderList[count].improvedXCoordinates, orderList[count].improvedYCoordinates);
			improvedTotal+=improvedDistances[count];
			count++;			
		}
		System.out.println("Total Distance: " + totalDistance);
		System.out.println("Improved Total: " + improvedTotal);
	}
    
    //helper function to calculate distance
	public static int distanceTraveled(int[] xCoord, int[] yCoord){
		int val = 0;
		
		for(int i = 0; i<xCoord.length; i++){
			if((i+1)<xCoord.length)
				val+=rectilinearDist(xCoord[i],xCoord[i+1],yCoord[i],yCoord[i+1]);
			else
				val+=rectilinearDist(xCoord[i],0,yCoord[i],4);
		}
		
		return val;
	}
	
	public static int rectilinearDist(int x1, int x2, int y1, int y2){
		return (Math.abs(x2-x1) + Math.abs(y2-y1));
	}
	
	public static String remove(String s, char b) {

	    String word = "";

	    for (int i = 0; i < s.length(); i++){
	        if (s.charAt(i) != b){
	            word += s.charAt(i);
	        }
	    }
	    return word;
	}
	
}
