/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

// TODO: Auto-generated Javadoc
/**
 * The Class WebDropdown.
 *
 * @author Anil Pandey
 */
public class WebDropdown {

	/**
	 * Select option by visible text.
	 *
	 * @param element the element
	 * @param optionToSelect the option to select
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
	 * Select option by index.
	 *
	 * @param element the element
	 * @param indexNo the index no
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
	 * Select option by value.
	 *
	 * @param element the element
	 * @param value the value
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
