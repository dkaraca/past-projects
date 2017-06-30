package correlation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Data {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		
		// Vectors to store transaction IDs and item IDs for further analysis are declared
		Vector transactionID = new Vector();
		Vector itemID = new Vector();
		Vector q = new Vector(); //quantity vector
		Vector count = new Vector();
		Vector f = new Vector(); //freqency vector
		Vector f30 = new Vector(); //frequency vector for top 30%
		Vector cf = new Vector(); //cumulative frequency vector
		Vector cf30 = new Vector(); //cumulative frequency vector for top 30%
		
		// Data file to be read in
		String filename = "Data.xlsx";
		FileInputStream fs = new FileInputStream(new File(filename));
		
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sh = wb.getSheetAt(0);
		Iterator<Row> iterator = sh.iterator();
		
		
		while(iterator.hasNext()){			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				
				// Cells in first column added to transactionID, cells in second column added to itemID vectors
				switch(cell.getColumnIndex()){
				case 0: transactionID.add(cell.getNumericCellValue());
				break;
				case 1: itemID.add(cell.getNumericCellValue());
				break;
//				case 2: quantity.add(cell.getNumericCellValue());
//				break;
				}
			}
			
		}
		wb.close();
		fs.close();
		
		// New workbook for output is declared
		Workbook wb2 = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		
		// Sheets for different analyses are created
		Sheet sh1 = wb2.createSheet("Same Transaction IDs");
		Sheet sh2 = wb2.createSheet("Order Frequencies");
		
		// Counters for analyses are declared
		int c = 0; // counter for "Same Transaction IDs"
		int l = 0; // counter to keep rows in order for output
		
		while(c<transactionID.size()){
			Vector sameTransaction = new Vector(); // Vector that stores itemIDs with same transaction IDs
			sameTransaction.add(itemID.get(c));
			 
			 // Loop starts at first item with that transaction ID, breaks when the next transaction ID is different
			 while((c+1) < transactionID.size() && transactionID.elementAt(c).equals(transactionID.elementAt(c+1))){
				sameTransaction.add(itemID.get(c+1));
				c++;
			 }
			 
			// sameTransaction vector is printed
			Row row = sh1.createRow((short) l);
			Cell cell = row.createCell(0);
			cell.setCellValue((Double) transactionID.elementAt(c));
			for(int i = 0; i<sameTransaction.size(); i++){
				Cell cell3 = row.createCell(i+1);
				cell3.setCellValue((Double) sameTransaction.elementAt(i));
			}
			
			c++; // update c, start the while loop again at the next different transaction ID
			l++; // update l, next line at Excel sheet
		}
		
		int counter = 0; // counter to keep track of item frequencies
		
		Vector frequentItems = new Vector(); // vector to add items to be included in the correlation analysis
		
		int o = 0; // counter to go through the entire itemID list 
		
		Vector itemFrequencyList = new Vector(); // new vector is created to avoid duplicates
		int newRow = 0; // counter to keep track of rows in output
		while(o<itemID.size()){
			
			for(int i = 0; i<itemID.size(); i++){
				if(itemID.elementAt(o).equals(itemID.elementAt(i)))
						counter++; // counter is increased by one to find frequency of each item
			}

			
			
			if(counter >= 49 && !frequentItems.contains(itemID.elementAt(o))) 
				frequentItems.add(itemID.elementAt(o));
			
			if(!itemFrequencyList.contains(itemID.elementAt(o))){ // if statement to avoid printing duplicate items
				itemFrequencyList.add(itemID.elementAt(o));
				count.add(counter);
				Row row1 = sh2.createRow(newRow);
				Cell cell1 = row1.createCell(0);
				cell1.setCellValue((Double) itemFrequencyList.lastElement());
				Cell cell2 = row1.createCell(1);
				cell2.setCellValue((int) counter);
				newRow++;
			}
			//System.out.println(counter);
			counter = 0; // reset counter
			o++; // move to the next item
		}
		
		// Create new sheet for Correlation analysis		
		Sheet sh3 = wb2.createSheet("Correlation");
		
		int rows = 0; // counter to keep order of rows at output
		int cells = 1; // counter to keep order of cells at output
		int b = 0; // counter to go through the entire itemID vector
		int cCounter = 0; // counter to keep track of correlation frequency
		
		Vector correlationDone = new Vector(); // vector to avoid duplicates 
			

		while(b<itemID.size()){
			
			if(!correlationDone.contains(itemID.elementAt(b))){
				
				Vector currentCorrelation = new Vector();
				currentCorrelation.add(itemID.elementAt(b));
				Vector cItemDone = new Vector();
				Vector cCount = new Vector();	
				int d = 0;
				int prev = d;
				System.out.println(transactionID.size() + " " + itemID.size());
				while(d<itemID.size()){
					
					if(itemID.elementAt(d).equals(itemID.elementAt(b))){
				
						int index = transactionID.indexOf(transactionID.elementAt(d),prev);
					
						for(int i = index; i<itemID.size(); i++){
							if(transactionID.elementAt(i).equals(transactionID.elementAt(d))){
								if(!itemID.elementAt(i).equals(itemID.elementAt(d))){
									currentCorrelation.add(itemID.elementAt(i));
									d = i;
								}
								d = i;
							}
						}
						prev = index;
					}
					d++;
				}
				
				for(int i = 1; i<currentCorrelation.size(); i++){
					if(frequentItems.contains(currentCorrelation.elementAt(i)) && !cItemDone.contains(currentCorrelation.elementAt(i))){
						cItemDone.add(currentCorrelation.elementAt(i));
						for(int j = 1; j<currentCorrelation.size(); j++){
							if(currentCorrelation.elementAt(i).equals(currentCorrelation.elementAt(j)))
								cCounter++;
						}
						cCount.add(cCounter);
						cCounter = 0;
					}
				}
							
			
			// row and cell declarations for output
			Row row2 = sh3.createRow(rows);
			Cell cell3 = row2.createCell(0);
			cell3.setCellValue((Double) currentCorrelation.firstElement());
			Row row3 = sh3.createRow(rows+1);
			
				for(int i = 0; i<cItemDone.size(); i++){
					Cell cell4 = row2.createCell(cells);
					cell4.setCellValue((Double) cItemDone.elementAt(i));
					Cell cell5 = row3.createCell(cells);
					cell5.setCellValue((int) cCount.elementAt(i));
					cells++;
				}
			rows+=2; // update rows
			cells=1; // update cells
			
			correlationDone.add(itemID.elementAt(b));
			}
		b++; // move to the next item in itemIDs list
		}
		
		// sheets to keep all itemIDs and transactionIDs are created		
		Sheet sh4 = wb2.createSheet("TransactionIDs");
		Sheet sh5 = wb2.createSheet("ItemIDs");
		
		// helper function removeDuplicates(Vector v) is used
		removeDuplicates(itemID);
		removeDuplicates(transactionID);
		
		// itemID and transactionID are printed to output sheets
		for(int i = 0; i<itemID.size(); i++){
			Row row5 = sh5.createRow((short) i);
			Cell cell5 = row5.createCell(0);
			cell5.setCellValue((Double) itemID.elementAt(i));
		}
		
		for(int i = 0; i<transactionID.size(); i++){
			Row row6 = sh4.createRow((short) i);
			Cell cell6 = row6.createCell(0);
			cell6.setCellValue((Double) transactionID.elementAt(i));
		}
		
		
		FileOutputStream fileOut = new FileOutputStream("Same Transaction IDs.xls");
		wb2.write(fileOut);
		fileOut.close();
	}
	
	// helper function to remove duplicate values
	public static void removeDuplicates(Vector v){
		Collection noDup = new LinkedHashSet(v);
		v.clear();
		v.addAll(noDup);
	}
}