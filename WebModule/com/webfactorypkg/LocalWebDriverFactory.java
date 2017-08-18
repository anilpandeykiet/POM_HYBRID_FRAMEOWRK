/**
 * 
 */
package com.webfactorypkg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.webElementPkg.WebPage;

/**
 * @author Anil Pandey
 *
 */
public class LocalWebDriverFactory {

	private static WebDriver driver = null;

	public static WebDriver openWebUrl(String browserName, String URL) {
		String userDirectory = System.getProperty("user.dir") + "/ImpLibs/SeleniumLibs/Drivers/";
		try {
			if (browserName.toUpperCase().trim().equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", userDirectory + "chromedriver.exe");
				ChromeOptions chOption = new ChromeOptions();
				chOption.addArguments("--disable-extensions");
				chOption.addArguments("test-type");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				chOption.setExperimentalOption("prefs", prefs);

				driver = new ChromeDriver(chOption);
				driver.manage().window().maximize();

			} else if (browserName.toUpperCase().trim().equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.ie.driver", userDirectory + "IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();

			} else if (browserName.toUpperCase().trim().equalsIgnoreCase("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver", userDirectory + "geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();

			} else {
				throw new Exception("'" + browserName + "'" + " is not a valid browser");
			}
			launchUrl(URL);
		} catch (Exception e) {
			System.out.println("'" + browserName + "'" + " is not a valid browser\n" + e.getMessage());
			e.printStackTrace();
		}

		return driver;
	}

	/**
	 * @param uRL
	 */
	private static void launchUrl(String url) {
		if (driver != null && url != null) {
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
	}
}
