/**
 * 
 */
package fileReadingPkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Anil Pandey
 *
 */
public class ReadTextFile {

	private static ReadTextFile INSTANCE = null;
	private static StringBuffer stringbuffer = new StringBuffer();
	private static String fileContent = null;
	private static BufferedReader bufferedReader = null;
	private static FileReader fileReader = null;
	private static File txtFile= null;

	private ReadTextFile(String textFilePath) {
		try {
			File txtFile = new File(textFilePath);
			fileReader = new FileReader(txtFile);
			bufferedReader = new BufferedReader(fileReader);

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error reading the text file: " + txtFile + "\n" + e.getMessage());
		}
	}

	public static ReadTextFile getInstance(String textFilePath) {
		if (INSTANCE == null) {
			INSTANCE = new ReadTextFile(textFilePath);
		}
		return INSTANCE;
	}

	/**
	 * Gets the text file content.
	 *
	 * @return the text file content
	 */
	public StringBuffer getTextFileContent() {
		if (bufferedReader != null && fileReader != null) {
			try {
				while ((fileContent = bufferedReader.readLine()) != null) {
					stringbuffer.append(fileContent);
					stringbuffer.append(System.lineSeparator());
				}
			} catch (IOException e) {
				System.out.println("Error reading the text file" + e.getMessage());
			} finally {
				if (bufferedReader != null && fileReader != null) {
					try {
						bufferedReader.close();
						fileReader.close();
					} catch (IOException e) {
						System.out.println("Error closing 'bufferedReader' or 'fileReader'" + e.getMessage());
					}
				}
			}
		} else {
			System.out.println("all the readers are null....");
		}
		return stringbuffer;
	}

}
