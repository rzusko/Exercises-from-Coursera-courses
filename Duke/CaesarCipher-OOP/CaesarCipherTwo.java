
/**
 * Trieda {@code CaesarCipherTwo} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(currChar));
            char newChar;
            
            if (index != - 1) {
                if ((i % 2) == 0) {
                    newChar = shiftedAlphabet1.charAt(index);
                }
                else {
                    newChar = shiftedAlphabet2.charAt(index);
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
    
    public String decrypt(String input) {
        CaesarCipherTwo cc2 =  new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String output = cc2.encrypt(input);
        
        return output;
    }
}
