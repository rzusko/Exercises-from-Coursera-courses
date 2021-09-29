
/**
 * Trieda {@code WordFrequencies} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }   
    }
    
    public int findIndexOfMax() {
        int indexOfMax = -1;
        int value = 0;
        
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > value) {
                indexOfMax = i;
                value = myFreqs.get(i);
            }
        }
        
        return indexOfMax;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words is: " + myWords.size());
        /*for (int i = 0; i < myWords.size(); i++) {
            System.out.print(myWords.get(i));
            System.out.print("\t");
            System.out.println(myFreqs.get(i));
        }
        */
        int indexOfMax = findIndexOfMax();
        String wordWithMax = myWords.get(indexOfMax);
        int valueOfMax = myFreqs.get(indexOfMax);
        
        System.out.println("The word that occurs most often and its count are: ");
        System.out.println(wordWithMax + "\t" + valueOfMax);
    }
    
}
