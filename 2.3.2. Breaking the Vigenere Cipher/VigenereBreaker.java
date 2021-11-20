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

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
