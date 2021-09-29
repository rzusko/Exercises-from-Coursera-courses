
/**
 * Trieda {@code Part2} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
    String result = "";
    String upperCase = dna.toUpperCase();
    int start = upperCase.indexOf(startCodon.toUpperCase()); //ATG
    if (start == -1) {
    return "";
    }
    
    int stop = upperCase.indexOf(stopCodon.toUpperCase(), start+3);  //TAA
    if (stop == -1) {
    return "";
    }
    
    int length = stop - start;
    if (length%3 == 0) {
    result = dna.substring(start, stop+3);
    }
    
    return result;
    }
    
    public void testSimpleGene() {
    String dna1 = "TACACATGAACTACGTGCGCTAAGTAAGTATGCGCGGAGCTAGCTAGCTGATG"; //OK ATGAACTACGTGCGCTAA
    System.out.println("String of DNA is: " +dna1);
    String gene1 = findSimpleGene(dna1, "ATG", "TAA");
    System.out.println("Gene is: " +gene1);
    
    String dna2 = "ACTGCCGCGATGAGTAGCTGATAGCTTCGTAGCTGAGCTGATCG"; //no TAA
    System.out.println("String of DNA is: " +dna2);
    String gene2 = findSimpleGene(dna2, "ATG", "TAA");
    System.out.println("Gene is: " +gene2);
        
    String dna3 = "ACGCGATCGTGCTGATCGCGCTAAGCTGAGATCCG"; //no ATG
    System.out.println("String of DNA is: " +dna3);
    String gene3 = findSimpleGene(dna3, "ATG", "TAA");
    System.out.println("Gene is: " +gene3);
    
    String dna4 = "GCTGCTGATGTAGCTGCGACGAGATAATG"; //no gene
    System.out.println("String of DNA is: " +dna4);
    String gene4 = findSimpleGene(dna4, "ATG", "TAA");
    System.out.println("Gene is: " +gene4);
    
    String dna5 = "TAGCGCGTAGCTCGATAGCTCTAGGACTGATCGGATCGT";
    System.out.println("String of DNA is: " +dna5);
    String gene5 = findSimpleGene(dna5, "ATG", "TAA");
    System.out.println("Gene is: " +gene5);
    
    String dna6 = "gtcatgcgcagacggtaacgatg";
    System.out.println("String of DNA is: " +dna6);
    String gene6 = findSimpleGene(dna6, "ATG", "TAA");
    System.out.println("Gene is: " +gene6);
    
    String dna7 = "TACACATGAACTACGTGCGCTAAGTAAGTATGCGCGGAGCTAGCTAGCTGATG";
    System.out.println("String of DNA is: " +dna7);
    String gene7 = findSimpleGene(dna7, "atg", "taa");
    System.out.println("Gene is: " +gene7);
    
    String dna8 = "AAATGCCCTAACTAGATTAAGAAACC";
    System.out.println("String of DNA is: " +dna8);
    String gene8 = findSimpleGene(dna8, "atg", "taa");
    System.out.println("Gene8 is: " +gene8);
    }

}
