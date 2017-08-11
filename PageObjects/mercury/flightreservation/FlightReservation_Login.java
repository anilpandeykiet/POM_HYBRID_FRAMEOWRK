/**
 * 
 */
package mercury.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
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

	@FindBy(id = "")
	private WebElement element1;

	private WebElement get_() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, element1, logger)) {
			element = element1;
		}

		return element;
	}
}
