package corePkg;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webfactorypkg.LocalWebDriverFactory;

import emailPkg.EmailSender;
import reportingPkg.ReportManager;

public class BaseClass {

	public static WebDriver driver = null;
	public static ExtentReports reporter = null;
	public static ExtentTest logger = null;

	// Config File path...Required to initialize the framework...
	public static String baseURL = "http://www.google.com";
	public static String browserName = "chrome";
	public static String testMethodName = null;
	public static String reportFile = "";

	@BeforeSuite
	public static void beforeSuite() {
		String currentUserDir = System.getProperty("user.dir");
		reportFile = currentUserDir + ("/TestResources/TestReports/testReporter.html");
		System.out.println(reportFile);
		reporter = ReportManager.getReporter(reportFile, true);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			testMethodName = method.getName();
			logger = reporter.startTest(testMethodName);
			driver = LocalWebDriverFactory.openWebUrl(browserName, baseURL);
		} catch (Exception e) {
			System.out.println("Error ocurred in @BeforeMethod BaseClass..." + e.getMessage());
		}
	}

	@AfterMethod
	public void aftreMethod() {
		try {
			reporter.endTest(logger);
			driver.quit();
		} catch (Exception e) {
			System.out.println("Error in @AfterTest method in BaseClass \n"+e.getMessage());
		}
	}

	@AfterSuite
	public void afterSuite() {
		reporter.flush();
		EmailSender.sendEmail(false);
	}

	public static void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED.";
				logger.log(LogStatus.PASS, passMessage);
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED.";
				logger.log(LogStatus.FAIL, failMessage);
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
		}
	}
}
