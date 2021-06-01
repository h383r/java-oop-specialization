public class CaesarCipherTwo {
    
    // Fields
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    /**
     * Constructor
     * @param key1
     * @param key2
     */
    public CaesarCipherTwo (int key1, int key2) {
        alphabet =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    /**
     * Returns a String that is the input encrypted using the two shifted alphabets
     * @param input
     * @return
     */
    public String encrypt (String input) {

        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {

            //String currentCharacter = Character.toString(encrypted.charAt(i));
            char currentCharacter = encrypted.charAt(i);

            if (i % 2 == 0) {

                if (Character.isLowerCase(currentCharacter)) {
                    int currentIndex = alphabet.toLowerCase().indexOf(currentCharacter);
                    
                    if (currentIndex != -1) {
                        char newCharacter = shiftedAlphabet1.toLowerCase().charAt(currentIndex);
                        encrypted.setCharAt(i, newCharacter);
                    }
                } 
                else  {
                    int currentIndex = alphabet.indexOf(currentCharacter);
                    
                    if (currentIndex != -1) {
                        char newCharacter = shiftedAlphabet1.charAt(currentIndex);
                        encrypted.setCharAt(i, newCharacter);
                    }
                }

            } else {

                if (Character.isLowerCase(currentCharacter)) {
                    int currentIndex = alphabet.toLowerCase().indexOf(currentCharacter);
                    
                    if (currentIndex != -1) {
                        char newCharacter = shiftedAlphabet2.toLowerCase().charAt(currentIndex);
                        encrypted.setCharAt(i, newCharacter);
                    }
                } 
                else  {
                    int currentIndex = alphabet.indexOf(currentCharacter);
                    
                    if (currentIndex != -1) {
                        char newCharacter = shiftedAlphabet2.charAt(currentIndex);
                        encrypted.setCharAt(i, newCharacter);
                    }
                }

            }
        }
        return encrypted.toString();
    }

    /**
     * Returns a String that is the encrypted String decrypted using the key1 and key2
     * @param input
     * @return
     */
    public String decrypt (String input) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }
}
