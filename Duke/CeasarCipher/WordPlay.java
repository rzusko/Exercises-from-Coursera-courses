
/**
 * Trieda {@code WordPlay} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.io.*;
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        boolean trueFalse = false;
        String vowels = "aeiou";
        
        if (vowels.indexOf(Character.toLowerCase(ch)) != -1) {
            trueFalse = true;
        }
        
        return trueFalse;
    }
    
    public void testIsVowel() {
        System.out.println("a - " + isVowel('a'));
        System.out.println("A - " + isVowel('A'));
        System.out.println("b - " + isVowel('b'));
        System.out.println("B - " + isVowel('B'));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder changed = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(changed.charAt(i))) {
                changed.setCharAt(i, ch);
            }
        }
        
        return changed.toString();
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Abraca Dabra", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder changed = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(changed.charAt(i)) == Character.toLowerCase(ch)) {
                if ((i % 2) == 0) {
                    changed.setCharAt(i, '*');
                }
                else {
                    changed.setCharAt(i, '+');
                }
            }
        }
        
        return changed.toString();
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
