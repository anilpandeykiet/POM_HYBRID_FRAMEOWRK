/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class WebRadiobutton.
 *
 * @author Anil Pandey
 */
public class WebRadiobutton {
	// Get value of radio button from user

	public static void getRadioButtonOption(WebDriver driver, ExtentTest logger, WebElement element,
			String radioOption) {
		try {
			if (element != null) {
				element.sendKeys(radioOption);
				logger.log(LogStatus.PASS, "Option <label>'" + radioOption + "'</label> saved successfully in <label>'"
						+ element.getAttribute("RadioButtonOption") + "'</label>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to find <label>'" + element.getAttribute("RadioButtonOption") + "'</label>");
		}
	}

	public static void selectRadioButton(WebDriver driver, ExtentTest logger, WebElement element) {
		if (element != null) {
			if (!element.isSelected()) {
				element.click();
				logger.log(LogStatus.INFO, "Radio button selected successfully");
			}else {
				logger.log(LogStatus.INFO, "Radio button is already selected");
			}
		}else {
			logger.log(LogStatus.INFO, "Unable to find the radio button");
		}
	}

}
