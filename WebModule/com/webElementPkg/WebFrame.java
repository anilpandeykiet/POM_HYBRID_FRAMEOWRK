/**
 * 
 */
package com.webElementPkg;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Anil Pandey
 *
 */
public class WebFrame {

	public static List<WebElement> getAllFramesOnPage(WebDriver driver) {

		List<WebElement> iFarmeList = new ArrayList<WebElement>();

		iFarmeList = driver.findElements(By.tagName("iframe"));

		return iFarmeList;
	}

	public static void switchOnFarmeByName(WebDriver driver, String frameName) {
		List<WebElement> iFarmeList = getAllFramesOnPage(driver);

		if (iFarmeList != null) {
			driver.switchTo().frame(frameName);
		}
	}

	public static void switchOnFarmeById(WebDriver driver, String frameId) {
		List<WebElement> iFarmeList = getAllFramesOnPage(driver);

		if (iFarmeList != null) {
			driver.switchTo().frame(frameId);
		}
	}

	public static void switchToMainPageFromIFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static int getIFrameCount(WebDriver driver) {
		return getAllFramesOnPage(driver).size();
	}
}
