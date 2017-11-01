/**
 * @package org.nng.automation.utils
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc Action on WebElement under "org.nng.automation.utils" package
 * 
 * ----------------------
 * Division of this class
 * ----------------------
 *  Locate 				=> Finding or locating an element
 *  Action-Interaction	=> Perform an action on an element
 *  Exchange			=> setting or getting values from an element
 *  
 *  
 *  TODO: Documentation of this class and functions.
 */

package org.nng.automation.utils;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.util.Set;

import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("unused")
public class Action {

	//=============================================================
	// Global Variables
	//=============================================================
		int waitTimer 		= 10;
		
	//=============================================================
	// Locate an Element
	//=============================================================
		/*
		 * Native functions to locate an Element
		 * ---------------------------------------
		 */
			public WebElement findElement(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception{
				WebElement element 	= null;
				switch(findBy.toUpperCase()) {
					case "ID"		: 	element = webDriver.findElement(By.id(elementIdentifier)); 
										break;
					case "XPATH"	: 	element = webDriver.findElement(By.xpath(elementIdentifier));
										break;
					case "NAME"		: 	element = webDriver.findElement(By.name(elementIdentifier));
										break;
					case "CLASSNAME": 	element = webDriver.findElement(By.className(elementIdentifier));
										break;
					case "LINKTEXT"	: 	element = webDriver.findElement(By.linkText(elementIdentifier));
										break;
					//DEFAULT
					default			: 	System.out.println("This findBy: "+findBy+", is not available yet to locate an element."); 
										return element;
				}
				return element;
			}
			
			public WebElement findElementWithExplicitWait(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception{
				WebDriverWait wait = new WebDriverWait(webDriver, this.waitTimer);	
				WebElement element 	= null;
				switch(findBy.toUpperCase()) {
					case "ID"		: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementIdentifier))); 
										break;
					case "XPATH"	: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementIdentifier)));
										break;
					case "NAME"		: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.name(elementIdentifier)));
										break;
					case "CLASSNAME": 	element = wait.until(ExpectedConditions.elementToBeClickable(By.className(elementIdentifier)));
										break;
					case "LINKTEXT"	: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementIdentifier)));
										break;
					//DEFAULT
					default			: 	System.out.println("This findBy: "+findBy+", is not available yet to locate an element."); 
										return element;
				}
				return element;
			}
			
			public WebElement findElementWithExplicitWait(WebDriver webDriver, String findBy, String elementIdentifier, int waitTimer) throws Exception{
				WebDriverWait wait = new WebDriverWait(webDriver, waitTimer);	
				WebElement element 	= null;
				switch(findBy.toUpperCase()) {
					case "ID"		: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementIdentifier))); 
										break;
					case "XPATH"	: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementIdentifier)));
										break;
					case "NAME"		: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.name(elementIdentifier)));
										break;
					case "CLASSNAME": 	element = wait.until(ExpectedConditions.elementToBeClickable(By.className(elementIdentifier)));
										break;
					case "LINKTEXT"	: 	element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementIdentifier)));
										break;
					//DEFAULT
					default			: 	System.out.println("This findBy: "+findBy+", is not available yet to locate an element."); 
										return element;
				}
				return element;
			}
		
	//=============================================================
	// Interaction/Perform an Action on Element
	//=============================================================
		
		/*
		 * Select class on WebDriver
		 * ---------------------------
		 */
			public void selectFromDropDown(WebDriver webDriver, String findBy, String elementIdentifier, String selectBy, String selectionValue) throws Exception {	
				WebElement element = this.findElement(webDriver, findBy, elementIdentifier);
				Select dropdown = new Select(element);
				//Selection Criteria
				switch(selectBy.toUpperCase()) {
					case "TEXT"	:	dropdown.selectByVisibleText(selectionValue); 				break;
					case "VALUE":	dropdown.selectByValue(selectionValue); 					break;
					case "INDEX":	dropdown.selectByIndex(Integer.parseInt(selectionValue)); 	break;
					default: break;
				}
			}
		
		/*
		 * Actions Class on WebDriver
		 * ---------------------------
		 */
			public Actions doDragAndDrop(WebDriver webDriver, WebElement drag, WebElement drop) throws Exception{
				Actions actions = new Actions(webDriver);
				actions.dragAndDrop(drag, drop).build().perform();
				return actions;
			}
			
			public Actions doclick(WebDriver webDriver, WebElement target) throws Exception{
				Actions actions = new Actions(webDriver);
				actions.click(target);
				return actions;
			}
			
			public Actions doubleClick(WebDriver webDriver, WebElement target) throws Exception{
				Actions actions = new Actions(webDriver);
				actions.doubleClick(target);
				return actions;
			}
			
			public Actions clickAndHold(WebDriver webDriver, WebElement target) throws Exception{
				Actions actions = new Actions(webDriver);
				actions.clickAndHold(target);
				return actions;
			}
			
			public Actions doRightClick(WebDriver webDriver, WebElement target) throws Exception{
				Actions actions = new Actions(webDriver);
				actions.contextClick(target);
				return actions;
			}
			
			public Actions moveToElement(WebDriver webDriver, WebElement target) throws Exception {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(target);
				return actions;
			}
		
		/*
		 * Alert class on WebDriver
		 * -----------------------------
		 */
			public void acceptAlert(WebDriver webDriver) throws Exception{
				Alert alert = webDriver.switchTo().alert();
				alert.accept();
			}
			
			public void dismissAlert(WebDriver webDriver) throws Exception{
				Alert alert = webDriver.switchTo().alert();
				alert.dismiss();
			}
			
		/*
		 * Switching, Window, Tab and Window Handles
		 * -----------------------------------------
		 */
			public Set<String> getWindowHandles(WebDriver webdriver) throws Exception{
				return webdriver.getWindowHandles();
			}
			public String getCurrentWindow(WebDriver webdriver) throws Exception {
				return webdriver.getWindowHandle();
			}
			public WebDriver switchToWindow(WebDriver webdriver, String windowName) throws Exception{
				webdriver.switchTo().window(windowName);
				return webdriver.switchTo().window(windowName);
			}
			public void switchToNextTab(WebDriver webdriver) {
				
			}
			public void switchToPreviousTab(WebDriver webdrivers) {
				
			}
			
		/*
		 * Native Functions for WebDriver
		 * --------------------------------
		 */
			
			public boolean captureBrowserScreenshot(WebDriver webDriver, String saveToPath) throws Exception {
				File screenshot = null, source = null;
				source 		= ( (TakesScreenshot)webDriver ).getScreenshotAs( OutputType.FILE );
				screenshot 	= new File( saveToPath + source.getName() );
				FileUtils.copyFile(source, screenshot);
				return true;
			}
			
			public void doClick(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				element.click();
			}
			
			public void doSubmit(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				element.submit();
			}
			
			public Point getElementPositionPoint(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.getLocation();
			}
			
			public boolean isEnable(WebDriver webDriver, String findBy, String elementIdentifier)  throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.isEnabled();
			}
		
			public boolean isSelected(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.isSelected();
			}
			
			public boolean isVisible(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.isDisplayed();
			}
		
		//=============================================================
		// Exchange[set-get] values to/from an element
		//=============================================================
		
		/*
		 * Native functions for/On WebDriver
		 * -----------------------------------
		 */
			public void setElementValue(WebDriver webDriver, String findBy, String elementIdentifier, String value) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				element.sendKeys(value);
			}
			
			public void setElementValue(WebDriver webDriver, String findBy, String elementIdentifier, Keys keyValue) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				element.sendKeys(keyValue);
			}
			
			public String getElementValue(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.getText().toString();
			}
			
			public String getElementValue(WebDriver webDriver, String findBy, String elementIdentifier, String valueType) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				switch(valueType.toUpperCase()) {
					case "TEXT"		: return element.getText().toString();
					case "CSSVALUE"	: return element.getCssValue(valueType).toString();
					case "TAGNAME"	: return element.getTagName().toString();
				}
				return element.getText().toString();
			}
			
			public String getElementValueByProperty(WebDriver webDriver, String findBy, String elementIdentifier, String property) throws Exception {
				WebElement element=this.findElement(webDriver, findBy, elementIdentifier);
				return element.getAttribute(property).toString();
			}
			
			public void clearElementValue(WebDriver webDriver, String findBy, String elementIdentifier) throws Exception {
				WebElement element = this.findElement(webDriver, findBy, elementIdentifier);
				element.clear();
			}
		
			public int getElementsSize(WebDriver webDriver, String elementIdentifier) throws Exception {
				return (webDriver.findElements(By.xpath(elementIdentifier)).size());
			}
			
} /* End of class */
