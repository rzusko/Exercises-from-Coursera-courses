
/**
 * Trieda {@code CeasarCipher} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if (index != -1) {
                char newChar = shiftedAlphabet.charAt(index);
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                }
                else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }           
        }
        
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("Key is " + key + "\n" + encrypted);
        
        //System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(currChar));
            char newChar;
            
            if (index != -1) {
                if ((i % 2) == 0) {
                    newChar = shiftedAlphabetKey1.charAt(index);    
                }
                else {
                    newChar = shiftedAlphabetKey2.charAt(index);
                }
                
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                }
                else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        
        //int key1 = 23;
        //int key2 = 17;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encryptTwoKeys(message, key1, key2);
        //System.out.println("Keys are " + key1 + " and " + key2 + "\n" + encrypted);
    }

}
