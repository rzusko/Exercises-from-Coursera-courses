
/**
 * Trieda {@code WordsInFiles} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
                
        for (String word : fr.words()) {
            if (wordMap.containsKey(word)) {
                ArrayList<String> list = wordMap.get(word);
                                
                if (!list.contains(fileName)) {
                    list.add(fileName);
                }
                wordMap.put(word, list);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(fileName);
                wordMap.put(word, list);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordMap.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0;
        
        for (String key : wordMap.keySet()) {
            ArrayList<String> list = wordMap.get(key);
            int size = list.size();
            if (size > max) {
                max = size;
            }
        }
        
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        
        for (String key : wordMap.keySet()) {
            ArrayList<String> list = wordMap.get(key);
            if (list.size() == number) {
                words.add(key);
            }
        }
        
        return words;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> list = wordMap.get(word);
        
        for (String s : list) {
            System.out.println(s);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        ArrayList<String> list = wordsInNumFiles(max);
        
        System.out.println("The greatest number of files a word appears in is: " + max);
        System.out.println("The words are:");
        
        for (String s : list) {
            System.out.println("\"" + s + "\" that appears in files: ");
            printFilesIn(s);
            System.out.println();
        }
    }
    
    public void testAll() {
        buildWordFileMap();
        
        for (String key : wordMap.keySet()) {
            System.out.println("\"" + key + "\" appears in:");
            printFilesIn(key);
            System.out.println();
        }
    }
    
    public void testReview() {
        buildWordFileMap();
        
        ArrayList<String> list5 = wordsInNumFiles(5);
        System.out.println("There are " + list5.size() + " words that appears in five files.\n");
        
        ArrayList<String> list4 = wordsInNumFiles(4);
        System.out.println("There are " + list4.size() + " words that appears in four files.\n");
        
        System.out.println("Word \"sad\" appears in files:");
        printFilesIn("sad");
        System.out.println("\n");
        
        System.out.println("Word \"red\" appears in files:");
        printFilesIn("red");
        System.out.println("\n");
    }
}
