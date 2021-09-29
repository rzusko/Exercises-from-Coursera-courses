
/**
 * Trieda {@code Part1} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int firstOccur = dna.indexOf(stopCodon, startIndex);
               
        while (firstOccur != -1) {
            
            if ((firstOccur - startIndex)%3 == 0) {
                return firstOccur;
            }
            else {
            firstOccur = dna.indexOf(stopCodon, firstOccur + 1);
            }
        }
        
        return dna.length();            
    }
    
    public String findGene(String dna, int where) {
        int firstATG = dna.indexOf("ATG", where);
        
        if (firstATG == -1) {
            return "";
        }
        
        int firstTAA = findStopCodon(dna, firstATG, "TAA");
        int firstTAG = findStopCodon(dna, firstATG, "TAG");
        int firstTGA = findStopCodon(dna, firstATG, "TGA");
        int error = dna.length();
        
        if ((firstTAA != error) || (firstTAG != error) || (firstTGA != error)) {
            if ((firstTAA < firstTAG) && (firstTAA < firstTGA)) {
                return dna.substring(firstATG, firstTAA + 3);
            }
            else {
                if (firstTAG < firstTGA) {
                    return dna.substring(firstATG, firstTAG + 3);
                }
                else {
                    return dna.substring(firstATG, firstTGA + 3);
                }
            }
        }
                
        return "";
    }
    
    public void testFindStopCodon() {
        System.out.println("TEST1");
        System.out.println();
        //               ATG3  6  9  2  5  8  TGA4  7  TAG3  6  TAA2  5  8 
        String sample = "ATGCCTGATAGTAACGTGATATGACGTAACTAGATGACCTAACTGCTGACT";
        System.out.print("Tested string of DNA is: ");
        System.out.println(sample);
        
        int stopIndexTAA = findStopCodon(sample, 0, "TAA");
        System.out.println(stopIndexTAA); //39
        System.out.println("Správna odpoveď: 39.");
        int stopIndexTGA = findStopCodon(sample, 0, "TGA");
        System.out.println(stopIndexTGA); //21
        System.out.println("Správna odpoveď: 21.");
        int stopIndexTAG = findStopCodon(sample, 0, "TAG");
        System.out.println(stopIndexTAG); //30
        System.out.println("Správna odpoveď: 30.");
        
        System.out.println();
        System.out.println("TEST2");
        System.out.println();
        //                ATG3  6  9  2  5  8  1  4  7  0  3  6  9  2  5  8 
        String sample2 = "ATGCTGTGAGGCCTAGTAACGTCACTGATATAGTAACGCATGACGTAACGA";
        int stopIndexTAA2 = findStopCodon(sample2, 0, "TAA");
        System.out.println(stopIndexTAA2); //33
        System.out.println("Správna odpoveď: 33.");
        int stopIndexTGA2 = findStopCodon(sample2, 0, "TGA");
        System.out.println(stopIndexTGA2); //6
        System.out.println("Správna odpoveď: 6.");
        int stopIndexTAG2 = findStopCodon(sample2, 0, "TAG");
        System.out.println(stopIndexTAG2); //30 (45)
        System.out.println("Správna odpoveď: 30.");
        
        System.out.println();
        System.out.println("TEST3");
        System.out.println();
        //                ATG3  6  9  2  5  8  1  4  7  0  3  6  9  2  5  8  1
        String sample3 = "ATGJANKOMRKVICKAGTTAGJABLKOTAAHRUSKATGAZEMIAKTAATGATAG";
        int stopIndexTAA3 = findStopCodon(sample3, 0, "TAA");
        System.out.println(stopIndexTAA3); //27 (45)
        System.out.println("Správna odpoveď: 27.");
        int stopIndexTGA3 = findStopCodon(sample3, 0, "TGA");
        System.out.println(stopIndexTGA3); //36 (48)
        System.out.println("Správna odpoveď: 36.");
        int stopIndexTAG3 = findStopCodon(sample3, 0, "TAG");
        System.out.println(stopIndexTAG3); //18 (51)
        System.out.println("Správna odpoveď: 18.");
        
        System.out.println();
        System.out.println("TEST4");
        System.out.println();
        //                ATG3  6  9  2  5  8  1  4  7  0  3  6  9  2  5  8  1
        String sample4 = "ATGJANKOMRKVICKAJABLKOHRUSKAZEMIAKANANASPOMARANCKIVI";
        int stopIndexTAA4 = findStopCodon(sample4, 0, "TAA");
        System.out.println(stopIndexTAA4); //51
        System.out.println("Správna odpoveď: 51.");
        int stopIndexTGA4 = findStopCodon(sample4, 0, "TGA");
        System.out.println(stopIndexTGA4); //51)
        System.out.println("Správna odpoveď: 51.");
        int stopIndexTAG4 = findStopCodon(sample4, 0, "TAG");
        System.out.println(stopIndexTAG4); //51
        System.out.println("Správna odpoveď: 51.");
        
        System.out.println();
        System.out.println("TEST5");
        System.out.println();
        //                ATG3  6  9  2  5  8  1  4  7  0  3  6  9  2  5  8  1  4  7
        String sample5 = "ATGJANKOMRKVICKACCTAGJABLKOHRUSKAZEMIAKANANASPOMARANCKIVI";
        int stopIndexTAA5 = findStopCodon(sample5, 0, "TAA");
        System.out.println(stopIndexTAA5); //56
        System.out.println("Správna odpoveď: 56.");
        int stopIndexTGA5 = findStopCodon(sample5, 0, "TGA");
        System.out.println(stopIndexTGA5); //56)
        System.out.println("Správna odpoveď: 56.");
        int stopIndexTAG5 = findStopCodon(sample5, 0, "TAG");
        System.out.println(stopIndexTAG5); //18
        System.out.println("Správna odpoveď: 18.");
        
        System.out.println();
        System.out.println("TEST6");
        System.out.println();
        //                ATG3  6  9  2  5  8  1  4  7  0  3  6  9  2  5  8  1  4  7
        String sample6 = "ATGJANKOMRKVICKACCTAAJABLKOHRUSKAZEMIAKANANASPOMARANCKIVI";
        int stopIndexTAA6 = findStopCodon(sample6, 0, "TAA");
        System.out.println(stopIndexTAA6); //18
        System.out.println("Správna odpoveď: 18.");
        int stopIndexTGA6 = findStopCodon(sample6, 0, "TGA");
        System.out.println(stopIndexTGA6); //56)
        System.out.println("Správna odpoveď: 56.");
        int stopIndexTAG6 = findStopCodon(sample6, 0, "TAG");
        System.out.println(stopIndexTAG6); //56
        System.out.println("Správna odpoveď: 56.");
    }
    
    public void testFindGene() {
        //test 1 - no ATG
        String sample1 = "mrkvazemiakTAApetrzlenTGAzelerTAGcvikla";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample1);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample1, 0));
        //test 2 - one valid stop codon
        String sample2 = "zemiakATGjablkohruskaTAApomaranckivi";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample2);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample2, 0));
        //test 3 - two valid stop codons
        String sample3 = "pomarancATGzemiakjablkoTGAhruskaTAGananaskivi";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample3);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample3, 0));
        //test 4 - three valid stop codons
        String sample4 = "maracujaATGananasTAGhruskaTGAjablkoTAAzemiakTGAkivi";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample4);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample4, 0));
        //test 5 - no valid stop codons
        String sample5 = "kapustaATGkalerabkarfiolparadajka";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample5);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample5, 0));
        //test 6
        String sample6 = "paradajkaATGpaprikaTAGmrkvaTGAzelerTAAcvikla";
        System.out.print("Testovaný reťazec dna je: ");
        System.out.println(sample6);
        System.out.print("V danom reťazci sa vyskytuje tento gén: ");
        System.out.println(findGene(sample6, 0));
    }    
    
    public void printAllGenes (String dna) {
        int start = 0;
        
        while (true) {
            String currentGene = findGene(dna, start);
            
            if (currentGene.isEmpty()) {
                break;
            }
            
            System.out.println(currentGene);
            
            start = dna.indexOf(currentGene, start) + currentGene.length();
        }
    }
    
    public void testPrintAllGenes() {
        //String sample = "ATGjablkoTAGhruškaATGpetrzlenTAAzemiakkiviATGananasTGAkivi";
        String sample = "AATGCTAACTAGCTGACTAAT";
        System.out.println("Testovaný reťazec je: " + sample);
        printAllGenes(sample);
        }
    }
