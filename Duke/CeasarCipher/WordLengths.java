
/**
 * Trieda {@code WordLengths} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int k = word.length();
            
            if (k >= counts.length - 1) {
                k = counts.length - 1;
            }
            
            if (Character.isLetter(word.charAt(0)) && Character.isLetter(word.charAt(word.length()-1))) {
                    counts[k] += 1;
            } 
            else {
                if (Character.isLetter(word.charAt(0)) || Character.isLetter(word.charAt(word.length()-1))) {
                        counts[k - 1] += 1;
                }
                else {
                    counts[k - 2] += 1;
                }    
            }
        }
    }
    
    public int indexOfMax(int[] values) {
        int index = 0; 
        int max = 0;
        
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                index = i;
                max = values[i];
            }
        }
        
        return index;
    }
    
    public void testCountWordLenghths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 1; i < counts.length; i++) {
            System.out.println("Number of words of length " + i + " is " + counts[i]);
        }
        System.out.println("The biggest number of words is of length " + indexOfMax(counts));
    }
    
    
    

}
