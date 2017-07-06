/**
 * 
 */
package com.webElementPkg;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Class WebFrame.
 *
 * @author Anil Pandey
 */
public class WebFrame {

	/**
	 * Gets the all frames on page.
	 *
	 * @param driver the driver
	 * @return the all frames on page
	 */
	public static List<WebElement> getAllFramesOnPage(WebDriver driver) {

		List<WebElement> iFarmeList = new ArrayList<WebElement>();

		iFarmeList = driver.findElements(By.tagName("iframe"));

		return iFarmeList;
	}

	/**
	 * Switch on frame by name.
	 *
	 * @param driver the driver
	 * @param frameName the frame name
	 */
	public static void switchOnFrameByName(WebDriver driver, String frameName) {
		List<WebElement> iFarmeList = getAllFramesOnPage(driver);

		if (iFarmeList != null) {
			driver.switchTo().frame(frameName);
		}
	}

	/**
	 * Switch on frame by id.
	 *
	 * @param driver the driver
	 * @param frameId the frame id
	 */
	public static void switchOnFrameById(WebDriver driver, String frameId) {
		List<WebElement> iFarmeList = getAllFramesOnPage(driver);

		if (iFarmeList != null) {
			driver.switchTo().frame(frameId);
		}
	}

	/**
	 * Switch to main page from iFrame.
	 *
	 * @param driver the driver
	 */
	public static void switchToMainPageFromIFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Gets the iFrame count.
	 *
	 * @param driver the driver
	 * @return the i frame count
	 */
	public static int getIFrameCount(WebDriver driver) {
		return getAllFramesOnPage(driver).size();
	}
}
