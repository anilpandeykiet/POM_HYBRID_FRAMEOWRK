/**
 * 
 */
package corePkg;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mobile.factory.MobileWebDriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.webElementPkg.WebUtilities;

import fileReadingPkg.ReadPropertyFile;
import io.appium.java_client.remote.MobilePlatform;

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
	private static String androidVersion = null;
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
		androidVersion = rpr.getKey("androidVersion");
		platformName = rpr.getKey("platformName");
		apkPath = rpr.getKey("apkPath");
		appPackage = rpr.getKey("appPackage");
		appActivity = rpr.getKey("appActivity");
		baseURL = rpr.getKey("baseURL");
	}

	@BeforeClass
	public void beforeClass() {
		
	}

	@BeforeMethod
	public void beforeTest(Method method) {
		try {
			if (appType.equalsIgnoreCase("WebView")) {
				driver = MobileWebDriverFactory.createDefaultDriverSession("6ab24750", MobilePlatform.ANDROID,
						browserName, baseURL);
			} else if (appType.equalsIgnoreCase("NativeApp")) {
				driver = MobileWebDriverFactory.createNewDriverForApp("6ab24750", MobilePlatform.ANDROID,
						"in.amazon.mShop.android.shopping", "com.amazon.mShop.splashscreen.StartupActivity");
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
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		MobileWebDriverFactory.stopAppiumServer();
	}

}
