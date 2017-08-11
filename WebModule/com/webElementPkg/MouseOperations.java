/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author cpandeak
 *
 */
public class MouseOperations {

	public static void hoverMouseOnWebElement(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error hovering over the element</br>" + e.getCause());
		}
	}

}
