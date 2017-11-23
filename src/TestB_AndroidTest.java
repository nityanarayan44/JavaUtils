/**
 * @author Ashutosh Mishra
 * @import org.nng.automation.utils.*; [Driver, Action]
 * @Desc:
 * Implements the AndroidDriver.
 * this is a sample test, Derives a sample Calculator Addition.
 * 
 * Jar Files: 	Selenium Server Stnadalone 3.4.0
 * 				appium java-client-5.0.0-BETA9
 * 				guava-22.0
 */

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class TestB_AndroidTest {
	// Global
		public Driver driver ;
	
	/* ==============================
	 *-| Test						|-
	 * ==============================
	 */
	  @SuppressWarnings("deprecation")
	  @Test (priority = 1, enabled = true)
	  public void f() throws Exception {
		// Wait
			Thread.sleep(5000);  
		// Operation [2+9=]
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_2']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/op_add']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_9']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/eq']")).click();
		// Get the Result [Expected sum Result:  11].
			System.out.println("Result: " + this.driver.androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.calculator2:id/result']")).getText());
			Assert.assertEquals("11", this.driver.androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.calculator2:id/result']")).getText() );
		// Wait
			Thread.sleep(5000);  
					
		
	  }
 
	/* ==============================
	 *-| TestNG Configurations		|-
	 * ==============================
	 */
	  
	  @BeforeClass
	  public void beforeClass() throws Exception {
		  	driver = new Driver();
			Map<String, String> opt = new HashMap<String, String>();
			opt.put("url", 				"http://127.0.0.1:4723/wd/hub/");
			opt.put("platformName", 	"Android");
			opt.put("platformVersion", 	"8.0.0");
			opt.put("deviceName", 		"emulator-5554");
			opt.put("appPackage", 		"com.android.calculator2");
			opt.put("appActivity",		"com.android.calculator2.Calculator");
			this.driver.getAndroidDriver(opt);
			return;
	  }
	
	  @AfterClass
	  public void afterClass() throws Exception {
		  System.out.println("Test Executed.");
		  driver.androidDriver.quit();
	  }

}
