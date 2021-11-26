import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    /**
     * Returns a String consisting of every totalSlices-th character from message,
     * starting at the whichSlice-th character.
     * @param message encrypted message
     * @param whichSlice index the slice should start from
     * @param totalSlices length of the key
     * @return
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
       
        StringBuilder sliced = new StringBuilder();
        int messageLength = message.length();

        for ( int i = whichSlice; i < messageLength; i = i + totalSlices ) {
            char character = message.charAt(i);
            sliced.append(character);
        }
        
        String output = sliced.toString();
        return output;
    }

    /**
     * Returns the shift for each index in the key.
     * @param encrypted encrypted message
     * @param klength key length
     * @param mostCommon most common character in the language of the message
     * @return
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);

        for ( int i = 0; i < klength; i++) {
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cracker.getKey(sliced);
        }
        return key;
    }

    /**
     * Cracks the cipher used on a message
     */
    public void breakVigenere () {
       
        FileResource file = new FileResource();
        String message = file.asString();

        int[] key = tryKeyLength(message, 5, 'e');
        VigenereCipher cipher = new VigenereCipher(key);
        String messageDecrypted = cipher.decrypt(message);

        System.out.println("Keys used: ");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }

        System.out.println("Decrypted message:");
        System.out.println(messageDecrypted);
        
    }
    
    /**
     * Read each line in file, convert that line to lower case and put that line
     * into a HashSet representing the words in a dictionary.
     * @param file dictionary
     * @return
     */
    public HashSet<String> readDictionary (FileResource file) {

        HashSet<String> words = new HashSet<String>();

        for (String word : file.lines()) {
            word = word.toLowerCase();
            words.add(word);
        }
        return words;
    }

    /**
     * Split the message into words, iterate over those words, and see how many of 
     * them are “real words”—that is, how many appear in the dictionary.
     * Returns the integer count of how many valid words it found.
     * @param message from file
     * @param dictionary from readDictionary method
     * @return
     */
    public int countWords (String message, HashSet<String> dictionary) {

        int countWords = 0;
        String[] words = message.split("\\W+"); // Returns a String array

        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                countWords = countWords + 1;
            }
        }
        return countWords;
    }

    



}
