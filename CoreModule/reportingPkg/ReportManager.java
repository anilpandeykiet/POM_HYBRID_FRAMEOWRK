/**
 * 
 */
package reportingPkg;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author cpandeak
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

		String destinationFile = null;

		try {
			char getLastCharacterInPath = screenshotPath.charAt(screenshotPath.length() - 1);

			if (!Character.toString(getLastCharacterInPath).equalsIgnoreCase("/")) {
				destinationFile = screenshotPath + "/" + ScreenshotName + ".png";
			}

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourceFile = (File) screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(destinationFile));

		} catch (Exception e) {
			System.out.println("Error capturing screenshot\n" + e.getMessage());
		}

		return destinationFile;
	}

	public static String addLocalScreenshotToReport(WebDriver driver, String screenshotPath, String screenshotName,
			ExtentTest logger) {
		String screenshotImage = null;

		try {
			String screenshotAbsolutePath = captureScreenshot(driver, screenshotPath, screenshotName);
			screenshotImage = logger.addScreenCapture(screenshotAbsolutePath);
		} catch (Exception e) {
			System.out.println("Error capturing screenshot of application\n" + e.getMessage());
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
		}
		return screenImage;
	}
}
