/**
 * @author Ashutosh Mishra
 * @desc just a sample file to check the Driver and Action class 
 * under org.nng.automation.utils package.
 * 
 */

import java.util.List;

import org.nng.utils.Excel;

public class Examples {

	/*
	 * =================================
	 * Entry Point
	 * =================================
	 */
		public static void main(String[] args) throws Exception {
			Examples example = new Examples();	
			
			// Implementing Excel Utility
			example.excelExample();
		}
	
	/*
	 * =================================
	 * Utility Implementation Code
	 * =================================
	 * */
		public void printData(List<String> colData) throws Exception {
			// Print the results.
				System.out.println("DATA >>>> Len="+ colData.size());
				for(String str :  colData) { System.out.println(str); }
			// End
			return;
		}
		
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
	
/*EOClass*/
}
