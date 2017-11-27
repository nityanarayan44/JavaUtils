/**
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @import org.nng.automation.utils.*; [Driver, Action]
 * @Desc:
 * Implements the AppiumDriver for Mobile Web Browser.
 * this is a sample test, Derives a sample Calculator Addition.
 * 
 * Appium Server: 1.6.5
 * Jar Files: 	Selenium Server Stnadalone 3.4.0
 * 				appium java-client-5.0.0-BETA9
 * 				guava-22.0
 */

import org.testng.annotations.Test;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.Map;

public class TestE_AppiumDriver {
	// Global
		public Driver driver ;
	
	/* ==============================
	 *-| Test [MobileWebBrowser]	|-
	 * ==============================
	 */
	  @SuppressWarnings("deprecation")
	  @Test (priority = 1, enabled = true)
	  public void f() throws Exception {
		  	// Open an address
				this.driver.appiumDriver.get("https://www.google.co.in");
			
			// Wait for few seconds.
				Thread.sleep(3000);
				
			// Search Something
				this.driver.appiumDriver.findElement(By.name("q")).sendKeys("This is a Search String" + Keys.ENTER);
				
			// Printout the page source:
				System.out.println( "\n\n\n SRC >> \n\n" + this.driver.appiumDriver.getPageSource()) ;
		// Last
		// Wait to exit
			Thread.sleep(1000);
			return;
	  }
 
	/* ==============================
	 *-| TestNG Configurations		|-
	 * ==============================
	 */
	  
	  @BeforeClass
	  public void beforeClass() throws Exception {
		  /*
		   * Mandatory capability for android
		   * 1- DeviceName
		   * 2- PlatformName
		   * 3- udid
		   * */
		  	driver = new Driver();
			Map<String, String> opt = new HashMap<String, String>();
			opt.put("url", 				"http://127.0.0.1:4723/wd/hub/");
			opt.put("platformName", 	"Android");
			opt.put("platformVersion", 	"7.0");
			opt.put("deviceName", 		"ZY2242RDQX");
			opt.put(MobileCapabilityType.BROWSER_NAME, "Chrome");
			// Browser Application
			//opt.put("appPackage", 		"com.android.chrome");
			//opt.put("appActivity",		"com.google.android.apps.chrome.Main");
			
			
			//this.driver.getRemoteDriver(opt);
			this.driver.getAppiumDriver(opt);
			return;
	  }
	
	  @AfterClass
	  public void afterClass() throws Exception {
		  System.out.println("Test Executed.");
		  //this.driver.androidDriver.quit();
		  //this.driver.appiumDriver.quit();
	  }

}
