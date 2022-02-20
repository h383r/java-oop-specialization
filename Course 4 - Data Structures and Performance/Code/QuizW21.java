public class QuizW21 {

    public static void main (String[] args) {

        /*
        String s1 = new String("String 1");
        String s2 = new String("String 1");
        if (s1 == s2) {
            System.out.println("Equal");
        }
        else {
            System.out.println("Not equal");
        }

        String string1 = new String("Coursera");
        char letter = string1.charAt(4);
        System.out.println(letter);

        String s = "Hello";
        s.concat(" World!");
        System.out.println(s);

        String text = "Practice, practice, practice!";
        System.out.println(text.indexOf("prac"));

        String text1 = "Hurray!!#! It's Friday! finally...";
        String[] words = text1.split("!+"); // ["Hurray", "#", " It's Friday", " finally..."]
        for(String word : words) {
            System.out.println(word);
        }

        
        
        String s1 = new String("String 1");
        String s2 = "String 1";
        if (s1 == s2) {
            System.out.println("Equal");
        }
        else {
            System.out.println("Not equal");
        }
        
        
        String text = "My ";
        String s2 = "String";
        text = text + s2;
        System.out.println(text);
        */
        

        String text1 = "%one%%two%%%three%%%%";
        String[] words = text1.split("one|two|three");
        for(String word : words) {
            System.out.print(word + ", ");
        }

        
    }
}