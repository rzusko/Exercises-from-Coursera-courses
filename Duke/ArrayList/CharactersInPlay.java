
/**
 * Trieda {@code CharactersInPlay} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            int value = counts.get(index);
            counts.set(index, value + 1);       
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        
        FileResource fr = new FileResource();
        
        for (String line : fr.lines()) {
            int index = line.indexOf(".");
            
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < names.size(); i++) {
            int value = counts.get(i);
            if ((value >= num1) && (value <= num2)) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }
    }    
    
    public void tester() {
        findAllCharacters();
        
        System.out.println("Main characters: \tNumber of parts:");
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) > 10) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }
        
        System.out.println("");
        System.out.println("Characters with 10-15 parts:");
        charactersWithNumParts(10,15);
    }
}
