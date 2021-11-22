import edu.duke.*;

public class Tester {

    public void test_CaesarCipher(String filename) {
        
        FileResource file = new FileResource(filename);
        String message = file.asString();

        int key = 5;
        CaesarCipher cipher = new CaesarCipher(key);
        String messageEncrypted = cipher.encrypt(message);
        String messageDecrypted = cipher.decrypt(messageEncrypted);

        System.out.println("Original message:");
        System.out.println(message);
        System.out.println("Encrypted message:");
        System.out.println(messageEncrypted);
        System.out.println("Decrypted message:");
        System.out.println(messageDecrypted);

        char character = 'x';
        key = 9;
        
        CaesarCipher cipher2 = new CaesarCipher(key);
        char characterEncrypted = cipher2.encryptLetter(character);
        char characterDecrypted = cipher2.decryptLetter(characterEncrypted);
        
        System.out.println("Encrypted Letter: " + characterEncrypted);
        System.out.println("Decrypted Letter: " + characterDecrypted);
        System.out.println("Key: ");
        System.out.println(key);

    }   
    
    public void test_CaesarCracker(String filename, char commonChar) {

        FileResource file = new FileResource(filename);
        String message = file.asString();

        CaesarCracker cracker = new CaesarCracker(commonChar);

        int key = cracker.getKey(message);

        String messageCracked = cracker.decrypt(message);
        System.out.println("Original message:");
        System.out.println(message);
        System.out.println("Cracked message:");
        System.out.println(messageCracked);
        System.out.println("Key:");
        System.out.println(key);

    }

    public void test_VigenereCipher(String filename, int[] keys) {

        FileResource file = new FileResource(filename);
        String message = file.asString();

        //int[] keys = {17, 14, 12, 4};

        VigenereCipher cipher = new VigenereCipher(keys);
        String messageEncrypted = cipher.encrypt(message);
        String messageDecrypted = cipher.decrypt(messageEncrypted);

        System.out.println("Original message:");
        System.out.println(message);
        System.out.println("Encrypted message:");
        System.out.println(messageEncrypted);
        System.out.println("Decrypted message:");
        System.out.println(messageDecrypted);

    }

    public void test_VigenereBreaker(String filename) {





    }

    public void test_sliceString(String message, int whichSlice, int totalSlices) {
        VigenereBreaker breaker = new VigenereBreaker();
        String output = breaker.sliceString(message, whichSlice, totalSlices);
        System.out.println(output);
    }

    public void test_tryKeyLength(String filename, int klength, char mostCommon) {
        
        FileResource file = new FileResource(filename);
        String encrypted = file.asString();
        
        VigenereBreaker breaker = new VigenereBreaker();
        int[] key = breaker.tryKeyLength(encrypted, klength, mostCommon);
        
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }
    }

    public void test_breakVigenere() {

        VigenereBreaker breaker = new VigenereBreaker();
        breaker.breakVigenere();

    }



    public static void main (String[] args) {

        Tester test = new Tester();
        
        /*
        System.out.println("-------------------------- Testing Caesar Cypher ");
        test.test_CaesarCipher("data/VigenereTestData/titus-small.txt");
        
        System.out.println("-------------------------- Testing Caesar Cracker ");
        System.out.println("English message, common character 'e' ");
        test.test_CaesarCracker("data/VigenereTestData/titus-small_key5.txt", 'e');
        System.out.println("Portuguese message, common character 'a' ");
        test.test_CaesarCracker("data/VigenereTestData/oslusiadas_key17.txt", 'a');
        
    
        System.out.println("-------------------------- Testing Vigenere Cipher ");
        int[] vigenereKeys = {17, 14, 12, 4}; //rome
        test.test_VigenereCipher("data/VigenereTestData/titus-small.txt", vigenereKeys);
        
        System.out.println("-------------------------- Testing sliceString method ");
        test.test_sliceString("abcdefghijklm", 0, 3); // should return "adgjm"
        test.test_sliceString("abcdefghijklm", 1, 3); // should return "behk"
        test.test_sliceString("abcdefghijklm", 2, 3); // should return "cfil"
        test.test_sliceString("abcdefghijklm", 0, 4); // should return "aeim"
        test.test_sliceString("abcdefghijklm", 1, 4); // should return "bfj"
        test.test_sliceString("abcdefghijklm", 2, 4); // should return "cgk"
        test.test_sliceString("abcdefghijklm", 3, 4); // should return "dhl"
        test.test_sliceString("abcdefghijklm", 0, 5); // should return "afk"
        
        System.out.println("-------------------------- Testing tryKeyLength method ");
        test.test_tryKeyLength("data/VigenereTestData/athens_keyflute.txt", 5, 'e'); 
        */
        
        System.out.println("-------------------------- Testing breakVigenere method ");
        test.test_breakVigenere();

    }
}