/**
 * 
 */
package com.webElementPkg;

import java.util.Set;

import org.openqa.selenium.WebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class WebBrowser.
 *
 * @author Anil Pandey
 */
public class WebBrowser {

	/**
	 * Switch to child window.
	 *
	 * @param driver the driver
	 * @param childPagetitle the child pagetitle
	 */
	public static void switchToChildWindow(WebDriver driver, String childPagetitle) {
		Set<String> allWin = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();

		for (String childWindow : allWin) {
			if (!childWindow.equalsIgnoreCase(parentWindow)) {
				if (driver.switchTo().window(childWindow).getTitle().equalsIgnoreCase(childPagetitle)) {
					break;
				}
			}
		}
	}

	/**
	 * Switch to child window.
	 *
	 * @param driver the driver
	 */
	public static void switchToChildWindow(WebDriver driver) {
		Set<String> allWin = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();
		for (String childWindow : allWin) {
			if (!childWindow.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(childWindow);
				break;
			}
		}
	}
	
}
