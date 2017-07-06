/**
 * 
 */
package com.webElementPkg;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class WebCookies.
 *
 * @author Anil Pandey
 */
public class WebCookies {

	/**
	 * Gets the all cookies for the current domain associated with </br>
	 * instance of WebDriver
	 *
	 * @param driver
	 *            the driver
	 * @return all cookies associated with current domain
	 * 
	 * 
	 */
	public static Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
    /**
     * Get a cookie with a given name.
     *
     * @param name the name of the cookie
     * @return the cookie, or null if no cookie with the given name is present
     */
	public static Cookie getCookieByName(WebDriver driver, String cookieName) {
		return driver.manage().getCookieNamed(cookieName);
	}
	
    /**
     * Delete all the cookies for the current domain.
     *
     * @param driver the driver
     */
	public static void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	
}
