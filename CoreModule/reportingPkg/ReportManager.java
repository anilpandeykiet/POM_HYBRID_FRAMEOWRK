/**
 * 
 */
package reportingPkg;

import java.io.File;

import javax.swing.text.html.HTML;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


/**
 * @author Anil Pandey
 *
 */
public class ReportManager {

	private static ExtentReports INSTANCE = null;

	private ReportManager() {

	}

	public synchronized static ExtentReports getReporter(String filePath, boolean replaceExisting) {
		if (INSTANCE == null) {
			INSTANCE = new ExtentReports(filePath, replaceExisting);
			
		}
		return INSTANCE;
	}

	private static String captureScreenshot(WebDriver driver, String screenshotPath, String ScreenshotName) {

		String destinationPath = null;
		try {
			File destFolder = new File(screenshotPath);
			
			destinationPath = destFolder.getCanonicalPath() + "/" + ScreenshotName + ".png";
		
			// Cast webdriver to Screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;

			File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(sourceFile, new File(destinationPath));

		} catch (Exception e) {
			System.out.println("Error capturing screenshot...\n" + e.getMessage());
			e.printStackTrace();
		}
		return destinationPath;
	}

	public static String addLocalScreenshotToReport(WebDriver driver, String screenshotPath, String screenshotName,
			ExtentTest logger) {
		String screenshotImage = null;

		try {
			String screenshotAbsolutePath = captureScreenshot(driver, screenshotPath, screenshotName);
			screenshotImage = logger.addScreenCapture(screenshotAbsolutePath);
		} catch (Exception e) {
			System.out.println("Error capturing screenshot of application\n" + e.getMessage());
			e.printStackTrace();
		}
		return screenshotImage;
	}

	public static String addServerScreenShotToReport(WebDriver driver, String screenshotPath, String screenshotName,
			ExtentTest logger) {
		String screenImage = null;

		try {
			String screenShotAbsolutePath = captureScreenshot(driver, screenshotPath, screenshotName);
			screenShotAbsolutePath.replaceAll("//", "http://");
			screenImage = logger.addScreenCapture(screenShotAbsolutePath);
		} catch (Exception e) {
			System.out.println("Error capturing screenshot of application\n" + e.getMessage());
			e.printStackTrace();
		}
		return screenImage;
	}
	
	/**
	 * Adds the screen shot to report. This method can be used only with WebDriver
	 * tests. This is not usable for Desktop applications
	 *
	 * @param driver
	 *            {@link WebDriver}
	 * @param screenshotPath
	 *            the screenshot path
	 * @param ScreenshotName
	 *            the screenshot name
	 * @param logger
	 *            {@link ExtentTest}
	 * @return {@link HTML} tag with the screenshot path embedded
	 */
	public static String addScreenShotToReport(WebDriver driver, String screenshotPath, String ScreenshotName,
			ExtentTest logger) {
		String screenImage = null;
		try {
			String screenshoAbsolutePath = ReportManager.captureScreenshot(driver, screenshotPath, ScreenshotName);
			screenImage = logger.addScreenCapture(screenshoAbsolutePath);
		} catch (Exception e) {
			System.out.println("Error adding screenshot to report...\n" + e.getMessage());
		}
		return screenImage;
	}
}
