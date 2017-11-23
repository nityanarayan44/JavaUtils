/**
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @import org.nng.automation.utils.*; [Driver, Action]
 * @Desc:
 * Implements the AndroidDriver.
 * this is a sample test, Derives a sample Calculator Addition.
 * 
 * Appium Server: 1.6.5
 * Jar Files: 	Selenium Server Stnadalone 3.4.0
 * 				appium java-client-5.0.0-BETA9
 * 				guava-22.0
 */

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

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
			
		// Normal Operation [2+9=]
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_2']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/op_add']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_9']")).click();
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/eq']")).click();
		// Get the Result [Expected sum Result:  11].
			System.out.println("Result: " + this.driver.androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.calculator2:id/result']")).getText());
			Assert.assertEquals("11", this.driver.androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.calculator2:id/result']")).getText() );
		
		// Wait
			Thread.sleep(5000);  
			
		// Special Operations [ cos(90) calculation]
			// Swipe from Right to Left;
				Dimension dim = this.driver.androidDriver.manage().window().getSize();
				System.out.printf("\n Current Dimentions: Width=%d, Height=%d \n", dim.width, dim.height);
				this.driver.androidDriver.swipe( 1020, 695, 30, 265, 10);
			// Choose Cos function
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/fun_cos']")).click();
			// slide back
				// [240,693][1080,1794] [Exact value.]
				// this.driver.androidDriver.pinch(150, 690);
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_7']")).click();
				Thread.sleep(3000);
			// choose a number to get its cos value.
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_9']")).click();
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_0']")).click();
			// again swipe to get the bracket
				this.driver.androidDriver.swipe( 1020, 695, 30, 265, 10);
			// closing bracket.	
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/rparen']")).click();
			// 
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_7']")).click();
				Thread.sleep(3000);
				
			// click on equals
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/eq']")).click();
				
		// Direct Operation.
				this.driver.androidDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.calculator2:id/formula']")).sendKeys("1+cos(-45)");
				this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/eq']")).click();
				//com.android.calculator2:id/formula
				
		// Do something extra. after this operation
				//this.driver.androidDriver.closeApp();
				
		// Wait to exit
			Thread.sleep(5000);  
			return;
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
			opt.put("autoGrantPermissions","true"); // Automatically grant the permission. No popups
			
			this.driver.getAndroidDriver(opt);
			return;
	  }
	
	  @AfterClass
	  public void afterClass() throws Exception {
		  System.out.println("Test Executed.");
		  //driver.androidDriver.quit();
	  }

}
