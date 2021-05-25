public class WorldPlay {
    
    /**
     * Returns true if ch is a vowel
     */
    public boolean isVowel(char ch) {

        ch = Character.toLowerCase(ch);

        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }

    /**
     * Return a String that is the string  with all the vowels replaced by ch
     */
    public String replaceVowels(String phrase, char ch) {

        StringBuilder sb = new StringBuilder(phrase);

        for (int i = 0; i < sb.length(); i++ ) {

            char currentCharacter = sb.charAt(i);
            //int currentIndex = phrase.indexOf(currentCharacter, i);

            if(isVowel(currentCharacter)) {
                //sb.setCharAt(currentIndex, ch);
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    /**
     * Return a String that is the string phrase but with the char replaced by:
     * "*" if it is in an odd number location
     * "+" if it is in an even number location
     */
    public String emphatize(String phrase, char ch) {
        
        ch = Character.toLowerCase(ch);
        StringBuilder sb = new StringBuilder(phrase);

        for (int i = 0; i < sb.length(); i++) {

            char currentCharacter = sb.charAt(i);
            int currentIndex = phrase.indexOf(currentCharacter, i);
            
            if (currentCharacter == ch && ((currentIndex+1) % 2 == 0)) {
                sb.setCharAt(currentIndex, '+');
            }

            if (currentCharacter == ch && ((currentIndex+1) % 2 != 0)) {
                sb.setCharAt(currentIndex, '*');
            }

        }
        return sb.toString();
    }

    /**
     *  Return true if ch is either ‘a’ or ‘e’, and otherwise return false.
     */
    public boolean isAorE(char ch) {
        /*
        if (ch == 'a' || ch == 'e') {
            return true;
        }
        return false;
        */
        if (ch == 'a') {
            return true;
        }
        if (ch == 'e') {
            return true;
        }
       return false;
    }

    public static void main(String[] args) {
        WorldPlay WorldPlay = new WorldPlay();

        //Test Cases
        //System.out.println(WorldPlay.isVowel('a'));
        System.out.println(WorldPlay.replaceVowels("qwerty", 'X'));
        //System.out.println(WorldPlay.emphatize("xxxxxxxxxxxxxxxx", 'x'));
        //System.out.println(WorldPlay.isAorE('e'));
        //System.out.println(WorldPlay.isAorE('a'));
        //System.out.println(WorldPlay.isAorE('i'));

    }
}
