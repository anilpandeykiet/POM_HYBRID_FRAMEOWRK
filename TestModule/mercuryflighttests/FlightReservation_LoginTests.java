/**
 * 
 */
package mercuryflighttests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import corePkg.BaseClass;
import mercury.flightreservation.FlightReservation_Login;

/**
 * @author Anil Pandey
 *
 */
public class FlightReservation_LoginTests extends BaseClass {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	private static FlightReservation_Login frLoginPage = null;

	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;

		frLoginPage = new FlightReservation_Login(driver, logger);
	}

	@Parameters({ "username", "password" })
	@Test(description = "Successful Login to Application")
	public void successfulLogin(String username, String password, Method method) {
		intialize();
		boolean testStatus = false;
		try {
			String homePageTitle = "Find a Flight: Mercury Tours:";
			testStatus = frLoginPage.loginToApplication(username, password, homePageTitle);
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
		}
	}
}
