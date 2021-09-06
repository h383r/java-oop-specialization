import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> wordsInFiles;

    public WordsInFiles() {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f) {

        String filename = f.getName();
        FileResource fr = new FileResource(f);

        for (String s: fr.words()) {

            if (!wordsInFiles.keySet().contains(s)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(filename);
                wordsInFiles.put(s, list);
            }
            else {
                if (!wordsInFiles.get(s).contains(filename)) {
                    wordsInFiles.get(s).add(filename);
                }
            }
        }
    }

    public void buildWordFileMap() {

        wordsInFiles.clear();

        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        
        int maxNumber = 0;

        for (String s: wordsInFiles.keySet()) {

            if (wordsInFiles.get(s).size() > maxNumber) {
                maxNumber = wordsInFiles.get(s).size();
            }
        }
        return maxNumber;
    }

    public ArrayList<String> wordsInNumFiles(int number) {

        ArrayList<String> list = new ArrayList<String>();

        for (String s: wordsInFiles.keySet()) {
            
            int size = wordsInFiles.get(s).size();

            if (size == number) {
                list.add(s);
            }
        }
        return list;
    }

    public void printFilesIn(String word) {

        ArrayList<String> file = wordsInFiles.get(word);

        for (int i = 0; i < file.size(); i++) {
            System.out.println(file.get(i));
        }

    }

    public void tester() {
        
        buildWordFileMap();
        int maxNumber = maxNumber();
        
        ArrayList<String> wordsInNumFiles = wordsInNumFiles(maxNumber);
        System.out.println("Count of words that appear in " + maxNumber + " files: " + wordsInNumFiles.size());

        wordsInNumFiles = wordsInNumFiles(4);
        System.out.println("Words in 4 files: " + wordsInNumFiles.size());
        
        wordsInNumFiles = wordsInNumFiles(5);
        System.out.println("Words in 5 files: " + wordsInNumFiles.size());

        System.out.println("sad");
        printFilesIn("sad");

        System.out.println("red");
        printFilesIn("red");

        System.out.println("laid");
        printFilesIn("laid");
        
        System.out.println("tree");
        printFilesIn("tree");

    }
        
    public static void main (String[] args) {
        WordsInFiles test = new WordsInFiles();
        test.tester();
    }
}

    