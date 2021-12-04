import edu.duke.*;

public class CaesarCipher {
    
    /**
     * Returns a String encrypted using the Caesar Cipher algorithm
     * @param input
     * @param key
     * @return
     */
    public String encrypt(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = encrypted.charAt(i);
            
            if (Character.isLowerCase(currentCharacter)) {
                int currentIndex = alphabet.toLowerCase().indexOf(currentCharacter);
                
                if (currentIndex != -1) {
                    char newCharacter = shiftedAlphabet.toLowerCase().charAt(currentIndex);
                    encrypted.setCharAt(i, newCharacter);
                }
            } 
            else  {
                int currentIndex = alphabet.indexOf(currentCharacter);
                
                if (currentIndex != -1) {
                    char newCharacter = shiftedAlphabet.charAt(currentIndex);
                    encrypted.setCharAt(i, newCharacter);
                }
            }
        }
        return encrypted.toString();
    }

    /**
     * Read and encrypt the complete file using the Caesar Cipher.
     */
    public void encryptFile() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 23);
        System.out.println("key is " + 23 + "\n" + encrypted);  
    }

    /**
     * Returns a String encrypted using the Caesar Cipher algorithm
     * @param input
     * @param key1
     * @param key2
     * @return
     */
    public String encryptTwoKeys(String input, int key1, int key2) {

        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {

            String currentCharacter = Character.toString(encrypted.charAt(i));

            if (i % 2 == 0) {
                encrypted.setCharAt(i, encrypt(currentCharacter, key1).charAt(0));
            } else {
                encrypted.setCharAt(i, encrypt(currentCharacter, key2).charAt(0));
            }
        }
        return encrypted.toString();
    }

    public static void main (String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();

        //Test cases
        System.out.println(caesarCipher.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        //caesarCipher.encryptFile();
        //System.out.println(caesarCipher.encryptTwoKeys("First Legion", 23, 17));

    }
}