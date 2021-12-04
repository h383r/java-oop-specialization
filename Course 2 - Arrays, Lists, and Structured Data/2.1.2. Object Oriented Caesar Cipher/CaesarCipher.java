public class CaesarCipher {
 
    // Fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    /**
     * Constructor
     * @param key
     */
    public CaesarCipher (int key) {

        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;

    }

    /**
     * Returns a String encrypted using the Caesar Cipher algorithm
     * @param input
     * @return
     */
    public String encrypt (String input) {

        StringBuilder encrypted = new StringBuilder(input);

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
     * Returns a String decrypted using the Caesar Cipher algorithm
     * @param input
     * @return
     */
    public String decrypt (String input) {

        CaesarCipher cc = new CaesarCipher(26 - mainKey); 
        return cc.encrypt(input);

    }
}