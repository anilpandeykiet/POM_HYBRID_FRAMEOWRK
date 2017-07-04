/**
 * 
 */
package com.webElementPkg;

import java.util.Set;

import org.openqa.selenium.WebDriver;

/**
 * @author Anil Pandey
 *
 */
public class WebBrowser {

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
