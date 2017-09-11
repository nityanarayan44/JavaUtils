/**
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc ScreenCapture for webdriver under "org.nng.automation.utils" package
 */
package org.nng.automation.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class ScreenShot {

	/********************************
	 * @param driver
	 * @param folderPath
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean captureScreen(WebDriver driver, String saveToPath) throws Exception {
		
		// File object.
		File file 	= null, 
			 source = null;
		
		// Capturing screenshot.
		source 		= ( (TakesScreenshot)driver ).getScreenshotAs( OutputType.FILE );
		
		// Creating a new file
		file 		= new File( saveToPath + source.getName() );
		
		// Returning 
		return true;
	}
	
}