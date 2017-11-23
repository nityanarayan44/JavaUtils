import java.util.HashMap;
import java.util.Map;

import org.nng.automation.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class AutomationTest {
	
	// Global
	public Driver driver = new Driver(); 
	
	// Selenium driver for web browser Testing
	public void testWeb() throws Exception {
		//Initiate the web driver
			//this.driver.getWebDriver("IE", "C:\\DRIVERS\\IEDriverServer_Win32_3.7.0\\IEDriverServer.exe");		
		// Operations on driver object 
			this.driver.maximizeBrowser();
			this.driver.open("https://www.google.com?q=earth");
		//exit;
			this.driver.quitDriver();
		//
			return;
	}
	
	public void testAndroid() throws Exception {
		
		// AutomationTest at = new AutomationTest();
		// get the driver
			Map<String, String> opt = new HashMap<String, String>();
			opt.put("url", 				"http://127.0.0.1:4723/wd/hub/");
			opt.put("platformName", 	"Android");
			opt.put("platformVersion", 	"8.0.0");
			opt.put("deviceName", 		"emulator-5554");
			opt.put("appPackage", 		"com.android.calculator2");
			opt.put("appActivity",		"com.android.calculator2.Calculator");
			
			//this.driver.getAndroidDriver(opt);
			
		// Operation
			//at.androidDriver.findElement(By.xpath("//android.widget.Button[@text=\"2\"]")).click();
			//at.androidDriver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/op_add']")).click();
			//at.androidDriver.findElement(By.xpath("//android.widget.Button[@text='5']")).click();
			//at.androidDriver.findElement(By.xpath("//android.widget.Button[@text='=']")).click();
			//this.driver.androidDriver.findElement(By.xpath("//*[@class='android.widget.TextView' and @index='0']")).sendKeys("2+5");
			Thread.sleep(4000);
			
		// Exit
			//this.driver.androidDriver.quit();
	}
	
	public static void main(String[] args) throws Exception {
		//(new AutomationTest()).testAndroid();
		AutomationTest at = new AutomationTest();
		at.testWeb();
	}

} /*EOClass*/

// USED [[this time]Till 17 Nov 2017 i used Recent Versions]
// Selenium Standalone Server 3.4.0 
// Chrome Driver 2.33
// Gecho Driver 2.19
// IE Driver 3.7.0

