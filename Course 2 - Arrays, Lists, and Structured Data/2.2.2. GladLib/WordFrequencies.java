import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {

    // Unique words from a file
    private ArrayList<String> myWords;
    // Corresponding counts for each word
    private ArrayList<Integer> myFreqs;

    public WordFrequencies () {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /**
     * Set the frequency of each word in myFreqs and the word in myWords
     */
    public void findUnique () {
        
        myWords.clear();
        myFreqs.clear();
        
        FileResource file = new FileResource();
        for (String currentWord : file.words()) {

            currentWord = currentWord.toLowerCase();
            int index = myWords.indexOf(currentWord);

            if (index == -1) {
                myWords.add(currentWord);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            }
        }
    }
    
    /**
     * Returns the index location of the largest value in myFreqs.
     * @return int
     */
    public int findIndexOfMax () {

        int maxIndex = 0;

        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    /**
     * Test Cases
     */
    public void tester () {

        myFreqs.clear();
        findUnique();
        
        // print out the number of unique words
        System.out.println("Number of unique words: " + myWords.size());
        
        // for each unique word, print the frequency of each word and the word
        for (int i = 0; i < 7; i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        
        // word that occurs the most often in a selected file
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
    
    public static void main (String[] args) {
        WordFrequencies test = new WordFrequencies();
        test.tester();
    }
}
