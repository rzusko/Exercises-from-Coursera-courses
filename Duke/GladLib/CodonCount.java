
/**
 * Trieda {@code CodonCount} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import java.util.*;
import edu.duke.*;
public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        codonMap.clear();
        
        for (int i = start; i < dna.length()-2; i += 3) {
            String codon = dna.substring(i, i+3);
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
            else {
                codonMap.put(codon, 1);
            }
        }
    }
    
    private String getMostCommonCodon() {
        String mostCommonCodon = "";
        int count = 0;
        
        for (String key : codonMap.keySet()) {
            int value = codonMap.get(key);
            if (value > count) {
                mostCommonCodon = key;
                count = value;
            }
        }
        
        return mostCommonCodon;
    }
    
    private void printCodonCount(int start, int end) {
        for (String key : codonMap.keySet()) {
            int value = codonMap.get(key);
            
            if ((value >= start) && (value <= end)) {
                System.out.println(key + "\t" + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        
        for (int i = 0; i <= 2; i++) {
            buildCodonMap(i, dna);
            System.out.print("Reading frame starting with " + i);
            System.out.println(" results in " + codonMap.size() + " unique codons");
            
            String mostCommon = getMostCommonCodon();
            System.out.print("and most common codon is " + mostCommon); 
            System.out.println(" with count " + codonMap.get(mostCommon));
            int start = 1;
            int end = 5;
            System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
            printCodonCount(start, end);
            System.out.println("\n\n");
        }
    }
}
