/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author Anil Pandey
 *
 */
public class WebPage {

	public static boolean searchTextOnWebPage(WebDriver driver, ExtentTest logger, String textToSearch) {

		boolean textFound = false;

		WebElement pageBody = driver.findElement(By.tagName("body"));
		if (pageBody.getText().contains(textToSearch)) {
			textFound = true;
		}
		return textFound;
	}

	public static boolean waitForPageToLoad(WebDriver driver) {
		boolean pageLoadStatus = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);

		pageLoadStatus = wait.until(ExpectedConditions
				.jsReturnsValue("return document.readyState=='complete';")) != null;

		return pageLoadStatus;
	}
}
