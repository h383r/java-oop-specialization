package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.  
		int numSteps = 12;
		
		// THe number of characters to start with. 
		int start = 50000;

		System.out.println(
			"NumberOfChars" + 
			"\t" + 
			"BasicTime" + 
			"\t" + 
			"EfficientTime"
			);

		for (int numToCheck = start; numToCheck < numSteps*increment + start; numToCheck += increment) {
			// numToCheck holds the number of characters that you should read from the 
			// file to create both a BasicDocument and an EfficientDocument.  
			
			// 1. Print out numToCheck followed by a tab (\t) (NOT a newline)
			System.out.print(numToCheck + "\t");

			// 2. Read numToCheck characters from the file into a String
			final String text = getStringFromFile(textfile, numToCheck);

			// 3. Time a loop that runs trials times (trials is the variable above) that:
			long basicDocumentStart = System.nanoTime();

			for (int i = 0; i < trials; i++) {

				// a. Creates a BasicDocument 
				BasicDocument basicDocument = new BasicDocument(text);
				// b. Calls fleshScore on this document
				basicDocument.getFleschScore();
			}
			// 4. Print out the time it took to complete the loop in step 3 
			long basicDocumentEnd = System.nanoTime();
			double basicDocumentTotalTime = (double) (basicDocumentEnd - basicDocumentStart) / 1000000000;
			System.out.print(basicDocumentTotalTime + "\t");
			
			// 5. Time a loop that runs trials times (trials is the variable above) that:
			long efficientDocumentStart = System.nanoTime();
			
			for (int i = 0; i < trials; i++) {
				
				// a. Creates an EfficientDocument
				EfficientDocument efficientDocument = new EfficientDocument(text);
				
				// b. Calls fleshScore on this document
				efficientDocument.getFleschScore();
			}
			// 6. Print out the time it took to complete the loop in step 5 
			long efficientDocumentEnd = System.nanoTime();
			double efficientDocumentTotalTime = (double) (efficientDocumentEnd - efficientDocumentStart) / 1000000000;
			System.out.print(efficientDocumentTotalTime + "\n");
			 
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
