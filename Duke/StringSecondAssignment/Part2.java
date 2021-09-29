
/**
 * Trieda {@code Part2} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int start = 0;
        int count = 0;
        int occurence = 0;
        
        while (true) {
            occurence = stringb.indexOf(stringa, start);
            if (occurence == -1) {
                break;
            }
            else {
                count = count + 1;
                start = occurence + stringa.length();
            }
        }
        
        return count;
    }
    
    public void testHowMany() {
        //Test 1 - 3-krát
        String sample1 = "ATGAACGAATTGAATC";
        System.out.println("Testovaný reťazec je: " + sample1);
        System.out.println("Reťazec \"GAA\" sa vyskytuje: " + howMany("GAA", sample1) + "-krát");
        
        //Test 2 - 2-krát
        String sample2 = "ATAAAA";
        System.out.println("Testovaný reťazec je: " + sample2);
        System.out.println("Reťazec \"AA\" sa vyskytuje: " + howMany("AA", sample2) + "-krát");
        
        //Test 3 - 0-krát
        String sample3 = "jablkohruškapomarančkivi";
        System.out.println("Testovaný reťazec je: " + sample3);
        System.out.println("Reťazec \"fg\" sa vyskytuje: " + howMany("fg", sample3) + "-krát");
    }

}
