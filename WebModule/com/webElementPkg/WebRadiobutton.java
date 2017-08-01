/**
 * 
 */
package com.webElementPkg;

/**
 * The Class WebRadiobutton.
 *
 * @author Anil Pandey
 */
public class WebRadiobutton {
	//Get value of radio button from user
	
	public static void getRadioButtonOption(WebDriver driver, ExtentTest logger, WebElement element, String radioOption){
		try {
			if (element != null) {
				element.sendKeys(radioOption);
				logger.log(LogStatus.PASS, "Option <label>'"+radioOption +"'</label> saved successfully in <label>'"+element.getAttribute("RadioButtonOption")+"'</label>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find <label>'"+element.getAttribute("RadioButtonOption")+"'</label>");
		}
}

	//FInd the radio button corresponding the input
	
	public static void selectRadioButton (WebDriver driver, ExtentTest logger, WebElement element)

	
}
