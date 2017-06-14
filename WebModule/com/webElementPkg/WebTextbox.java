/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author anilpandey
 *
 */
public class WebTextbox {

	public static void sendTextToWebInput(WebDriver driver, ExtentTest logger, WebElement element, String testToWrite){
		
		try {
			if (element != null) {
				element.sendKeys(testToWrite);
				logger.log(LogStatus.PASS, "Text <label>'"+testToWrite +"'</label> written successfully in <label>'"+element.getAttribute("name")+"'</label>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find <label>'"+element.getAttribute("name")+"'</label>");
		}
	}
	
	public static String getWebInputText(WebDriver driver, ExtentTest logger, WebElement element){
		String elementText = null;
		try {
			if (element != null) {
				elementText = element.getText().trim();
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the <label>'"+element.getAttribute("name")+ "'</label>");
		}
		return elementText;
	}
}
