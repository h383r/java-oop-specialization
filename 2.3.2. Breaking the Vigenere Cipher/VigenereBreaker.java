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
    public void breakVigenere (String filename) {
       
        /*
        FileResource file = new FileResource(filename);
        String message = file.asString();
        
        int[] key = tryKeyLength(message, 38, 'e'); //5
        VigenereCipher cipher = new VigenereCipher(key);
        String messageDecrypted = cipher.decrypt(message);
        
        System.out.println("Keys used: ");
        for (int i = 0; i < key.length; i++) {
            System.out.print(key[i] + " ");
        }
        
        System.out.println("Decrypted message:");
        System.out.println(messageDecrypted);
        */
        
        /*
        //Test this method on the text file athens_keyflute.txt.
        //The first line should be “SCENE II. Athens. QUINCE'S house”
        //The key is “flute”, or {5, 11, 20, 19, 4}.
        //This file contains 376 valid words out of 386 (total word count ignores whitespace). 
        FileResource dictionaryFile = new FileResource("data/dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictionaryFile);
        FileResource messageFile = new FileResource("data/VigenereTestData/athens_keyflute.txt");
        String messageEncrypted = messageFile.asString();
        String messageDecrypted = breakForLanguage(messageEncrypted, dictionary);
        System.out.println(messageDecrypted);
        */

        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        
        String[] languages = new String[8];
        languages[0] = "Danish";
        languages[1] = "Dutch";
        languages[2] = "English";
        languages[3] = "French";
        languages[4] = "German";
        languages[5] = "Italian";
        languages[6] = "Portuguese";
        languages[7] = "Spanish";
        
        for (int i = 0; i < languages.length; i++) {
            
            String language = languages[i];
            FileResource file = new FileResource("data/dictionaries/" + language);
            
            System.out.println("Reading: " + language);
            
            HashSet<String> dictionary = readDictionary(file);
            dictionaries.put(language, dictionary);
        }
        
        FileResource messageFile = new FileResource(filename);
        String messageEncrypted = messageFile.asString();
        
        breakForAllLangs(messageEncrypted, dictionaries);

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

    /**
     * Returns the decryption which gives the largest count of real words.
     * @param encrypted
     * @param dictionary
     * @return
     */
    public String breakForLanguage (String encrypted, HashSet<String> dictionary, String language ) {

        String messageDecrypted = "";
        int totalRealWords = 0;
        int keyLength = 0;

        // Try all key lengths from 1 to 100 to obtain the best decryption for each key length in that range.
        for (int i = 1; i < 100; i++) {
            
            
            //Note that there is nothing special about 100; we will just give you messages with 
            //key lengths in the range 1–100. If you did not have this information, 
            //you could iterate all the way to encrypted.length()
            
            char commonChar = mostCommonCharIn(dictionary);
            
            int[] key = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher cipher = new VigenereCipher(key);
            
            // For each key length, decrypt the message
            String currentDecrypted = cipher.decrypt(encrypted);
            
            // Count how many of the “words” in it are real words in English.
            int countWords = countWords(currentDecrypted, dictionary);
            
            if (countWords > totalRealWords) {
                totalRealWords = countWords;
                keyLength = i;
                messageDecrypted = currentDecrypted;
            }
        }

        /*
        char commonChar = mostCommonCharIn(dictionary);
        int[] key = tryKeyLength(encrypted, 38, commonChar);
        VigenereCipher cipher = new VigenereCipher(key);
        String currentDecrypted = cipher.decrypt(encrypted);
        totalRealWords = countWords(currentDecrypted, dictionary);
        */

        System.out.println("");
        System.out.println("Analyzing Language: " + language);
        System.out.println("Real words: " + totalRealWords);
        System.out.println("Key Length: " + keyLength);
        System.out.println("");
        
        return messageDecrypted;
    }
    
    /** 
     * Returns the most commonly occurring character in the words of dictionary.
     * @param dictionary
     * @return
     */
    public char mostCommonCharIn (HashSet<String> dictionary) {

        HashMap<Character, Integer> charactersCounter = new HashMap<Character, Integer>();

        for (String word : dictionary) {

            char[] characters = word.toCharArray();

            for (int i = 0; i < characters.length; i++) {

                char currentCharacter = characters[i];
                if (!charactersCounter.containsKey(currentCharacter)) {
                    charactersCounter.put(currentCharacter, 1);
                } else {
                    charactersCounter.replace(currentCharacter, charactersCounter.get(currentCharacter) + 1);
                }
            }
        }

        int maxCount = 0;
        char mostCommonChar = ' ';
        for (Character character : charactersCounter.keySet()) {
            
            int currentCount = charactersCounter.get(character);

            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostCommonChar = character;
            }
        }
        return mostCommonChar;
    }

    /**
     * Breaks the encryption for each language, and see which gives the best results.
     * @param encrypted
     * @param languages
     */
    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {

        String languageUsed = "";
        String decryptedMessage = "";
        int countWordsMax = 0;

        for (String language : languages.keySet()) {

            HashSet<String> dictionary = languages.get(language);
            String message = breakForLanguage(encrypted, dictionary, language);

            int countWords = countWords(message, dictionary);

            if (countWords > countWordsMax) {
                countWordsMax = countWords;
                decryptedMessage = message;
                languageUsed = language;
            }
        }
        
        System.out.println("Language used to decrypt message: " + languageUsed);
        System.out.println("");
        
        
        System.out.println("Message:");
        System.out.println("");
        //System.out.println(decryptedMessage);
        String shortMessage = decryptedMessage.substring(0, 100);
        System.out.println(shortMessage);
    }



}
