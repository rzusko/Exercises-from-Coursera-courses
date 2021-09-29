
/**
 * Trieda {@code Part3} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        //boolean result = false;
        int first = stringb.indexOf(stringa);
        int second = stringb.indexOf(stringa, first+1);
    
       
        return (first != -1 && second != -1);
    
        //return result;
    }
    
    public String lastPart(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        
        if (first == -1) {
            return stringb;
        }
        
        return stringb.substring(first + stringa.length());
    }
    
    public void testing() {
        String a1 = "by";
        String b1 = "Story by Abby Long";
        System.out.print("We are looking for string: \"");
        System.out.print(a1);
        System.out.print("\" in string: \"");
        System.out.print(b1);
        System.out.println("\". Is it true or false?");
        System.out.println(twoOccurrences(a1, b1));
        
        String a2 = "a";
        String b2 = "banana";
        System.out.print("We are looking for string: \"");
        System.out.print(a2);
        System.out.print("\" in string: \"");
        System.out.print(b2);
        System.out.println("\". Is it true or false?");
        System.out.println(twoOccurrences(a2, b2));
        
        String a3 = "a";
        String b3 = "Scarified";
        System.out.print("We are looking for string: \"");
        System.out.print(a3);
        System.out.print("\" in string: \"");
        System.out.print(b3);
        System.out.println("\". Is it true or false?");
        System.out.println(twoOccurrences(a3, b3));
        
        String a4 = "an";
        String b4 = "autobus";
        System.out.print("The part of the string after \"");
        System.out.print(a4);
        System.out.print("\" in \"");
        System.out.print(b4);
        System.out.print("\" is \"");
        System.out.print(lastPart(a4, b4));
        System.out.println("\".");
        
        String a5 = "zoo";
        String b5 = "Ideme do zoologickej záhrady";
        System.out.print("The part of the string after \"");
        System.out.print(a5);
        System.out.print("\" in \"");
        System.out.print(b5);
        System.out.print("\" is \"");
        System.out.print(lastPart(a5, b5));
        System.out.println("\".");
        
    }
}
