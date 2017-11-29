package Examples;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.nng.automation.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class TestD_RemoteWebDriverTest {

	/* 
	 * +====================================+
	 * | Global Instance					|
	 * +====================================+
	 */
		public Driver driver ;
	
	/* 
	 * +====================================+
	 * | Test [Test with MobileWebBrowser]	|
	 * | Your Test Goes here....			|			
	 * +====================================+
	 */
	  @Test (priority = 1, enabled = true)
	  public void f() throws Exception {
		// Wait
			Thread.sleep(5000);
			
		// Accepting the chrome terms
			//System.out.println("\n\n Accept Terms&Cond Page Source Code: \n " + this.driver.androidDriver.getPageSource() );
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.chrome:id/terms_accept']")).click();
			Thread.sleep(1000);
			
		// Accept Data saver.
			//System.out.println("\n\n Accept Data Saver Page Source Code: \n " + this.driver.androidDriver.getPageSource() );
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.chrome:id/next_button']")).click();
			
		// Accept the Selected Account [List of Account is shown]
			//System.out.println("\n\n Account Selection Page Source Code: \n " + this.driver.androidDriver.getPageSource() );
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.chrome:id/positive_button']")).click();
			
		// Send Logs status
			//System.out.println("\n\n Chrome Synchronisation Source Code: \n " + this.driver.androidDriver.getPageSource() );
			this.driver.androidDriver.findElement(By.xpath("//android.widget.Button[@text='OK, GOT IT' and @resource-id='com.android.chrome:id/positive_button']")).click();
			
		// Do something extra. after this operation
			Thread.sleep(2000);
			//System.out.println("\n\n Main Chrome Page Source Code: \n " + this.driver.androidDriver.getPageSource() );
			
		// Assigning AndroidDriver to WebDriver also
			//this.driver.webDriver = this.driver.androidDriver;
		
		//
		// Normal Operation [Search a keyword then scroll down]
		//
			//Wait
				Thread.sleep(1000);
				
			// Click on url textBox
				this.driver.androidDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/search_box_text']")).click();
			
			// Enter few text
				System.out.println("Current EditText Value >>> " + this.driver.androidDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")).getText());
				this.driver.androidDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")).clear();
				this.driver.androidDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")).sendKeys("This is Earth Buddy");
				System.out.println("Current EditText Value >>> " + this.driver.androidDriver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")).getText());
			
			// Pressing Enter [KeyCode 66 for Enter]
				this.driver.androidDriver.pressKeyCode(66);
			
			// Wait for scroll down
				Thread.sleep(3000);
				
			// Scrolling down
//				this.driver.androidDriver.swipe( ((this.driver.androidDriver.manage().window().getSize()).width / 2) - 200, 
//												 (this.driver.androidDriver.manage().window().getSize()).height - 200,
//												 ((this.driver.androidDriver.manage().window().getSize()).width / 2) - 200, 
//												 200, 
//												 10);
				
				System.out.println( "\n\n\n SRC >> \n\n" + this.driver.webDriver.getPageSource()) ;
			// Lock Device
				//this.driver.androidDriver.lockDevice();
				
		// Last
		// Wait to exit
			Thread.sleep(5000);
			return;
	  }
 
	/* 
	 * +============================================+
	 * | TestNG Configuration Settings				|
	 * | Just for Initialization and finalization 	|
	 * +============================================+
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
			// Browser Application
			opt.put("appPackage", 		"com.android.chrome");
			opt.put("appActivity",		"com.google.android.apps.chrome.Main");
			
			
			//this.driver.getRemoteDriver(opt);
			this.driver.getAndroidDriver(opt);
			
			this.driver.webDriver = this.driver.androidDriver;
			
			return;
	  }
	
	  @AfterClass
	  public void afterClass() throws Exception {
		  System.out.println("Test Executed.");
		  this.driver.androidDriver.quit();
	  }

}
