package frameworkTestMethods;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.webElementPkg.WebCookies;

import corePkg.BaseClass;


/**
 * @author Anil Pandey
 *
 */
public class Test2 extends BaseClass{

	@Test(description = "First test")
	public void firstTest(Method method){
		System.out.println(driver.getTitle());
		System.out.println(WebCookies.getAllCookies(driver));
		BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);

	}
	
}
