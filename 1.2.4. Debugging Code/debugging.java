public class debugging {
    
    //Debugging Excercise

    /**
     * finds each occurrence of “abc_” in a String input
     * where _ is a single character
     * prints “bc_” for each such occurrence
     * @param input
     */
    public void findAbc (String input) {
        
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);
            index = input.indexOf("abc", index + 3);
        }
    }

    public void test() {
        //findAbc("abcd"); //should print bcd
        findAbc("abcdabc");
    }


    public static void main (String[] args) {
        debugging db = new debugging();
        db.test();
    }
}