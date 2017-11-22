/**
 * @package org.nng.automation.utils
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc Webdriver basic functionality under "org.nng.automation.utils" package
 * 
 * -------------------------------------
 * Driver Category
 * -------------------------------------
 * 			- Local WebDriver [Chrome | Firefox | IE | Edge]
 * 	TODO	- Remote WebDriver[Android | IPHONE | CHROME | FIREFOX | IE | EDGE | SAFARI]
 */

package org.nng.automation.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

@SuppressWarnings("unused")
public class Driver {
	
	//=============================================================
	// Variables
	//=============================================================
		public WebDriver driver 					= null;
		private DesiredCapabilities capabilities 	= null;
		private ChromeOptions options				= null;
		private String browserName					= null;
		private String errorMsgForWrongBrwserPassed = "\n./`ERROR`\\\\______________________________________________________________.\n| WebDriver is availble for one of these: CHROME | FIREFOX | IE | EDGE	|\n| Property is not set, hence driver(NULL) will not be initialized.	|\n+-----------------------------------------------------------------------+\n\n";
	
	/* 
	 * ChromeOptions and DesiredCapabilities.
	 * ------------------------------------------------------------
	 */
		public String[] defaultChromeOptions 				= {"--disable-extensions", "--start-maximized"};
		public String[] defaultchromeOptionsWithIncognito 	= {"--incognito","--disable-extensions", "--start-maximized"};
		public String[] IEDefaultCapabilities				= {"INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", "IGNORE_ZOOM_SETTING"};
		public String[] FirefoxDefaultCapabilities			= {""};
		
		// For remote server address
		public String APPIUM_URL 		= "http://127.0.0.1:4723/wd/hub";		//Specific complete address, where appium server is running and listening 
		public String PLATFORM_NAME 	= "Android";							//Specification of Mobile Platform either of "Android" || "iOS"
		public String PLATFORM_VERSION 	= "7.1.1";								//Specification of Mobile platform version
		public String DEVICE_NAME 		= "Android";							//Device Name.
		public String APP_PACKAGE 		= "com.android.calculator2";			//Name of the application package.
		public String APP_ACTIVITY 		= "com.android.calculator2.Calculator";	//Name of the application activity from where to start.
		public String APP 				= "";									//In case if we want to load an android application from a given location.
		public String UDID				= "";
		
	/*
	 * -----------------------
	 * Distructor
	 * -----------------------
	 */
		//		@Override
		//		protected void finalize() throws Throwable {
		//			//super.finalize();
		//			//System.out.println(">>> Closing and Quiting current driver object.");
		//			//this.closeDriver();
		//			this.quitDriver();
		//		}
		
	//=============================================================
	// Functions [Main]
	//=============================================================
		
		// Check for null driver.
		private boolean checkDriver(WebDriver d) {
			if (d.equals(null)) 
				return false;
			else 
				return true;
		}
		
