import org.nng.automation.utils.Action;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.WebDriver;

public class Auto {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
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
