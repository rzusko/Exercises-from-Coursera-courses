
/**
 * Trieda {@code CaesarCipher} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = (alphabet.substring(key) + alphabet.substring(0, key));
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(currChar));
            
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
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String output = cc.encrypt(input);
        
        return output;
    }
}
