/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Anil Pandey
 *
 */
public class WebDropdown {

	/**
	 * @param element
	 * @param optionToSelect
	 */
	public static void selectOptionByVisibleText(WebElement element, String optionToSelect) {
		if (element != null) {
			Select option = new Select(element);
			if (option != null) {
				option.selectByVisibleText(optionToSelect);
			}
		}
	}
	
	/**
	 * @param element
	 * @param indexNo
	 */
	public static void selectOptionByIndex(WebElement element, int indexNo) {
		if (element != null) {
			Select option = new Select(element);
			if (option != null) {
				option.selectByIndex(indexNo);
			}
		}
	}
	
	/**
	 * @param element
	 * @param value
	 */
	public static void selectOptionByValue(WebElement element, String value) {
		if (element != null) {
			Select option = new Select(element);
			if (option != null) {
				option.selectByValue(value);
			}
		}
	}
}
