/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Class WebLink.
 *
 * @author Anil Pandey
 */
public class WebLink {

	/**
	 * Click on web link.
	 *
	 * @param WebDriver the driver
	 * @param WebElement the element
	 */
	public static void clickOnWebLink(WebDriver driver, WebElement element) {
		try {
			if (element != null) {
				element.click();
			}else {
				System.out.println("WebLink is empty. Cannot click");
			}
		} catch (Exception e) {
			System.out.println("Error occured while clicking on weblink..\n");
			e.printStackTrace();
		}
	}
}
