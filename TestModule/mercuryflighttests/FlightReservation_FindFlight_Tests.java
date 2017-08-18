/**
 * 
 */
package mercuryflighttests;

import java.lang.reflect.Method;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import corePkg.BaseClass;
import mercury.flightreservation.FlightReservation_FlightFinder_Page;
import mercury.flightreservation.FlightReservation_Login;

/**
 * @author Anil Pandey
 *
 */
public class FlightReservation_FindFlight_Tests extends BaseClass {

	private static FlightReservation_Login loginPage = null;
	private static FlightReservation_FlightFinder_Page flightFinderPage = null;
	private static String testCategory = "Mercury Flight Reservation - Find Flight";

	private static void initialize() {
		loginPage = new FlightReservation_Login(driver, logger);
		flightFinderPage = new FlightReservation_FlightFinder_Page(driver, logger);
		logger.assignCategory(testCategory);
	}

	@Parameters({ "username", "password", "tripType", "noOfPassengers", "departFrom", "departmonth", "departDay",
			"arrivingIn", "arrivingMonth", "arrivingDay", "serviceClass", "airlineName" })
	@Test(description = "Mercury Flight Reservation - Find a flight")
	public void findANewFlight(String username, String password, String tripType, String noOfPassengers,
			String departFrom, String departmonth, String departDay, String arrivingIn, String arrivingMonth,
			String arrivingDay, String serviceClass, String airlineName, Method method) {

		try {
			initialize();

			boolean testStatus = false;
			String homePageTitle = "Find a Flight: Mercury Tours:";
			boolean loginStatus = loginPage.loginToApplication(username, password, homePageTitle);

			if (loginStatus) {

				testStatus = flightFinderPage.findANewFlight(tripType, noOfPassengers, departFrom, departmonth,
						departDay, arrivingIn, arrivingMonth, arrivingDay, serviceClass, airlineName);
			}

			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
		}

	}
}
