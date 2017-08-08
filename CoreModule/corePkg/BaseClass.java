package corePkg;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webfactorypkg.LocalWebDriverFactory;

import emailPkg.EmailSender;
import fileReadingPkg.ReadPropertyFile;
import reportingPkg.ReportManager;

public class BaseClass {

	public static WebDriver driver = null;
	public static ExtentReports reporter = null;
	public static ExtentTest logger = null;

	// Config File path...Required to initialize the framework...
	private static String baseURL = null;
	private static String browserName = null;
	public static String testMethodName = null;
	public static String reportFile = "";
	public static String emailConfigFile = null;

	public static ReadPropertyFile rpr = null;
	private static String sendEmail = null;

	@BeforeSuite
	public void beforeSuite() {
		try {

			rpr = ReadPropertyFile.getInstance("./TestResources/TestConfig/test.properties");

			reportFile = rpr.getKey("reportFile");
			emailConfigFile = rpr.getKey("emailConfigFile");
			sendEmail = rpr.getKey("sendEmail");
			baseURL = rpr.getKey("baseURL");
			browserName = rpr.getKey("browserName");
			reporter = ReportManager.getReporter(reportFile, true);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured in @BeforeSuite");
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			testMethodName = method.getName();
			logger = reporter.startTest(browserName.toUpperCase() + " - "+ testMethodName);
			driver = LocalWebDriverFactory.openWebUrl(browserName, baseURL);
		} catch (Exception e) {
			System.out.println("Error ocurred in @BeforeMethod BaseClass..." + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void aftreMethod() {
		try {
			reporter.endTest(logger);
			driver.quit();
		} catch (Exception e) {
			System.out.println("Error in @AfterTest method in BaseClass \n" + e.getMessage());
			reporter.endTest(logger);
			driver.quit();
		}
	}

	@AfterSuite
	public void afterSuite() {
		reporter.flush();
		// EmailSender.sendEmail(sendEmail, emailConfigFile);
	}

	public static void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {
		String screenshotPath = rpr.getKey("screenshotPath");
	
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED.";

				logger.log(LogStatus.PASS, passMessage,
						ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED.";
				logger.log(LogStatus.PASS, failMessage,
						ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
