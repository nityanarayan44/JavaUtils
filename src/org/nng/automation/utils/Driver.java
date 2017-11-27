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
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;

import io.appium.java_client.AppiumDriver;
//For Appium's AndroidDriver
import io.appium.java_client.android.AndroidDriver;

@SuppressWarnings({"unused", "rawtypes"})
public class Driver {
	
	//=============================================================
	// Variables
	//=============================================================
		public AndroidDriver androidDriver			= null;
		public AppiumDriver appiumDriver			= null;
		public WebDriver webDriver 					= null;
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
		
		// Default desiredCapability for Android
		private Map<String, String> androidOptions = new HashMap<String, String>();
		
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
	// Functions to initiate the driver [Main]
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
			else this.webDriver = null;
			
			// Return webdriver
			if (this.checkDriver(this.webDriver)) { return this.webDriver; } else { throw new NullPointerException("Problem in driver creation"); }
		}
		
		// Get a Web driver, [with chromeOptions]		
		public WebDriver getWebDriver(String browserName, String driverPath, ChromeOptions chromeOptions) throws Exception {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setChromeOptions(chromeOptions);
				this.initDriver(browserName+"_With_Options");
			}
			else this.webDriver = null;
			// Return webdriver
			if (this.checkDriver(this.webDriver)) { return this.webDriver; } else { throw new NullPointerException("Problem in driver creation"); }
		}

		// Get a Web driver, [with desired capabilities]
		public WebDriver getWebDriver(String browserName, String driverPath, DesiredCapabilities capabilities) throws Exception {
			if(this.setProperty(browserName, driverPath)) { 
				this.setDriver(browserName);
				this.setDesiredCapabilites(capabilities);
				this.initDriver(browserName+"_With_Capabilities");
			}
			else this.webDriver = null;
			// Return webdriver
			if (this.checkDriver(this.webDriver)) { return this.webDriver; } else { throw new NullPointerException("Problem in driver creation"); }
		}
		
		// get the Android Driver
		public AndroidDriver getAndroidDriver(Map<String, String> optionForAndroid) throws Exception {
			if(!optionForAndroid.equals(null)) {
				this.initAndroidDriver(optionForAndroid.get("url"), this.getCapabilitiesForAndroidDriver(optionForAndroid));
			} else {
				throw new InvalidParameterException("Passed parameter can not be null.");
			}
			return this.androidDriver;
		}
		
		// Get the Remote webdriver {e.g. Android Chrome driver}
		public WebDriver getRemoteDriver (Map<String, String> optionForAndroid) throws Exception {
			if(!optionForAndroid.equals(null)) {
				this.initRemoteDriver(optionForAndroid.get("url"), this.getCapabilitiesForAndroidDriver(optionForAndroid));
			} else {
				throw new InvalidParameterException("Passed parameter can not be null.");
			}
			return this.webDriver;
		}
		
		// Get AppiumDriver
		public WebDriver getAppiumDriver (Map<String, String> optionForAndroid) throws Exception {
			if(!optionForAndroid.equals(null)) {
				this.initAppiumDriver(optionForAndroid.get("url"), this.getCapabilitiesForAndroidDriver(optionForAndroid));
			} else {
				throw new InvalidParameterException("Passed parameter can not be null.");
			}
			return this.webDriver;
		}
		
	/*
	 * ------------------------------------------------------
	 * get the default desiredCapabilities OR chromeOption
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
			
			//-----------------------------------------------------------
			// Android Driver default capabilities [as per this framework: Targeting 7.1.1 Android Nougat]
			//-----------------------------------------------------------
			public DesiredCapabilities getDefaultCapabilitesForAndroidDriver() throws Exception {
				// Initialize the capability
					this.capabilities = new DesiredCapabilities();
				// Assigning the passed options as capabilities.
					this.capabilities.setCapability("platformName", 	this.PLATFORM_NAME);
					this.capabilities.setCapability("deviceName", 		this.DEVICE_NAME);
					this.capabilities.setCapability("platformVersion", 	this.PLATFORM_VERSION);
					this.capabilities.setCapability("appPackage", 		this.APP_PACKAGE);
					this.capabilities.setCapability("appActivity", 		this.APP_ACTIVITY);
				// Returning
					return this.capabilities;
			}
			
			public DesiredCapabilities getCapabilitiesForAndroidDriver(Map<String, String> opt) throws Exception {
				// Initialize the capability
				this.capabilities = new DesiredCapabilities();
				// Assigning the passed options as capabilities.
				for(String key : opt.keySet()) {
					if(!key.equalsIgnoreCase("url")) {
						this.capabilities.setCapability(key, opt.get(key));
						System.out.println("Key= " + key + ", Value= " + opt.get(key));
					}
				}
				return this.capabilities;
			}
		
		/*
		 * ------------------------------------------------------
		 * Driver Native functions
		 * ------------------------------------------------------
		 */
			
			// Open an given address.
			public void open(String address) throws Exception {
				this.webDriver.get(address);
				return;
			}
			
			// maximize the driver window.
			public void maximizeBrowser() throws Exception {
				this.webDriver.manage().window().maximize();
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
						System.out.println("Executing the commandline to kill IEDriver");
						Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
						System.out.println( "Avaiable: " + Runtime.getRuntime().freeMemory() / 1024 + ", Total: " + (Runtime.getRuntime().totalMemory()) / (1024));
					} catch (IOException e) {
						System.out.println("+--------------------------------------------+");
						System.out.println("| Problem in closing the IE driver object... |");
						System.out.println("+--------------------------------------------+");
						e.printStackTrace();
					}
				} else {
					this.webDriver.close();
				}
			}
			
			//Quit the current driver
			public void quitDriver() {
				// Fix: #1 [IE is not supporting the driver.quit functionality.]
				if(this.browserName.toLowerCase().equals("ie")) {
					try {
						System.out.println("Executing the commandline to kill IEDriver");
						Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
						System.out.println( "Free: " + Runtime.getRuntime().freeMemory() / 1024 + ", Total: " + (Runtime.getRuntime().totalMemory()) / (1024));
					} catch (IOException e) {
						System.out.println("+--------------------------------------------+");
						System.out.println("| Problem in Quiting the IE driver object... |");
						System.out.println("+--------------------------------------------+");
						e.printStackTrace();
					}
				} else if (this.androidDriver != null) {
						this.androidDriver.quit();
				} else {
						this.webDriver.quit();
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
						this.webDriver = new ChromeDriver();
					}
					private void initChromeIncognito() {
						this.webDriver = new ChromeDriver();
					}
					private void initChromeWithOptions(ChromeOptions options) {
						this.webDriver = new ChromeDriver(options);
					}
					private void initChromeWithDesiredCapabilities( DesiredCapabilities capabilities) {
						this.webDriver = new ChromeDriver(capabilities);
					}
					
					// Firefox browser
					//----------------------------------------------------------------
					private void initFirefox() {
						this.webDriver = new FirefoxDriver();
					}
					private void initFirefoxIncognito() {
						this.webDriver = new FirefoxDriver();
					}
					private void initFirefoxWithDesiredCapabilities( DesiredCapabilities capabilities) {
						this.webDriver = new FirefoxDriver( capabilities );
					}
			
					// Edge browser
					//----------------------------------------------------------------
					private void initEdge() {
						DesiredCapabilities capabilities = DesiredCapabilities.edge();
						EdgeOptions options = new EdgeOptions();
						
						//capabilities.setCapability(, value);
						this.webDriver = new EdgeDriver(options);
					}
					private void initEdgeWithDesiredCapabilities( DesiredCapabilities capabilities) {
						this.webDriver = new EdgeDriver(capabilities);
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
						this.webDriver = new InternetExplorerDriver(capabilities);
					}
					private void initIEWithDesiredCapabilities( DesiredCapabilities capabilities) {
						this.webDriver = new InternetExplorerDriver(capabilities);
					}
		/*
		 * =========================================
		 * Remote WebDriver initiations
		 * -----------------------------------------
		 * [ CHROME | FIREFOX | IE | EDGE | SAFARI ]
		 * [ ANDROID | IPHONE | SELENIUM_RC ]
		 * -----------------------------------------
		 */
					//----------------------------------------------------------------
					// Appium Android Driver
					//----------------------------------------------------------------
					private void initAndroidDriver(String url, DesiredCapabilities capability) throws Exception {
						this.androidDriver = new AndroidDriver(new URL(url), capability);
						return;
					}
			
					//----------------------------------------------------------------
					// Remote WebDriver [Android Chrome, or other network web driver]
					//----------------------------------------------------------------
					private void initRemoteDriver(String url, DesiredCapabilities capability) throws Exception {
						this.webDriver = new RemoteWebDriver(new URL(url), capability);
						return;
					}
					
					//----------------------------------------------------------------
					// Appium WebDriver [Android Chrome, or other network web driver]
					//----------------------------------------------------------------
					private void initAppiumDriver(String url, DesiredCapabilities capability) throws Exception {
						this.appiumDriver = new AppiumDriver(new URL(url), capability);
						return;
					}
} /* End of Class */
