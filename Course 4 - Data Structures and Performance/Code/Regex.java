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
		
		Regex reg = new Regex();

		//String d = "this is a test.23,54,390.";
		//List<String> tokens = reg.getTokens("[a-z0-9 ]+", d);
		//List<String> tokens = reg.getTokens("[a-z0-9]+", d);
		//List<String> tokens = reg.getTokens("[a-z ]+|[0-9]+", d);
		//List<String> tokens = reg.getTokens("[^.,]+", d);
		//List<String> tokens = reg.getTokens("[^ ]+", d);
		
		String d = "one (1), two (2), three (3)";
		List<String> tokens = reg.getTokens("[^, ]+", d);
		//List<String> tokens = reg.getTokens("[^,]+", d);
		//List<String> tokens = reg.getTokens("[a-z()0-9]+", d);
		//List<String> tokens = reg.getTokens("[a-z()0-9]+", d);

		for (String token : tokens) {
			System.out.print(token + ", ");
		}


		/*
        
		String pattern = "it|st";
        String text = "Splitting a string, it's as easy as 1 2 33! Right?";
        
		List<String> test = new ArrayList<String>();
        test = reg.getTokens(pattern, text);
		System.out.println(test);
		
		
		String text = "Hurray!!#! It's Friday! finally...";
		String[] words = text.split("!+");
		
		for (String word : words) {
			System.out.print(word + ", ");
		}
		*/



		
		
    }







}
