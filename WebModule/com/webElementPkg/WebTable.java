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
public class WebTable {

	/**
	 * @param WebDriver
	 *            driver
	 * @param WebElement
	 *            webTableId
	 * @return {@code List<WebElement>}</br>
	 * 
	 *         <br>
	 * 		It is assumed that the first row in the table would the name of table
	 *         columns.</br>
	 *         Same is removed from the List before returning it to the calling
	 *         method.</br>
	 *         If subsequent rows after table column names are null or blank</br>
	 *         user would have to handle the same in the calling method
	 */
	public static List<WebElement> getAllTableRows(WebDriver driver, WebElement webTableId) {
		List<WebElement> tableRows = new ArrayList<WebElement>();

		WebElement tBody = driver.findElement(By.tagName("tbody"));
		tableRows = tBody.findElements(By.tagName("tr"));

		tableRows.remove(0);
		return tableRows;
	}

	/**
	 * @param driver
	 * @param tableRow
	 * @return
	 */
	public static List<WebElement> getAllColumnsInTableRow(WebDriver driver, WebElement tableRow) {
		List<WebElement> tableCols = new ArrayList<WebElement>();

		tableCols = tableRow.findElements(By.tagName("td"));

		return tableCols;
	}

}
