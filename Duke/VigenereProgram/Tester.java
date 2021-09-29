
/**
 * Trieda {@code Tester} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import edu.duke.*;
import java.util.*;
public class Tester {
    public void testCipher() {
        CaesarCipher cc = new CaesarCipher(5);
        FileResource message = new FileResource();
        String strMess = message.asString();
        
        System.out.println(strMess);
        String encrMess = cc.encrypt(strMess);
        System.out.println(encrMess);
    }
    
    public void testCracker() {
        CaesarCracker cc = new CaesarCracker('a');
        FileResource message = new FileResource();
        String strMess = message.asString();
        
        System.out.println(strMess);
        String decrMess = cc.decrypt(strMess);
        System.out.println(decrMess);
    }
    
    public void testVigenereCipher() {
        int[] key = {21, 4, 17, 3, 8};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource message = new FileResource();
        String strMess = message.asString();
        
        //System.out.println(strMess);
        String encrMess = vc.encrypt(strMess);
        System.out.println(encrMess);
        String decrMess = vc.decrypt(encrMess);
        //System.out.println(decrMess);
    }
}
