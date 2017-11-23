/**
 * @author Ashutosh Mishra
 * @import org.nng.automation.utils.*; [Driver, Action]
 * @Desc:
 * Implements the webDriver.
 * this is a sample test, Derives a sample Google Search Test.
 */

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.Keys;

public class TestA_WebDriverTest {
	// Global
		public Driver driver ;
	
	/* ==============================
	 *-| Test						|-
	 * ==============================
	 */
	  @Test (priority = 1, enabled = true)
	  public void f() throws Exception {
		  this.driver.maximizeBrowser();
		  this.driver.open("https://www.google.co.in?q=This+Is+Earth");
		  this.driver.webDriver.switchTo().activeElement().sendKeys(Keys.ENTER);
		  System.out.println("Page Title >>>> " + this.driver.webDriver.getTitle());
		  Thread.sleep(5000);
	  }
 
	/* ==============================
	 *-| TestNG Configurations		|-
	 * ==============================
	 */
	  
	  @BeforeClass
	  public void beforeClass() throws Exception {
		  driver = new Driver();
		  driver.getWebDriver("CHROME", "C:\\DRIVERS\\chromedriver_win32_new\\chromedriver.exe");
		  return;
	  }
	
	  @AfterClass
	  public void afterClass() throws Exception {
		  System.out.println("Test Completed.");
		  driver.quitDriver();
	  }

}
