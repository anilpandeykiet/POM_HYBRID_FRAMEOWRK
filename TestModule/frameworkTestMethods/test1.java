package frameworkTestMethods;

import emailPkg.EmailSender;
import fileReadingPkg.ReadPropertyFile;


/**
 * 
 */

/**
 * @author Anil Pandey
 *
 */
public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadPropertyFile rpr = ReadPropertyFile.getInstance("./TestResources/TestConfig/test.properties");
		
		EmailSender.sendEmail("true", rpr.getKey("emailConfigFile"));
/*		System.out.println(System.getProperty("user.dir"));
		
		Set<String> props = rpr.getAllProperties();
		
		System.out.println(rpr.getKey("emailBodyTextFile"));

		Iterator<String> iterator = props.iterator();
		
		
		ReadTextFile rt = ReadTextFile.getInstance("./TestResources/TestConfig/EmailBodyContent.txt");*/
		
	//	System.out.println(rt.getTextFileContent());
		
		
/*		while(iterator.hasNext()){
			String text= iterator.next();
			System.out.println(text+"="+rpr.getKey(text));
		}*/
	}

}
