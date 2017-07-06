/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class WebButton.
 *
 * @author Anil Pandey
 */
public class WebButton {

	
	/**
	 * Web button click.
	 *
	 * @param driver the driver
	 * @param logger the logger
	 * @param element the element
	 */
	public static void webButtonClick(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			if (element != null && element.isEnabled()) {
				element.click();
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error clicking the button</br>"+e.getStackTrace());
		}
	}
}
