
/**
 * Trieda {@code CeasarBreaker} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import edu.duke.*;


public class CaesarBreaker {
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters (encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public void testDecrypt () {
        FileResource fr = new FileResource("Data/test-key17.txt");
        String encryptedMessage = fr.asString();
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println(decryptedMessage);
    }
    
    public String halfOfString(String message, int start) {
        String halfOfMessage = "";
        
        for (int i = start; i < message.length(); i += 2) {
            char ch = message.charAt(i);
            halfOfMessage = halfOfMessage + ch;
        }
        
        return halfOfMessage;
    }
    
    public void testHalfOfString() {
        String testMessage = "123456789";
        System.out.println(halfOfString(testMessage, 0));
        System.out.println(halfOfString(testMessage, 1));
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
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String message1 = halfOfString(encrypted, 0);
        String message2 = halfOfString(encrypted, 1);
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        System.out.println("First key used to encrypt message is: " + key1);
        System.out.println("Second key used to encrypt message is: " + key2);
        
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        
        return decrypted;
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        //String encryptedMessage = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decryptedMessage = decryptTwoKeys(encryptedMessage);
        
        System.out.println("Decrypted message is:");
        System.out.println(decryptedMessage);
    }

}
