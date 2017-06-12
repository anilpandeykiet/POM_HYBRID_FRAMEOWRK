/**
 * 
 */
package com.webfactorypkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author cpandeak
 *
 */
public class LocalWebDriverFactory {

	private static WebDriver driver = null;

	public static WebDriver openWebUrl(String browserName, String URL) {
		String userDirectory = System.getProperty("user.dir") + "/ImpLibs/SeleniumLibs/Drivers/";
		try {
			if (browserName.trim().equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", userDirectory + "chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				launchUrl(URL);

			} else if (browserName.trim().equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.ie.driver", userDirectory + "IEDriverServer.exe");
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().fullscreen();
				launchUrl(URL);

			} else if (browserName.trim().equalsIgnoreCase("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver", userDirectory + "geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().fullscreen();

			} else {
				throw new Exception("'" + browserName + "'" + " is not a valid browser");
			}
		} catch (Exception e) {
			System.out.println("'" + browserName + "'" + " is not a valid browser\n" + e.getMessage());
		}

		return driver;
	}

	/**
	 * @param uRL
	 */
	private static void launchUrl(String url) {
		if (driver != null) {
			driver.navigate().to(url);
		}
	}
}