		// Get a Web driver, [with no args]
		public WebDriver getWebDriver(String browserName, String driverPath) throws Exception {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName); 
				this.initDriver(browserName);
			}
			else this.driver = null;
			
			// Return webdriver
			if (this.checkDriver(this.driver)) { return this.driver; } else { throw new NullPointerException("Problem in driver creation"); }
		}
		
		// Get a Web driver, [with chromeOptions]		
		public WebDriver getWebDriver(String browserName, String driverPath, ChromeOptions chromeOptions) throws Exception {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setChromeOptions(chromeOptions);
				this.initDriver(browserName+"_With_Options");
			}
			else this.driver = null;
			// Return webdriver
			if (this.checkDriver(this.driver)) { return this.driver; } else { throw new NullPointerException("Problem in driver creation"); }
		}

		// Get a Web driver, [with desired capabilities]
		public WebDriver getWebDriver(String browserName, String driverPath, DesiredCapabilities capabilities) throws Exception {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setDesiredCapabilites(capabilities);
				this.initDriver(browserName+"_With_Capabilities");
			}
			else this.driver = null;
			// Return webdriver
			if (this.checkDriver(this.driver)) { return this.driver; } else { throw new NullPointerException("Problem in driver creation"); }
		}
		
		/*
		 * get the default chromeOption and desiredCapabilities
		 * ------------------------------------------------------
		 */
			public ChromeOptions getdefaultChromeOption() {
				this.options = new ChromeOptions();
				for(String flag : this.defaultChromeOptions)
					this.options.addArguments(flag);
				return this.options;
			}
			public ChromeOptions getdefaultChromeOptionWithIncognito() {
				this.options = new ChromeOptions();
				for(String flag : this.defaultchromeOptionsWithIncognito)
					this.options.addArguments(flag);
				return this.options;
			}
			public DesiredCapabilities getDefaultCapabilitiesForFirefox() {
				this.capabilities = DesiredCapabilities.firefox();
				//this.capabilities.setCapability();
				return this.capabilities;
			}
			public DesiredCapabilities getDefaultCapabilitiesForIE() {
				this.capabilities = DesiredCapabilities.internetExplorer();
				this.capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				this.capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
				return this.capabilities;
			}
			public DesiredCapabilities getDefaultCapabilitiesForEdge() {
				this.capabilities = DesiredCapabilities.edge();
				return this.capabilities;
			}
		
		/*
		 * Driver Native functions
		 * ------------------
		 */
			
			// maximize the driver window.
			public void maximizeBrowser() throws Exception {
				this.driver.manage().window().maximize();
				return;
			}
			//Get the Browser Name for this driver object
			public String getBrowserName() {
				return this.browserName;
			}
			
			// Close the current driver
			public void closeDriver() {
				// Fix: #1 [IE is not supporting the driver.close functionality.]
				if(this.browserName.toLowerCase().equals("ie")) {
					try {
						 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
					} catch (IOException e) {
						System.out.println("+--------------------------------------------+");
						System.out.println("| Problem in closing the IE driver object... |");
						System.out.println("+--------------------------------------------+");
						e.printStackTrace();
					}
				} else {
					this.driver.close();
				}
			}
			
			//Quit the current driver
			public void quitDriver() {
				// Fix: #1 [IE is not supporting the driver.quit functionality.]
				if(this.browserName.toLowerCase().equals("ie")) {
					try {
						 Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
					} catch (IOException e) {
						System.out.println("+--------------------------------------------+");
						System.out.println("| Problem in Quiting the IE driver object... |");
						System.out.println("+--------------------------------------------+");
						e.printStackTrace();
					}
				} else {
					this.driver.quit();
				}
			}
		
	//=============================================================
	// Private: functions
	//=============================================================
		
		//Set the system property for webdriver
		private boolean setProperty(String browserName, String driverPath) {
			
			switch(browserName.toLowerCase()) {
				case "chrome"	: 	System.setProperty("webdriver.chrome.driver", 	driverPath); break;
				case "firefox"	: 	System.setProperty("webdriver.gecko.driver", 	driverPath); break;
				case "ie"		: 	System.setProperty("webdriver.ie.driver", 		driverPath); break;
				case "edge"		: 	System.setProperty("webdriver.edge.driver", 	driverPath); break;
				//DEFAULT
				default 		: 	System.out.println(this.errorMsgForWrongBrwserPassed);
									return false;
			}
			//returning the status
			return true;
		}
		
		/*
		 * =========================================
		 * Local WebDriver initiations
		 * -----------------------------------------
		 */
		
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
					private boolean initDriver(String initWith) {
						System.out.println("INIT DRIVER PARAMETER: " + initWith);
						switch(initWith.toUpperCase()) {
							case 	"CHROME"					: this.initChrome();
								break;
							case	"CHROME_WITH_OPTIONS"		: this.initChromeWithOptions(this.options);
								break;
							case	"CHROME_WITH_CAPABILITIES"	: this.initChromeWithDesiredCapabilities(this.capabilities);
								break;
							case	"FIREFOX"					: this.initFirefox(); 	
								break;
							case	"FIREFOX_WITH_CAPABILITIES"	: this.initFirefoxWithDesiredCapabilities(this.capabilities); 	
								break;
							case	"EDGE"						: this.initEdge(); 		
								break;
							case	"EDGE_WITH_CAPABILITIES"	: this.initEdgeWithDesiredCapabilities(this.capabilities); 	
								break;
							case	"IE"						: this.initIE();
								break;
							case	"IE_WITH_CAPABILITIES"		: this.initIEWithDesiredCapabilities(this.capabilities); 	
								break;
							case	"ANDROID_WITH_CAPABILITY"	:
								break;
							case	"IOS_WITH_CAPABILITY"		:
								break;
							//DEFAULT
							default	: 	this.initChrome();	
										System.out.println("As a default WebDriver: Chrome");
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
						DesiredCapabilities capabilities = DesiredCapabilities.edge();
						EdgeOptions options = new EdgeOptions();
						
						//capabilities.setCapability(, value);
						driver = new EdgeDriver(options);
					}
					private void initEdgeWithDesiredCapabilities( DesiredCapabilities capabilities) {
						driver = new EdgeDriver(capabilities);
					}
					
					// IE browser
					//----------------------------------------------------------------
					private void initIE() {
						//setting Default capability
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
						capabilities.setCapability("allow-blocked-content", true);
						capabilities.setCapability("allowBlockedContent", true);
						driver = new InternetExplorerDriver(capabilities);
					}
					private void initIEWithDesiredCapabilities( DesiredCapabilities capabilities) {
						driver = new InternetExplorerDriver(capabilities);
					}
		/*
		 * =========================================
		 * Remote WebDriver initiations
		 * -----------------------------------------
		 * [ CHROME | FIREFOX | IE | EDGE | SAFARI ]
		 * [ ANDROID | IPHONE | SELENIUM_RC ]
		 * -----------------------------------------
		 */
				
			
} /* End of Class */
