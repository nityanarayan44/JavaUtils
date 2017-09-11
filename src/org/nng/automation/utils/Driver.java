/**
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc Webdriver basic functionality under "org.nng.automation.utils" package
 */

package org.nng.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

@SuppressWarnings("unused")
public class Driver {
	
	//=============================================================
	// Private Variables
	//=============================================================
		private WebDriver driver 					= null;
		private DesiredCapabilities capabilities 	= null;
		private ChromeOptions options				= null;
		private String browserName					= null;
		private String[] chromeDefaultOptions 		= {"disable-extensions", "--start-maximized"};
		private String[] chromeIncognitoOptions 	= {"incognito","disable-extensions", "--start-maximized"};
		
	//=============================================================
	// Public: Functions [Main]
	//=============================================================
		
		// Get a Web driver, [with no args]
		public WebDriver getWebDriver(String browserName, String driverPath) {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName); 
				this.initDriver(browserName);
			}
			else this.driver = null;
			// Return webdriver
			return this.driver;
		}
		// Get a Web driver, [with chromeOptions]		
		public WebDriver getWebDriver(String browserName, String driverPath, ChromeOptions chromeOptions) {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setChromeOptions(chromeOptions);
				this.initDriver(browserName+"WithOptions");
			}
			else this.driver = null;
			// Return webdriver
			return this.driver;
		}

		// Get a Web driver, [with desired capabilities]
		public WebDriver getWebDriver(String browserName, String driverPath, DesiredCapabilities capabilities) {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setDesiredCapabilites(capabilities);
				this.initDriver(browserName+"WithCapabilities");
			}
			else this.driver = null;
			// Return webdriver
			return this.driver;
		}
		
		//Get the Browser Name for this driver object
		public String getBrowserName() {
			return this.browserName;
		}
		
		// Close the current driver
		public void closeDriver() {
			this.driver.close();
		}
		
		//Quit the current driver
		public void quitDriver() {
			this.driver.quit();
		}
		
	//=============================================================
	// Private: functions
	//=============================================================
		
		//Set the system property for webdriver
		private boolean setProperty(String browserName, String driverPath) {
			switch(browserName) {
				case "chrome"	: System.setProperty("webdriver.chrome.driver", driverPath); break;
				case "firefox"	: System.setProperty("webdriver.gecko.driver", 	driverPath); break;
				case "ie"		: System.setProperty("webdriver.ie.driver", 	driverPath); break;
				case "edge"		: System.setProperty("webdriver.edge.driver", 	driverPath); break;
				default 		: return false;
			}
			//returning the status
			return true;
		}
		
		//Set the chrome options for current object.
		private void setChromeOptions(ChromeOptions chromeOptions) {
			this.options = chromeOptions;
		}
		
		//Set the desired capabilities for current object.
		private void setDesiredCapabilites(DesiredCapabilities capabilities) {
			this.capabilities = capabilities;
		}
		
		//Set Browser for this driver object
		private void setDriver(String browserName) {
			this.browserName = browserName;
		}
		
		//Initializing the driver
		public boolean initDriver(String initWith) {
			switch(initWith) {
				case 	"chrome"					: this.initChrome(); 	
					break;
				case	"chromeWithOptions"			: this.initChromeWithOptions(this.options);
					break;
				case	"chromeWithCapabilities"	: this.initChromeWithDesiredCapabilities(this.capabilities);
					break;
				case	"firefox"					: this.initFirefox(); 	
					break;
				case	"firefoxWithCapabilities"	: this.initFirefoxWithDesiredCapabilities(this.capabilities); 	
					break;
				case	"edge"						: this.initEdge(); 		
					break;
				case	"edgeWithCapabilities"		: this.initEdgeWithDesiredCapabilities(this.capabilities); 	
					break;
				case	"ie"						: this.initIE();
					break;
				case	"ieWithCapabilities"		: this.initIEWithDesiredCapabilities(this.capabilities); 	
					break;
				
				//DEFAULT
				default	: 	this.initChrome();	
							break;
			}
			return true;
		}
		
		// Chrome browser
		//----------------------------------------------------------------
		private void initChrome() {
			this.driver = new ChromeDriver();
		}
		private void initChromeIncognito() {
			this.driver = new ChromeDriver();
		}
		private void initChromeWithOptions(ChromeOptions options) {
			this.driver = new ChromeDriver(options);
		}
		private void initChromeWithDesiredCapabilities( DesiredCapabilities capabilities) {
			this.driver = new ChromeDriver(capabilities);
		}
		
		// Firefox browser
		//----------------------------------------------------------------
		private void initFirefox() {
			this.driver = new FirefoxDriver();
		}
		private void initFirefoxIncognito() {
			this.driver = new FirefoxDriver();
		}
		private void initFirefoxWithDesiredCapabilities( DesiredCapabilities capabilities) {
			this.driver = new FirefoxDriver( capabilities );
		}

		// Edge browser
		//----------------------------------------------------------------
		private void initEdge() {
			driver = new EdgeDriver();
		}
		private void initEdgeWithDesiredCapabilities( DesiredCapabilities capabilities) {
			driver = new EdgeDriver(capabilities);
		}
		
		// IE browser
		//----------------------------------------------------------------
		private void initIE() {
			
		}
		private void initIEWithDesiredCapabilities( DesiredCapabilities capabilities) {
			
		}
		
} /* End of Class */
