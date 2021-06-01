import edu.duke.*;

public class TestCaesarCipher {

    /**
     * Returns the index of the most frecuent letter in String message.
     * @param message
     * @return
     */
    public int[] countLetters (String message) {
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int i = 0; i < message.length(); i++) {

            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);

            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    /**
     * Returns the index of the most frecuent letter in frecuency array.
     * @param vals
     * @return
     */
    public int maxIndex (int[] vals) {
        
        int maxDex = 0;
        
        for (int k = 0; k < vals.length; k++) {
            
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    /**
     * Location of the encrypted letter "e" = key returned
     * @param input
     * @return
     */
    public int getKey(String input) {

        int counts[] = countLetters(input);
        int maxIndex = maxIndex(counts);
        int keyIndex = maxIndex - 4;

        if (maxIndex < 4) {
            keyIndex = 26 - (4 - maxIndex);
        }
        int key = 26 - keyIndex;
        return key;
    }

    /**
     * Figure out which key was used to encrypt this message and decrypt the message.
     * @param input
     * @return
     */
    public String breakCaesarCipher (String input) {

        // figure out which key was used to encrypt this message
        int key = getKey(input);
        
        // create a CaesarCipher object with that key and decrypt the message.
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.encrypt(input);

        return decrypted;
    }

    /**
     * Test Case
     */
    public void simpleTest () {
        
        // Read in a file as a String
        FileResource fr = new FileResource();
        
        // Create a CaesarCipher object with key 18
        CaesarCipher cc = new CaesarCipher(18);
        
        // Encrypt the String read in using the CaesarCipher object
        String input = fr.asString();
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted message: " + encrypted);
        
        // Decrypt the encrypted String using the decrypt method
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
        
        // Decrypt it automatically by determining the key
        String decryptedAuto = breakCaesarCipher(encrypted);
        System.out.println("Automatic Decrypted message: " + decryptedAuto);

    }
    
    public static void main (String[] args) {
        
        TestCaesarCipher test = new TestCaesarCipher();
        test.simpleTest();
        
    }
}