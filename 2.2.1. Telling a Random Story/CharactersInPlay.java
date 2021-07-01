import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    // Names of the characters you find
    private ArrayList<String> myChar = new ArrayList<String>();
    // Corresponding counts for each character
    private ArrayList<Integer> myFreqs = new ArrayList<Integer>();

    public CharactersInPlay() {
        myChar = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /**
     * Set the character´s names in myChar and increase the counter in myFreqs
     * @param person
     */
    public void update (String person) {

        int index = myChar.indexOf(person);
        
        if (index == -1) {
            myChar.add(person);
            myFreqs.add(1);
        }
        else {
            int freq = myFreqs.get(index);
            myFreqs.set(index, freq + 1);
        }
    }


    /**
     * Opens and reads the file linebyline.
     * For each line, if there is a period on the line, extract the name and 
     * call update to count it as an occurrence for this person.
     */
    public void findAllCharacters () {

        myChar.clear();
        myFreqs.clear();
        
        FileResource file = new FileResource();
        for (String currentLine : file.lines()) {
            
            int periodIndex = currentLine.indexOf(".");

            if (periodIndex != -1) {
                String currentChar = currentLine.substring(2, (periodIndex));
                update(currentChar);
            }
        }
    }

    /**
     * Print out the names of all those characters that have exactly 
     * number speaking parts, where number is greater than or equal to num1 
     * and less than or equal to num2
     * @param num1
     * @param num2
     */

    public void charactersWithNumParts (int num1, int num2) {
        
        for (int i = 0; i < myChar.size(); i++) {
            int freq = myFreqs.get(i);
            
            if (freq >= num1 && freq <= num2) {
                System.out.println(myChar.get(i));
            }
        }
    }

    /**
     * Test Cases
     */
    public void tester () {
        
        System.out.println("All Characters:");
        findAllCharacters();
        
        // For each main character, print out the main character, followed by the number of speaking parts that character has
        for (int i = 0; i < myChar.size(); i++) {
            
            int count = myFreqs.get(i);

            if (count > 2 ) {
                System.out.println(myChar.get(i) + " " + myFreqs.get(i));
            }
        }

        System.out.println("Main Characters:");
        charactersWithNumParts(10, 15);
    }

    public static void main (String[] args) {
        CharactersInPlay test = new CharactersInPlay();
        test.tester();
    }
}
