import edu.duke.*;

public class TestCaesarCipherTwo {

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
     * Return a new String that is every other character from message 
     * starting with the start position
     * @param message
     * @param start
     * @return
     */
    public String halfOfString(String message, int start) {

        String answer = "";
        for (int k = start; k < message.length(); k +=2) {
            answer = answer + message.charAt(k);
        }
        return answer;
    }

    /**
     * Figure out which keys was used to encrypt this message and decrypt the message.
     * @param input
     * @return
     */
    public String breakCaesarCipher (String input) {

        // figure out which keys was used to encrypt this message
        String halfKey1 = halfOfString(input, 0);
        String halfKey2 = halfOfString(input, 1);

        int key1 = getKey(halfKey1);
        int key2 = getKey(halfKey2);

        System.out.println("Key 1: " + (26 - key1));
        System.out.println("Key 2: " + (26 - key2));

        // create a CaesarCipher object with that key and decrypt the message.
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String decrypted = cct.encrypt(input);

        return decrypted;
    }

    /**
     * Test Case
     */
    public void simpleTest () {

        // Read in a file as a String
        FileResource fr = new FileResource();

        // Create a CaesarCipherTwo object with keys 17 and 3 
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        
        // Encrypt the String using the CaesarCipherTwo object 
        String input = fr.asString();
        String encrypted = cct.encrypt(input);
        System. out.println("Encrypted message: " + encrypted);    
        
        // Decrypt the encrypted String using the decrypt method.
        String decrypted = cct.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);

        // Decrypt it automatically by determining the keys
        String decryptedAuto = breakCaesarCipher(input);
        System.out.println("Automatic Decrypted message: " + decryptedAuto);

    }

    public void quiz () {

        // Question 2
        CaesarCipherTwo cct = new CaesarCipherTwo(21, 8);
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println((cct.encrypt(input)));

        // Question 6
        CaesarCipherTwo cct6 = new CaesarCipherTwo(14, 24);
        String input6 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println((cct6.decrypt(input6)));

        // Question 7
        System.out.println(breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));

        // Question 8 - 9
        FileResource fr = new FileResource();
        String input8 = fr.asString();
        System.out.println(breakCaesarCipher(input8));  

    }

    public static void main (String[] args) {

        TestCaesarCipherTwo test = new TestCaesarCipherTwo();
        //test.simpleTest();
        test.quiz();
        
    }

}
