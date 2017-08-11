/**
 * 
 */
package googlePageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

import corePkg.BaseClass;
import fileReadingPkg.ReadPropertyFile;
import reportingPkg.ReportManager;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchAndVerifyPageObjects.
 *
 * @author cpandeak
 */
public class SearchAndVerifyPageObjects {

	/** The driver. */
	private WebDriver driver = null;

	/** The logger. */
	private ExtentTest logger = null;

	/**
	 * Instantiates a new search and verify page objects.
	 *
	 * @param driver
	 *            the driver
	 * @param logger
	 *            the logger
	 */
	public SearchAndVerifyPageObjects(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	/** The Search input txt box. */
	@FindBy(xpath = ".//input[@id='lst-ib']")
	private WebElement SearchInputTxtBox;

	/**
	 * Gets the search input txt box.
	 *
	 * @return the search input txt box
	 */
	private WebElement get_SearchInputTxtBox() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, SearchInputTxtBox, logger)) {
			element = SearchInputTxtBox;
		}
		return element;
	}

	@FindBy(xpath = ".//input[@name='btnK']")
	private WebElement SearchButton;

	private WebElement get_SearchButton() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, SearchButton, logger)) {
			element = SearchButton;
		}
		return element;
	}

	@FindBy(xpath = ".//div[@id='ires']")
	private WebElement SearchedDataGrid;

	private WebElement get_SearchedDataGrid() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, SearchedDataGrid, logger)) {
			element = SearchedDataGrid;
		}
		return element;
	}

	public void enterSearchText(String textToSearch) {
		WebElement element = get_SearchInputTxtBox();
		try {
			if (element != null) {
				WebTextbox.clearWebInput(element);
				WebTextbox.sendTextToWebInput(element, textToSearch, logger);
				WebTextbox.sendKeysNTimes(element, 2, Keys.TAB);
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the search filed" + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void clickOnSearchButton() {
		WebElement element = get_SearchButton();
		try {
			if (element != null) {
				MouseOperations.hoverMouseOnWebElement(driver, logger, element);
				WebButton.webButtonClick(driver, logger, element);
				logger.log(LogStatus.PASS, "'Search Button' clicked successfully");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the 'Search Button'" + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public int getSearchResults() {
		WebElement element = get_SearchedDataGrid();
		List<WebElement> searchResults = null;
		if (element != null) {
			searchResults = element.findElements(By.className("g"));
		}
		return searchResults.size();
	}

	// Test Methods

	public boolean searchAndVerifyTextSearch(String textToSearch) {
		boolean testStatus = false;
		enterSearchText(textToSearch);
		clickOnSearchButton();
		logger.log(LogStatus.INFO, ReportManager.addLocalScreenshotToReport(driver,
				BaseClass.rpr.getKey("screenshotPath"), "Test", logger));
		if (getSearchResults() > 0) {
			testStatus = true;
		}

		return testStatus;

	}
}
