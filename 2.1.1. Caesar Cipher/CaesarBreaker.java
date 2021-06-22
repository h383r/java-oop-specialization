import edu.duke.*;

public class CaesarBreaker {

    /**
     * Returns the index of the most frecuent letter in String message.
     * @param message
     * @return
     */
    public int[] countLetters(String message) {
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for(int i = 0; i < message.length(); i++) {

            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);

            if(dex != -1) {
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
    public int maxIndex(int[] vals) {

        int maxDex = 0;

        for(int k = 0; k < vals.length; k++) {

            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * Returns a String decrypted using the Caesar Cipher algorithm
     * @param encrypted
     * @return
     */
    public String decrypt(String encrypted) {

        CaesarCipher cc = new CaesarCipher();

        // Count 26 frequencies of "a"-"z"
        int[] freqs = countLetters(encrypted);

        // Find largest value, assume it's "e"
        int maxDex = maxIndex(freqs);

        // Find distance from "e" wich has index 4
        int dkey = maxDex - 4;

        // Use 26-distance to decrypt using encrypt method
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void decryptTest() {

        // "Teeeeeeest Meeeeesage" key = 23
        String encrypted = "Qbbbbbbbpq Jbbbbbpxdb";      
        System.out.println(decrypt(encrypted));

    }

    /**
     * Return a new String that is every other character from message 
     * starting with the start position
     * @param message
     * @param start
     * @return
     */
    public String halfOfString(String message, int start) {
        /*
        StringBuilder halfString = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
        */
        String answer = "";
        for (int k = start; k < message.length(); k +=2) {
            answer = answer + message.charAt(k);
        }
        return answer;
    }

    public void halfOfStringTest() {
        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    /**
     * Call countLetters to get an array of the letter frequencies in String s
     * Use maxIndex to calculate the index of the largest letter frequency,
     * largest letter frequency = location of the encrypted letter "e" = key returned
     */
    public int getKey(String s) {

        int counts[] = countLetters(s);
        int maxIndex = maxIndex(counts);
        int keyIndex = maxIndex - 4;

        if (maxIndex < 4) {
            keyIndex = 26 - (4 - maxIndex);
        }
        int key = 26 - keyIndex;
        return key;
    }
    
    /**
     * Attempts to determine the two keys used to encrypt the message,
     * prints the two keys, and then returns the decrypted 
     * String with those two keys
     */
    public String decryptTwoKeys(String encrypted) {

        // Calculate a String of every other character starting with the first character
        String half0 = halfOfString(encrypted, 0);
        
        // Calculate a String of every other character starting with the second character
        String half1 = halfOfString(encrypted, 1);

        // Calculate the key used to encrypt each half String
        int key0 = getKey(half0);
        System.out.println("Keys used to encrypt the message");
        System.out.println("key 1: " + (26 - key0));

        int key1 = getKey(half1);
        System.out.println("key 2: " + (26 - key1));
        
        //Calculate and return the decrypted String
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, key0, key1);
        return decrypted;
    }

    public void decryptTwoKeysTest() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);

        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + decrypted);

        decryptTwoKeys(encrypted);
    }

    public static void main(String[] args) {
    
        CaesarBreaker caesarBreaker = new CaesarBreaker();
        //caesarBreaker.decryptTest();
        //caesarBreaker.halfOfStringTest();
        //System.out.println(caesarBreaker.getKey("test"));
        caesarBreaker.decryptTwoKeysTest();

        //CaesarCipher cc = new CaesarCipher();
        //System.out.println(cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));

        //System.out.println(caesarBreaker.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));

    }
}