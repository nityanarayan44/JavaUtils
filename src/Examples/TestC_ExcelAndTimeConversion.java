package Examples;
/**
 * @author Ashutosh Mishra
 * Examples.java
 * @desc just a sample file to implement an Example for the utilities.
 * 
 */

import java.util.List;

import javax.swing.JOptionPane;

import org.nng.utils.Excel;
import org.nng.utils.*;

public class TestC_ExcelAndTimeConversion {

	/*
	 * =================================
	 * Entry Point
	 * =================================
	 */
		public static void main(String[] args) throws Exception {
			TestC_ExcelAndTimeConversion example = new TestC_ExcelAndTimeConversion();
			if(args.length == 0) {
				System.out.println( "Current Date : " + (new DateAndTime()).getCurrentDateAsD_M_Y());
				//example.help();
			}else {	
				// Time Conversion : org.nng.utils package
				//example.conversion();
				System.out.println( "Current Date : " + (new DateAndTime()).getCurrentDateAsD_M_Y());
				// Excel File : org.nng.utils package
				//example.excelExample();
			}
		}
	
	/*
	 * =================================
	 * Utility Implementation Code
	 * =================================
	 * */
		public void printData(List<String> data) throws Exception {
			// Print the results.
				System.out.println("DATA >>>> Len="+ data.size());
				for(String str :  data) { System.out.println(str); }
			// End
			return;
		}
		
		/*
		 * Unit Conversion Utility
		 */
		public void conversion() throws Exception {
			
			Conversion uc = new Conversion();
			//conversion
			long seconds = 3600; // 1 Hour
			System.out.println(seconds+ " Seconds to HMS: " + uc.secondsToHMSTime(seconds) );
			long minutes = 68; // 1 hours 8 minutes
			System.out.println(minutes+ " Minutes to HMS: " + uc.minutesToHMSTime(minutes) );
			
			// Execution Finished.
		}
		/*
		 * Excel Utility
		 */
		public void excelExample() throws Exception {
			// Excel Object creation
				// Excel File Format: XLSX
				//Excel excel = new Excel("./data/TC_128_addNewUserData.xlsx");
				
				// Excel File Format: XLS
				Excel excel = new Excel("./data/UserData2003Format.xls");
				
			// getting Data from an Excel file
				//Excel excel = new Excel("E:\\OFFICE-WORKPLACE\\__TestPoint\\FLEXI-CRM-Scenario.xlsx");
				//excel.printExcelSheet(0);
				
				// CASE: Get Row data by SheetIndex and rowIndex
				//List<String> data = excel.getRowData(0, 1);
			
				// CASE: Get Row data by SheetName and rowIndex
				//List<String> data = excel.getRowData("sheet1", 1);
			
				// CASE: Get Column data by Column Name
				List<String> colData = excel.getColumnData(0, "USERID");
				
			// Print the results.
				this.printData(colData);
		}
		
		/*
		 * ===================
		 * HELP function
		 * ===================
		 * 
		 */
		public void help() throws Exception {
			//
				String msg = "######## 	\n" +
							 "#  Help  #    \n" +
							 "##############################################\n" +
							 "# This JAR (Java Archive) is not meant to be  \n" +
							 "# an executable program. To use its API, you  \n" +
							 "# need to import this to your java program    \n" +
							 "##############################################\n" +
							 "# Available Packages are as follows           \n" +
							 "# 1- org.nng.utils.*                          \n" +
							 "# 2- org.nng.automation.utils.*               \n" +
							 "##############################################\n" +
							 "# @Author: Ashutosh Mishra [@github: nityanarayan44] \n" +
							 "# @License: MIT License 2017 \n" +
							 "# @Written: 10 Aug 2017     \n" +
							 "# @Modified: 23 Nov 2017   \n" +
							 "##############################################\n";
								
				javax.swing.JOptionPane.showMessageDialog(null, msg, "HELP : Jar Usage", JOptionPane.INFORMATION_MESSAGE);
			// Last
				return;
		}
	
/*EOClass*/
}
