/**
 * 
 */
package fileReadingPkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ReadPropertyFile {

	private static ReadPropertyFile INSTANCE = null;
	private Properties properties = null;

	/**
	 * Instantiates a new read property file.
	 *
	 * @param filePath
	 *            the file path<br>
	 *            Example:<b> ./configuration/testconfig.properties</b>
	 */
	private ReadPropertyFile(String filePath) {
		InputStream inputStream = null;
		try {
			File propFile = new File(filePath);
			properties = new Properties();
			inputStream = new FileInputStream(propFile);

			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (Exception e) {
			
			//System.out.println("Error Reading property file"+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Gets the single instance of ReadPropertyFile.
	 *
	 * @param filePath
	 *            the file path
	 * @return single instance of ReadPropertyFile
	 */
	public static ReadPropertyFile getInstance(String filePath) {
		//if (INSTANCE == null) {
			INSTANCE = new ReadPropertyFile(filePath);
		//}
		return INSTANCE;
	}

	/**
	 * Gets the key.
	 *
	 * @param keyName
	 *            the key name
	 * @return the key
	 */
	public String getKey(String keyName) {
		
		String value = null;
		try {
			value= properties.getProperty(keyName);
		} catch (Exception e) {
			System.out.println("Error reading the property: "+keyName+"\n"+e.getMessage());
		}
		
		return value;
	}

	/**
	 * Gets the all properties.
	 *
	 * @return the all properties
	 */
	public Set<String> getAllProperties() {
		Set<String> value = null;
		try {
			value = null;
			value = properties.stringPropertyNames();
		} catch (Exception e) {
			System.out.println("Error reading all the properties\n"+e.getMessage());
		}
		
		return value;
	}

	/**
	 * Contains key.
	 *
	 * @param keyName
	 *            the key name
	 * @return true, if successful
	 */
	public boolean containsKey(String keyName) {
		boolean contains= false;
		try {
			contains = properties.containsKey(keyName);
		} catch (Exception e) {
			System.out.println("'"+keyName+"' not found...");
		}
		
		return contains;
	}

}
