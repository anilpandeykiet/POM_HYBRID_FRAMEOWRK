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

// TODO: Auto-generated Javadoc
/**
 * The Class WebUtilities.
 *
 * @author Anil Pandey
 */
public class WebUtilities {

	/**
	 * Checks if is element present.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param logger the logger
	 * @return true, if is element present
	 */
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

	/**
	 * Wait for element to appear.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param logger the logger
	 */
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

	/**
	 * Wait for element to disappear.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param logger the logger
	 * @return true, if successful
	 */
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
	
	/**
	 * Gets the element attribute value.
	 *
	 * @param element the element
	 * @param logger the logger
	 * @param attributeName the attribute name
	 * @return the element attribute value
	 */
	public static String getElementAttributeValue(WebElement element, ExtentTest logger, String attributeName) {
		String attributeValue = null;
		
		attributeValue = element.getAttribute(attributeName);
		
		return attributeValue;
	}
	
	/**
	 * Wait for element staleness.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return true, if successful
	 */
	public static boolean waitForElementStaleness(WebDriver driver, WebElement element) {
		boolean staleStatus = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.until(ExpectedConditions.stalenessOf(element));
		
		return staleStatus;
	}
	
	
	/**
	 * Static wait.
	 *
	 * @param driver the driver
	 * @param timeToWait the time to wait
	 */
	public static void staticWait(WebDriver driver, int timeToWait) {
		
		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}





















