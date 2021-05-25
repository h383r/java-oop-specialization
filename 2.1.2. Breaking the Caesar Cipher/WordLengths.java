import edu.duke.FileResource;

public class WordLengths {

    /**
     * Read in the words from resource and count the number of words
     * of each length for all the words in resource, storing these
     * counts in the array counts. 
     */
    public void countWordLengths(FileResource resource, int[] counts) {
        
        for(String word : resource.words()) {
            word = word.toLowerCase();

            char ch = word.charAt(word.length()-1);
            
            if(Character.isLetter(ch)) {
                counts[word.length()] += 1;
            }
            else {
                counts[word.length() -1] += 1;
            }
        }
    }
    
    /**
     * Returns the index position of the largest element in values.
     */
    public int indexOfMax(int[] values) {
        
        int max = values[0];
        
        for(int i = 0; i < values.length; i++) {
            
            if(values[i] > values[max]) {
                max = i;
            }
        }
        return max;
    }
    
    /**
     * Test Case
     */
    public void countWordLengthsTest() {
        
        // Test in smallHamlet.txt
        FileResource resource = new FileResource();
        int[] counts = new int[31];

        countWordLengths(resource, counts);

        for(int i = 0; i < counts.length; i++) {
            System.out.println(counts[i] + " words of length " + i);
        }
        System.out.println("Largest element in values = " + indexOfMax(counts));
    }    

    /**
     * Check if the last character of the String word is not a letter.
     */
    public void checkCharacter(String word) {

        int wordlength = 0;

        if (Character.isLetter(word.charAt(word.length()-1)) == false) {
        //if ( ! Character.isLetter(word.charAt(word.length()-1))) {
            wordlength--;
        }
        System.out.println(wordlength);
    }



    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.countWordLengthsTest();

        //wordLengths.checkCharacter("asd32fsdf3");


        
    }
}