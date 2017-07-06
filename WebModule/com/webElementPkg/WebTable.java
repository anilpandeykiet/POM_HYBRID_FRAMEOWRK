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
 * The Class WebTable.
 *
 * @author Anil Pandey
 */
public class WebTable {

	/**
	 * Gets the all table rows.
	 *
	 * @param driver the driver
	 * @param webTableId the web table id
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
	 * Gets the all columns in table row.
	 *
	 * @param driver the driver
	 * @param tableRow the table row
	 * @return the all columns in table row
	 */
	public static List<WebElement> getAllColumnsInTableRow(WebDriver driver, WebElement tableRow) {
		List<WebElement> tableCols = new ArrayList<WebElement>();

		tableCols = tableRow.findElements(By.tagName("td"));

		return tableCols;
	}

}
