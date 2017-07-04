/**
 * 
 */
package com.webElementPkg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Anil Pandey
 *
 */
public class WebUtilities {

	public static boolean isElementPresent(WebDriver driver, WebElement element, ExtentTest logger) {
		boolean elementPresent = false;

		try {
			if (element.isDisplayed()) {
				elementPresent = true;
				logger.log(LogStatus.INFO, "WebElement is visible");
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "WebElement is not present...<br></br>" + e.getStackTrace());
			e.printStackTrace();
		}
		return elementPresent;
	}

	public static void waitForElementToAppear(WebDriver driver, WebElement element, ExtentTest logger) {

		try {
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).pollingEvery(2, TimeUnit.SECONDS)
					.withTimeout(60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			fluentWait.until(ExpectedConditions.visibilityOf(element));

		} catch (TimeoutException toe) {
			logger.log(LogStatus.ERROR, "Timeout waiting for webelement to be present<br></br>" + toe.getStackTrace());
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Exception occured<br></br>" + e.getStackTrace());
		}
	}

	public static boolean waitForElementToDisappear(WebDriver driver, WebElement element, ExtentTest logger) {
		try {
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			fluentWait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Error occured while waiting for element to disappear</br>" + e.getStackTrace());
			return false;
		}
	}
	
	public static String getElementAttributeValue(WebElement element, ExtentTest logger, String attributeName) {
		String attributeValue = null;
		
		attributeValue = element.getAttribute(attributeName);
		
		return attributeValue;
	}
	
	public static boolean waitForElementStaleness(WebDriver driver, WebElement element) {
		boolean staleStatus = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.until(ExpectedConditions.stalenessOf(element));
		
		return staleStatus;
	}
	
	
	public static void staticWait(WebDriver driver, int timeToWait) {
		
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}





















