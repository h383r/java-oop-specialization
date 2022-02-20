public class Week2 {

    // Week 2 - Core Working with Strings in Java
    
    public static boolean hasLetter (String word, char letter) {

        // toCharArray returns the chars in a String, as a char[]
        char [] characters = word.toCharArray();
        
        // for loop:
        //    for (int i = 0; i < word.length(); i++) {
        
        // for-each loop:
        for(char i : characters) {
            
            if (word.charAt(i) == letter) {

                return true;
            }
        }
        return false;
    }

    public static String replace (String word, char gone, char here) {

        char[] cArray = word.toCharArray();
        char[] cArrayMod = new char[cArray.length];

        for ( int i = 0; i < cArray.length; i++ ) {

            if (cArray[i] == gone) {
                cArrayMod[i] = here;
            } else {
                cArrayMod[i] = cArray[i];
                i++;
            }
        }
        return new String (cArrayMod);
    }



    public static void splitStringTest () {

        String text = "alks akjsh kqwjbn vopias, qwbe";
        String[] words = text.split(" ");

        for(int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

    }












    
}
