/**
 * @author Ashutosh Mishra
 * @desc just a sample file to check the Driver and Action class 
 * under org.nng.automation.utils package.
 * 
 */

import java.util.List;

import org.nng.automation.utils.Action;
import org.nng.automation.utils.Excel;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.WebDriver;

public class Auto {

	public static void main(String[] args) throws Exception {
		Excel excel = new Excel("E:\\OFFICE-WORKPLACE\\__TestPoint\\FLEXI-CRM-Scenario.xlsx");
		
		//;
		List<String> data = excel.getRowData("Login", 5);
		System.out.println("DATA >>>> Len="+ data.size());
		for(String str :  data) { System.out.println(str); }
	}
	
	public void stup1() throws InterruptedException {
		WebDriver wdriver = null;
		Driver d = new Driver();
		Action a = new Action();
		
		String driverPath = "E:\\OFFICE-WORKPLACE\\__TestPoint\\Automation\\FW_Samples\\BHS_Regression\\Lib\\chromedriver.exe";
		//init webdriver for chrome
		wdriver = d.getWebDriver("ChromE", driverPath, d.getdefaultChromeOptionWithIncognito());
		
		//surf
		//if(driver != null)
			wdriver.get("http://google.com");
			
			try {
				System.out.println( "Screen capture: " + a.captureBrowserScreenshot(wdriver, "E:\\OFFICE-WORKPLACE\\") );
				
			}catch(Exception e) {
				//
				System.out.println("Problem in capturing webdriver Screenshot.");
				e.printStackTrace();
			}
		
		//sleep
		Thread.sleep(5000);
		
		//Close and Exit
		wdriver.close();
		wdriver.quit();
	}

}
