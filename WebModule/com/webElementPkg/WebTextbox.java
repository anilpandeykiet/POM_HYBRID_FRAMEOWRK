/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Anil Pandey
 *
 */
public class WebTextbox {

	public static void sendTextToWebInput(WebElement element, String textToEnter, ExtentTest logger) {
		try {
			if (element != null) {
				element.sendKeys(textToEnter);
				logger.log(LogStatus.INFO, "Text '" + textToEnter + "' successfully entered in the text box");
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to enter text '" + textToEnter + "' in the text box</br>" + e.getStackTrace());
		}
	}

	public static boolean isWebInputEditable(WebElement element) {
		boolean visibilityStatus = false;
		try {
			if (element != null) {
				visibilityStatus = element.isEnabled();
			}
		} catch (Exception e) {
			// nothing to catch
		}
		return visibilityStatus;
	}
}
