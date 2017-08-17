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
import com.webElementPkg.WebDropdown;
import com.webElementPkg.WebRadiobutton;
import com.webElementPkg.WebUtilities;

/**
 * @author Anil Pandey
 *
 */
public class FlightReservation_FlightFinder_Page {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public FlightReservation_FlightFinder_Page(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	// Flight Details
	@FindBy(xpath = ".//input[@value='roundtrip']")
	private WebElement roundTripRadioBtn;

	private WebElement get_roundTripRadioBtn() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, roundTripRadioBtn, logger)) {
			element = roundTripRadioBtn;
		}
		return element;
	}

	@FindBy(xpath = ".//input[@value='oneway']")
	private WebElement oneWayRadioBtn;

	private WebElement get_oneWayRadioBtn() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, oneWayRadioBtn, logger)) {
			element = oneWayRadioBtn;
		}
		return element;
	}

	@FindBy(name = "passCount")
	private WebElement passengerCountDD;

	private WebElement get_passengerCountDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, passengerCountDD, logger)) {
			element = passengerCountDD;
		}
		return element;
	}

	@FindBy(name = "fromPort")
	private WebElement departFromDD;

	private WebElement get_departFromDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, departFromDD, logger)) {
			element = departFromDD;
		}
		return element;
	}

	@FindBy(name = "fromMonth")
	private WebElement fromMonthDD;

	private WebElement get_fromMonthDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, fromMonthDD, logger)) {
			element = fromMonthDD;
		}
		return element;
	}

	@FindBy(name = "fromDay")
	private WebElement fromDayDD;

	private WebElement get_fromDayDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, fromDayDD, logger)) {
			element = fromDayDD;
		}
		return element;
	}

	@FindBy(name = "toPort")
	private WebElement arrivingInDD;

	private WebElement get_arrivingInDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, arrivingInDD, logger)) {
			element = arrivingInDD;
		}
		return element;
	}

	@FindBy(name = "toMonth")
	private WebElement returnMonthDD;

	private WebElement get_returnMonthDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, returnMonthDD, logger)) {
			element = returnMonthDD;
		}
		return element;
	}

	@FindBy(name = "toDay")
	private WebElement returnDateDD;

	private WebElement get_returnDateDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, returnDateDD, logger)) {
			element = returnDateDD;
		}
		return element;
	}

	// Preferences

	@FindBy(xpath = ".//input[@name='servClass'][@value='Coach']")
	private WebElement economyServiceClass;

	private WebElement get_economyServiceClass() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, economyServiceClass, logger)) {
			element = economyServiceClass;
		}
		return element;
	}

	@FindBy(xpath = ".//input[@name='servClass'][@value='Business']")
	private WebElement businessServiceClass;

	private WebElement get_businessServiceClass() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, businessServiceClass, logger)) {
			element = businessServiceClass;
		}
		return element;
	}

	@FindBy(xpath = ".//input[@name='servClass'][@value='First']")
	private WebElement firstServiceClass;

	private WebElement get_firstServiceClass() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, firstServiceClass, logger)) {
			element = firstServiceClass;
		}
		return element;
	}

	@FindBy(name = "airline")
	private WebElement airlinePreferenceDD;

	private WebElement get_airlinePreferenceDD() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, airlinePreferenceDD, logger)) {
			element = airlinePreferenceDD;
		}
		return element;
	}

	@FindBy(name = "findFlights")
	private WebElement continueButton;

	private WebElement get_continueButton() {
		WebElement element = null;

		if (WebUtilities.waitForElementToAppear(driver, continueButton, logger)) {
			element = continueButton;
		}
		return element;
	}

	// WebElement Action methods
	private void selectTripType(String tripType) {
		WebElement element = null;
		System.out.println("Trip type: "+tripType.toUpperCase());
		
		switch (tripType.toUpperCase()) {
		case "ROUND TRIP":
			element = get_roundTripRadioBtn();
			break;

		case "ONE WAY":
			element = get_oneWayRadioBtn();
			break;

		default:
			logger.log(LogStatus.ERROR, "Invalid Trip type.");
			break;
		}
		WebRadiobutton.selectRadioButton(driver, logger, element);
	}

	private void selectNoOfPassengers(String noOfPassengers) {
		WebElement element = get_passengerCountDD();

		if (element != null) {
			WebDropdown.selectOptionByVisibleText(element, noOfPassengers);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(element) + " selected successfully");
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'Passengers' dropdown");
		}
	}

	private void selectDepartingFrom(String departFrom) {
		WebElement element = get_departFromDD();
		if (element != null) {
			WebDropdown.selectOptionByVisibleText(element, departFrom);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(element) + " selected successfully");
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'Departing From' dropdown");
		}
	}

	private void selectDepartureDate(String monthName, String departDay2) {
		WebElement month = get_fromMonthDD();
		WebElement departDay = get_fromDayDD();

		if (month != null && departDay != null) {
			WebDropdown.selectOptionByVisibleText(month, monthName);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(month) + " selected successfully");

			WebDropdown.selectOptionByVisibleText(departDay, departDay2);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(departDay) + " selected successfully");
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'On Month and Day' dropdown");
		}
	}

	private void selectArrivingIn(String arrivingIn) {
		WebElement element = get_arrivingInDD();
		if (element != null) {
			WebDropdown.selectOptionByVisibleText(element, arrivingIn);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(element) + " selected successfully");
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'Arriving In' dropdown");
		}
	}

	private void selectArrivalDate(String monthName, String arrivingDay) {
		WebElement month = get_returnMonthDD();
		WebElement returnDate = get_returnDateDD();

		if (month != null && returnDate != null) {
			WebDropdown.selectOptionByVisibleText(month, monthName);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(month) + " selected successfully");

			WebDropdown.selectOptionByVisibleText(returnDate, arrivingDay);
			logger.log(LogStatus.INFO, WebDropdown.getCurrentSelectdOption(returnDate) + " selected successfully");
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'On Month and Day' dropdown");
		}
	}

	private void selectServiceClass(String serviceClass) {
		WebElement element = null;

		switch (serviceClass.toUpperCase()) {
		case "ECONOMY CLASS":
			element = get_economyServiceClass();
			break;
		case "BUSINESS CLASS":
			element = get_businessServiceClass();
			break;
		case "FIRST CLASS":
			element = get_firstServiceClass();
			break;

		default:
			logger.log(LogStatus.ERROR, "'" + serviceClass + "' not a valid Service Class");
			break;
		}
		WebRadiobutton.selectRadioButton(driver, logger, element);
	}

	private void selectAirLinePreference(String airlineName) {
		WebElement element = get_airlinePreferenceDD();

		if (element != null) {
			WebDropdown.selectOptionByVisibleText(element, airlineName);
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'Airline' " + airlineName + "'");
		}
	}

	private void clickOnContinueButton() {
		WebElement element = get_continueButton();

		if (element != null) {
			WebButton.webButtonClick(driver, logger, element);
		} else {
			logger.log(LogStatus.ERROR, "Failed to find the 'Continue' button");
		}
	}

	/**
	 * Find A new flight.
	 *
	 * @param tripType
	 *            the trip type
	 * @param noOfPassengers
	 *            the no of passengers
	 * @param departFrom
	 *            the depart from
	 * @param departmonth
	 *            the departmonth
	 * @param departDay
	 *            the depart day
	 * @param arrivingIn
	 *            the arriving in
	 * @param arrivingMonth
	 *            the arriving month
	 * @param arrivingDay
	 *            the arriving day
	 * @param serviceClass
	 *            the service class
	 * @param airlineName
	 *            the airline name
	 */
	public boolean findANewFlight(String tripType, String noOfPassengers, String departFrom, String departmonth,
			String departDay, String arrivingIn, String arrivingMonth, String arrivingDay, String serviceClass,
			String airlineName) {

		boolean testStatus = false;

		selectTripType(tripType);
		selectNoOfPassengers(noOfPassengers);
		selectDepartingFrom(departFrom);
		selectDepartureDate(departmonth, departDay);
		selectArrivingIn(arrivingIn);
		selectArrivalDate(arrivingMonth, arrivingDay);
		selectServiceClass(serviceClass);
		selectAirLinePreference(airlineName);
		clickOnContinueButton();

		if (driver.getTitle().trim().equalsIgnoreCase("Select a Flight: Mercury Tours")) {
			testStatus = true;
		}
		return testStatus;
	}

}
