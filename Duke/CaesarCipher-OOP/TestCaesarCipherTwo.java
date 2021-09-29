
/**
 * Trieda {@code TestCaesarCipherTwo} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start) {
        String halfOfMessage = "";
        
        for (int i = start; i < message.length(); i += 2) {
            char ch = message.charAt(i);
            halfOfMessage = halfOfMessage + ch;
        }
        
        return halfOfMessage;
    }
    
    public int[] countLetters(String message) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alpha.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        
        return maxDex;
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int key = maxDex - 4;
        if (maxDex < 4) {
            key = 26 - (4 - maxDex);
        }
                
        return key;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String original = fr.asString();
        /*CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        
        String encrypted = cc2.encrypt(original);
        System.out.println(encrypted);
        
        String decrypted = cc2.decrypt(encrypted);
        System.out.println(decrypted);        */
        
        String decrypted = breakCaesarCipher(original);
        System.out.println(decrypted);
    }
    
    public String breakCaesarCipher(String input) {
        String message1 = halfOfString(input, 0);
        String message2 = halfOfString(input, 1);
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(key1, key2);
        String output = cc2.decrypt(input);
        
        return output;
    }
}
