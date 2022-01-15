// This file contains the solution for the getNumSyllables
// method.  You can use this code if you get totally 
// stuck, or simply get some hints from it if you 
// stuck.  

// It is a .java file because it contains Java code
// but it will not compile as-is.  
// If you want to use this code, you should copy
// and paste the getNumSyllables method into the 
// BasicDocument class (replacing the method of the same 
// name that is already there. Then copy and paste the method
// countSyllables into the class Document, again replacing
// the method of the same name that appears in the starter code.

    // **** IN THE CLASS BasicDocument (file: BasicDocument.java)
	/**
     * 
	 * Get the number of sentences in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for an "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		// We provide for you two solutions: One that uses multiple 
		// regexs to calculate the number of syllables and the other
		// that finds words using a loop.  The regex solution is commented 
		// out here at the top.

		/* Our solution using regex's.  Uncoment here to run it*/
		/*
		List<String> tokens = getTokens("[aeiouyAEIOUY]+");
		List<String> loneEs = getTokens("[^aeiouyAEIOUY]+[eE]\\b");
		List<String> singleEs = getTokens("\\b[^aeiouyAEIOUY]*[eE]\\b");
		
		
		return tokens.size() - (loneEs.size() - singleEs.size());
		*/
		
		/* Our solution that does NOT use regexs to find syllables */
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens)
		{
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	
	}
    
    // *** IN THE CLASS: Document (file: Document.java)
    // This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	protected static int countSyllables(String word)
	{
	    //System.out.print("Counting syllables in " + word + "...");
		int numSyllables = 0;
		boolean newSyllable = true;
		String vowels = "aeiouy";
		char[] cArray = word.toCharArray();
		for (int i = 0; i < cArray.length; i++)
		{
		    if (i == cArray.length-1 && Character.toLowerCase(cArray[i]) == 'e' 
		    		&& newSyllable && numSyllables > 0) {
                numSyllables--;
            }
		    if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
				newSyllable = false;
				numSyllables++;
			}
			else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
				newSyllable = true;
			}
		}
		//System.out.println( "found " + numSyllables);
		return numSyllables;
	}
