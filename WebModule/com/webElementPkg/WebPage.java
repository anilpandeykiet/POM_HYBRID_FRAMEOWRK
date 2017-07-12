/**
 * 
 */
package com.webElementPkg;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

// TODO: Auto-generated Javadoc

/**
 * The Class WebPage.
 *
 * @author Anil Pandey
 */
public class WebPage {

	public static void scrollingToBottomOfAPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

	}

	/**
	 * Search text on web page.
	 *
	 * @param driver
	 *            the driver
	 * 
	 * @param logger
	 *            the logger
	 * @param textToSearch
	 *            the text to search
	 * @return true, if successful
	 */
	public static boolean searchTextOnWebPage(WebDriver driver, ExtentTest logger, String textToSearch) {

		boolean textFound = false;

		WebElement pageBody = driver.findElement(By.tagName("body"));
		if (pageBody.getText().contains(textToSearch)) {
			textFound = true;
		}
		return textFound;
	}

	/**
	 * Wait for page to load.
	 *
	 * @param driver
	 *            the driver
	 * @return true, if successful
	 */
	public static boolean waitForPageToLoad(WebDriver driver) {
		boolean pageLoadStatus = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);

		pageLoadStatus = wait
				.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';")) != null;

		return pageLoadStatus;
	}

	/**
	 * @param textToSearch
	 *            the text to search
	 * @return the boolean
	 */
	public static Boolean searchTextOnWebPage(WebDriver driver, String textToSearch) {

		String bodyText = driver.findElement(By.tagName("body")).getText();

		Boolean textStatus = bodyText.contains(textToSearch);

		if (textStatus) {
			return true;
		} else {
			return false;
		}
	}

	public static void waitForPageLoad(WebDriver driver) {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			
			/* (non-Javadoc)
			 * @see java.util.function.Function#apply(java.lang.Object)
			 */
			public Boolean apply(WebDriver driver_) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public static boolean waitForJSandJQueryToLoad(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.google.common.base.Function#apply(java.lang.Object)
			 */
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);

	}
}
