/**
 * 
 */
package corePkg;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mobile.factory.MobileWebDriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebUtilities;

import fileReadingPkg.ReadPropertyFile;
import reportingPkg.ReportManager;

/**
 * @author Anil Pandey
 *
 */
public class BaseClassMobile {

	public static WebDriver driver = null;
	public static ExtentReports reporter = null;
	public static ExtentTest logger = null;

	// Config File path...Required to initialize the framework...

	public static String testMethodName = null;
	public static String reportFile = "";
	public static String emailConfigFile = null;

	public static ReadPropertyFile rpr = null;
	private static String sendEmail = null;

	private static String deviceName = null;
	private static String appType = null;
	private static String browserName = null;
	private static String platformVersion = null;
	private static String platformName = null;
	private static String apkPath = null;
	private static String appPackage = null;
	private static String appActivity = null;
	private static String baseURL = null;

	@BeforeSuite
	public void beforeSuite() {
		MobileWebDriverFactory.startAppiumServer();
		rpr = ReadPropertyFile.getInstance("./TestResources/TestConfig/mobileTestConfig.properties");

		deviceName = rpr.getKey("deviceName");
		appType = rpr.getKey("appType");
		browserName = rpr.getKey("browserName");
		platformVersion = rpr.getKey("platformVersion");
		platformName = rpr.getKey("platformName");
		apkPath = rpr.getKey("apkPath");
		appPackage = rpr.getKey("appPackage");
		appActivity = rpr.getKey("appActivity");
		baseURL = rpr.getKey("baseURL");

		reportFile = rpr.getKey("reportFile");
		reporter = ReportManager.getReporter(reportFile, true);

		emailConfigFile = rpr.getKey("emailConfigFile");
		sendEmail = rpr.getKey("sendEmail");
	}

	@BeforeClass
	public void beforeClass() {

	}

	@BeforeMethod
	public void beforeTest(Method method) {

		testMethodName = method.getAnnotation(Test.class).description();
		logger = reporter.startTest(browserName.toUpperCase() + " - " + testMethodName);

		try {
			if (appType.equalsIgnoreCase("WebView")) {
				driver = MobileWebDriverFactory.createDefaultDriverSession(deviceName, platformName, browserName,
						baseURL);
			} else if (appType.equalsIgnoreCase("NativeApp")) {
				driver = MobileWebDriverFactory.createNewDriverForApp(deviceName, platformName, platformVersion, apkPath, appPackage,
						appActivity);
			} else {
				System.out.println("Wrong app type entered '" + appType + "'. Valid values are \"NativeApp, WebView\"");
			}
		} catch (Exception e) {
			System.out.println("Error in @BeforeSuite method");
		}
	}

	@Test
	public void test() {
		WebUtilities.staticWait(driver, 5000);
	}

	@AfterMethod
	public void afterTest() {
		try {

		} finally {
			driver.quit();
			reporter.endTest(logger);
		}
	}

	@AfterClass
	public void afterClass() {
		
		try {
			
		} finally {
			MobileWebDriverFactory.stopAppiumServer();
		}
	}

	@AfterSuite
	public void afterSuite() {
		reporter.flush();
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
