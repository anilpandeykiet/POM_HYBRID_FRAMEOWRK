/**
 * 
 */
package googleSearchTests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import corePkg.BaseClass;
import googlePageObjects.SearchAndVerifyPageObjects;

/**
 * @author Anil Pandey
 *
 */
public class SearchAndVerifyTest extends BaseClass{

	private static SearchAndVerifyPageObjects searchPObj = null;
	private WebDriver driver = null;
	private ExtentTest logger = null;
	private static String testCategory = "Search And Verify";
	
	public SearchAndVerifyTest() {

	}
	
	private void initialize() {
		this.driver = BaseClass.driver;
		this.logger = BaseClass.logger;
		logger.assignCategory(testCategory);
		searchPObj = new SearchAndVerifyPageObjects(driver, logger);
	}
	
	@Test(description="First Test", groups= {"Search And Verify"})
	public void test123(Method method) {
		initialize();
		boolean testStatus = false;
		try {
			testStatus = searchPObj.searchAndVerifyTextSearch("Anil Kumar Pandey");
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			e.printStackTrace();
		}
	}	
}
