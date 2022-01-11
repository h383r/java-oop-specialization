import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    
    /** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}


    public static void main (String[] args) {

		/*
        Regex reg = new Regex();
        
		String pattern = "it|st";
        String text = "Splitting a string, it's as easy as 1 2 33! Right?";
        
		List<String> test = new ArrayList<String>();
        test = reg.getTokens(pattern, text);
        
		System.out.println(test);
		*/
		

		// Practice Quiz:

		String text = "Hurray!!#! It's Friday! finally...";
		String[] words = text.split("!+");

		for (String word : words) {
			System.out.print(word + ", ");
		}


    }







}
