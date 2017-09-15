/**
 * @package org.nng.automation.utils
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc Provides Excel file utility under "org.nng.automation.utils" package
 * 
 * ----------------------
 * Division of this class
 * ----------------------
 *  - Read an excel file
 *  - Write and create an excel file
 *  
 *  TODO: Documentation of this class and functions.
 */

package org.nng.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unused")
public class Excel {

	//=============================================================
	// Variables
	//=============================================================
		private String excelFilePath 			= null;
		private FileInputStream inputStream 	= null;
		private FileOutputStream outputStream 	= null;
		private File file 						= null;
		private Workbook workbook 				= null ;
		private Cell cell 						= null;
		private Row row 						= null;
	
	//=============================================================
	// Functions
	//=============================================================
		
		public boolean validateExcelFile(String excelFilePath) throws Exception {
			boolean status = false;
			//check the extension of the given file.
			if (excelFilePath.endsWith("xlsx")) 	status = true;
			else if (excelFilePath.endsWith("xls")) status = true;
			else {
				status = false;
				throw new IllegalArgumentException("The specified file is not Excel file");
			}
			//If all okay then globally set path for the excel file
			this.excelFilePath = excelFilePath;
			return status;
		}
		
		public boolean isSheetExist(String sheetName) {
			return (workbook.getSheet(sheetName) != null) ? true : false;
		}
		
		public int getRowCount(String sheetName) {
			return ( (workbook.getSheet(sheetName)).getLastRowNum() - (workbook.getSheet(sheetName)).getFirstRowNum() ) + 1;		
		}
		
		public String getCellData(String sheetName, int rowNum, int colNum){
			return ( (workbook.getSheet(sheetName)).getRow(rowNum) ).getCell(colNum).getStringCellValue();
		}
		
		public ArrayList<String> getColumnData(String sheetName, String colName)
		{
			Sheet objSheet = workbook.getSheet(sheetName);
			int rowCount = objSheet.getLastRowNum() - objSheet.getFirstRowNum();
			int colIndex = 0;
			ArrayList<String> columnData = new ArrayList<String>();
			row = objSheet.getRow(0);

			for(int i=0;i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).getStringCellValue().equalsIgnoreCase(colName))
				{
					colIndex= row.getCell(i).getColumnIndex();
				}
			}		
			for(int i=1;i<rowCount+1;i++)
			{
				row = objSheet.getRow(i);
				columnData.add(row.getCell(colIndex).getStringCellValue());		
			}
			return columnData;
		}
		
		public void printSheet(String sheetName)
		{

			Sheet objSheet = workbook.getSheet(sheetName);

			int rowCount = objSheet.getLastRowNum() - objSheet.getFirstRowNum();

			// Create a loop over all the rows of excel file
			for(int i = 0; i< rowCount+1; i++)
			{
				row = objSheet.getRow(i);			
				for(int j=0; j < row.getLastCellNum();j++)
				{
					System.out.print(row.getCell(j).getStringCellValue() + " | "); 
				}
				System.out.println();
			}	
		}
		
		
		private void initXLSStream() throws FileNotFoundException, IOException, Exception {
			this.inputStream 	= new FileInputStream(this.excelFilePath);
			this.workbook 		= new XSSFWorkbook(inputStream);
		}
		
		private void initXLSXStream() throws FileNotFoundException, IOException, Exception {
			this.inputStream = new FileInputStream(excelFilePath);
			this.workbook = new HSSFWorkbook(inputStream);
		}
		
		
	
}