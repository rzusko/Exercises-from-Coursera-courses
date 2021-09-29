
/**
 * Trieda {@code Part2} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part2 {
    public double cgRatio(String dna) {
        int cg = 0;
        int i = 0;
        
        while (i < dna.length()) {
            String s = dna.substring(i, i+1);
            System.out.println(s);
            
            if (s.equals("C") || s.equals("G")) {
                cg += 1;
            }
            
            i += 1;
        }
        
        return (float) cg/dna.length();
    }
    
    public int countCTG(String dna) {
        int start = 0;
        int count = 0;
        
        while (true) {
            int i = dna.indexOf("CTG", start);
            
            if (i == -1) {
                break;
            }
            
            count += 1;
            start = i + 1;
        }
                     
        return count;
    }
    
    
    public void testCgRatio() {
        String sample = "ATGCCATAG";
        System.out.println("Testovaný reťazec je: " + sample);
        System.out.println("Pomer znakov \"C\" a \"G\" v testovanom reťazci je: " + cgRatio(sample));
    }
    
    public void testCountCTG() {
        // 1-krat
        String sample1 = "ATGCTGCCACGCGA";
        System.out.println("Testovaný reťazec je: " + sample1);
        System.out.print("Reťazec \"CTG\" sa v testovanom reťazci vyskytuje: ");
        System.out.print(countCTG(sample1));
        System.out.println("-krát.");
        
        // 2-krat
        String sample2 = "AACTGCGCTGACAGTACTA";
        System.out.println("Testovaný reťazec je: " + sample2);
        System.out.print("Reťazec \"CTG\" sa v testovanom reťazci vyskytuje: ");
        System.out.print(countCTG(sample2));
        System.out.println("-krát.");
        
        // 0-krat
        String sample3 = "ATGTGCACGTGA";
        System.out.println("Testovaný reťazec je: " + sample3);
        System.out.print("Reťazec \"CTG\" sa v testovanom reťazci vyskytuje: ");
        System.out.print(countCTG(sample3));
        System.out.println("-krát.");
    }

}
