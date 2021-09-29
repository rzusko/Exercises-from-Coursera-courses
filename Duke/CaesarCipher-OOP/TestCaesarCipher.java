
/**
 * Trieda {@code TestCaesarCipher} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import edu.duke.*;


public class TestCaesarCipher {
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        
        return maxDex;
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
    
    public void simpleTests() {
        FileResource fr =  new FileResource();
        String message = fr.asString();
        /*CaesarCipher cc = new CaesarCipher(18);
        
        String encryptedMessage = cc.encrypt(message);
        System.out.println(encryptedMessage);
        
        String decryptedMessage = cc.decrypt(encryptedMessage);
        System.out.println(decryptedMessage);*/
        
        String decryptedMessage = breakCaesarCipher(message);
        System.out.println(decryptedMessage);
    }
    
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        CaesarCipher cc = new CaesarCipher(dkey);
        String output = cc.decrypt(input);
        
        return output;
    }
}
