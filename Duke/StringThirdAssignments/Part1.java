import edu.duke.*;
/**
 * Trieda {@code Part1} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int firstOccur = dna.toUpperCase().indexOf(stopCodon, startIndex);
               
        while (firstOccur != -1) {
            
            if ((firstOccur - startIndex)%3 == 0) {
                return firstOccur;
            }
            else {
            firstOccur = dna.toUpperCase().indexOf(stopCodon, firstOccur + 1);
            }
        }
        
        return dna.length();            
    }
    
    public String findGene(String dna, int where) {
        int firstATG = dna.toUpperCase().indexOf("ATG", where);
        
        if (firstATG == -1) {
            return "";
        }
        
        int firstTAA = findStopCodon(dna.toUpperCase(), firstATG, "TAA");
        int firstTAG = findStopCodon(dna.toUpperCase(), firstATG, "TAG");
        int firstTGA = findStopCodon(dna.toUpperCase(), firstATG, "TGA");
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
    
    public void printAllGenes(String dna) {
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
    
    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource genes = new StorageResource();
        
        while (true) {
            String currentGene = findGene(dna, start);
            
            if (currentGene.isEmpty()) {
                break;
            }
            
            genes.add(currentGene);
            
            start = dna.indexOf(currentGene, start) + currentGene.length();
        }
        
        return genes;
    }
    
    public void testPrintAllGenes() {
        String sample = "ATGjablkoTAGhruškaATGpetrzlenTAAzemiakkiviATGananasTGAkivi";
        //String sample = "AATGCTAACTAGCTGACTAAT";
        System.out.println("Testovaný reťazec je: " + sample);
        printAllGenes(sample);
    }
        
    public void testGetAllGenes() {
        String sample = "ATGjablkoTAGhruškaATGzemiakTAApetrzlenATGananasTGAkivi";
        //String sample = "AATGCTAACTAGCTGACTAAT";
        System.out.println("Testovaný reťazec je: " + sample);
        StorageResource geneDatabase = getAllGenes(sample);
        
        for (String g : geneDatabase.data()) {
            System.out.println(g);
        }
    }
   
    public double cgRatio(String dna) {
        int cg = 0;
        int i = 0;
        
        while (i < dna.length()) {
            String s = (dna.substring(i, i+1)).toUpperCase();
            //System.out.println(s);
            
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
    
    public void processGenes(StorageResource sr) {
        StorageResource longerThan60Char = new StorageResource();
        StorageResource cgHigherThan035 = new StorageResource();
        int lengthOfTheLongestGene = 0;
                
        for (String g : sr.data()) {
            if (g.length() > 60) {
                longerThan60Char.add(g);                
            }
            
            System.out.println("CGRatio: " + cgRatio(g));
            if (cgRatio(g) > 0.35) {
                cgHigherThan035.add(g);
            }
            
            if (g.length() > lengthOfTheLongestGene) {
                lengthOfTheLongestGene = g.length();
            }
        }
        
        System.out.println("Strings that are longer than 60 characters:");
        for (String g : longerThan60Char.data()) {
            System.out.println(g);
            System.out.println();
        }
        System.out.println();
        
        System.out.print("Number of Strings longer than 60 characters: ");
        System.out.println(longerThan60Char.size());
        System.out.println();
        
        System.out.println("Strings in whose C-G ratio is higher than 0.35:");
        for (String g : cgHigherThan035.data()) {
            System.out.println(g);
            System.out.println();
                    }
        System.out.println();
        
        System.out.print("Number of Strings in whose C-G ratio is higher than 0.35: ");
        System.out.println(cgHigherThan035.size());
        System.out.println();
        
        System.out.print("Length of the longest gene: ");
        System.out.println(lengthOfTheLongestGene);
    }
    
    public void testProcessGenes() {
        // longer than 9 char
        System.out.println("Sample 1.");
  
        //                V  v  v  X  v  V  v  v  v  v  X  V  X  v
        String sample1 = "ATGCCACTGTGACGAATGCTGCCGAAATTATAAATGTGAAAT";
        processGenes(getAllGenes(sample1));
        System.out.println();
        
        // no longer than 9 char
        System.out.println("Sample 2.");
        //                V  X  v  v  V  v  X
        String sample2 = "ATGTGACTGCGAATGTCGTAA";
        processGenes(getAllGenes(sample2));
        System.out.println();
        
        
        // C-G ratio higher than 0.35
        System.out.println("Sample 3.");
        //                V  v  v  v  v  X
        String sample3 = "ATGCCGCGCACGTCGTAA";
        processGenes(getAllGenes(sample3));
        System.out.println();
        
        System.out.println("Sample 4.");
        // C-G ratio lower than 0.35
        //                V  v  v  X  V  v  v  X
        String sample4 = "ATGTATATATAAATGATAGTGTAA";
        processGenes(getAllGenes(sample4));
        System.out.println();
        
        System.out.println("Sample 5.");
        //                v  v  V  v  v  v  X  v  V  v  v  v  X  V  v  v  v  v  v  v  v  X  v  v  
        String sample5 = "CCGCAAATGCGACAGCTGTGAACGATGCAACTGTCATGAATGGAGGCGGGATGCCCAAAGGGCTGACGCAAC";
        processGenes(getAllGenes(sample5));
        System.out.println();
    }
    
    public void testProcessGenesFile() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        System.out.println("Length of file: " + dna.length());
        processGenes(getAllGenes(dna));
    }
}
