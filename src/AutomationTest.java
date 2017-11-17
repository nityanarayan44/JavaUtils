import org.nng.automation.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@SuppressWarnings("unused")
public class AutomationTest extends Driver {
	
	// USED [[this time]Till 17 Nov 2017 i used Recent Versions]
	// Selenium Standalone Server 3.4.0 
	// Chrome Driver 2.33
	// Gecho Driver 2.19
	// IE Driver 3.7.0
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AutomationTest at = new AutomationTest();
		
		//Initiate the web driver
		//WebDriver driver = at.getWebDriver("CHROME", "C:\\DRIVERS\\chromedriver_win32_new\\chromedriver.exe");
		at.getWebDriver("FIREFOX", "E:\\MY-WORKPLACE\\__GIT_PROJECTS\\__Dependencies\\WebDrivers\\geckodriver.exe");
		//WebDriver driver = at.getWebDriver("IE", "C:\\DRIVERS\\IEDriverServer_Win32_3.7.0\\IEDriverServer.exe");
		//WebDriver driver = at.getWebDriver("EDGE", "C:\\DRIVERS\\IEDriverServer_x64_3.7.0\\IEDriverServer.exe");
		
		// Operations on driver object 
		at.maximizeBrowser();

		at.driver.get("https://www.google.com?q=earth");
		at.driver.switchTo().activeElement().sendKeys(Keys.ENTER);
		//driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//Wait
		System.out.println("Waiting....");
		Thread.sleep(5000);
		System.out.println("Exiting....Exited");
		
		//exit;
		at.quitDriver();
	}

}
