/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * @author Anil Pandey
 *
 */
public class WebAlerts {

	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
}
