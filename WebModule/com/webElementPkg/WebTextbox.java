/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class WebTextbox.
 *
 * @author Anil Pandey
 */
public class WebTextbox {

	/**
	 * Send text to web input.
	 *
	 * @param element
	 *            the element
	 * @param textToEnter
	 *            the text to enter
	 * @param logger
	 *            the logger
	 */
	public static void sendTextToWebInput(WebElement element, String textToEnter, ExtentTest logger) {
		try {
			if (element != null) {
				element.sendKeys(textToEnter);
				logger.log(LogStatus.INFO, "Text '" + textToEnter + "' successfully entered in the text box");
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to enter text '" + textToEnter + "' in the text box</br>" + e.getStackTrace().toString());
			e.printStackTrace();;
		}
	}
	
	public static void clearTextBox(WebElement element) {
		if (element != null) {
			element.clear();
		}
	}

	public static void sendKeysNTimes(WebElement element, int numberOfTimes, Keys keyName) {
		try {
			for (int count = 1; count <= numberOfTimes; count++) {
				element.sendKeys(keyName);
			}
		} catch (Exception e) {
			System.out.println("Error occured while sending keys...\n" + e.getMessage());
		}
	}

	/**
	 * Checks if is web input editable.
	 *
	 * @param element
	 *            the element
	 * @return true, if is web input editable
	 */
	public static boolean isWebInputEditable(WebElement element) {
		boolean visibilityStatus = false;
		try {
			if (element != null) {
				visibilityStatus = element.isEnabled();
			}
		} catch (Exception e) {
			// nothing can be done at this point
		}
		return visibilityStatus;
	}

	/**
	 * Clear web input.
	 *
	 * @param element
	 *            the element
	 */
	public static void clearWebInput(WebElement element) {
		element.clear();
	}
}
