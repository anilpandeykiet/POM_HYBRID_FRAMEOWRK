/**
 * 
 */
package mercury.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

/**
 * @author Anil Pandey
 *
 */
public class FlightReservation_Login {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public FlightReservation_Login(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	// WebElements Objects

	@FindBy(name = "userName")
	private WebElement username;

	private WebElement get_username() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, username, logger)) {
			element = username;
		}

		return element;
	}

	@FindBy(name = "password")
	private WebElement password;

	private WebElement get_password() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, password, logger)) {
			element = password;
		}
		return element;
	}

	@FindBy(name = "login")
	private WebElement signInButton;

	private WebElement get_signInButton() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, signInButton, logger)) {
			element = signInButton;
		}
		return element;
	}

	// WebElement methods

	private void enterUserName(String username) {
		WebElement element = get_username();

		try {
			if (element != null) {
				WebTextbox.clearTextBox(element);
				WebTextbox.sendTextToWebInput(element, username, logger);
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error occured while finding username field..</br>" + e.getLocalizedMessage());
		}
	}

	private void enterPassword(String password) {
		WebElement element = get_password();

		try {
			if (element != null) {
				WebTextbox.clearTextBox(element);
				WebTextbox.sendTextToWebInput(element, password, logger);
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error occured while finding password field..</br>" + e.getLocalizedMessage());
		}
	}

	private void clickOnSingInButton() {
		WebElement element = get_signInButton();

		try {
			if (element != null) {
				WebButton.webButtonClick(driver, logger, element);
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error occured while finding Sign In button..</br>" + e.getLocalizedMessage());
		}
	}

	public boolean loginToApplication(String username, String password, String homePageTitle) {
		boolean loginStatus = false;
		enterUserName(username);
		enterPassword(password);
		clickOnSingInButton();
		
		String pageTitle = driver.getTitle().trim();
		
		System.out.println("pageTitle: "+pageTitle);
		if (pageTitle.equalsIgnoreCase(homePageTitle.trim())) {
			System.out.println("Login successful");
			loginStatus = true;
		}
		return loginStatus;
	}
}
