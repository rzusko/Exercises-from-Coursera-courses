
/**
 * Trieda {@code Part1} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part1 {
    public String findSimpleGene(String dna) {
    String result = "";
    int startCodon = dna.indexOf("ATG"); //ATG
    if (startCodon == -1) {
    return "";
    }
    
    int stopCodon = dna.indexOf("TAA", startCodon+3);  //TAA
    if (stopCodon == -1) {
    return "";
    }
    
    int length = stopCodon - startCodon;
    if (length%3 == 0) {
    result = dna.substring(startCodon, stopCodon+3);
    }
    
    return result;
    }
    
    public void testSimpleGene() {
    String dna1 = "TACACATGAACTACGTGCGCTAAGTAAGTATGCGCGGAGCTAGCTAGCTGATG"; //OK ATGAACTACGTGCGCTAA
    System.out.println("String of DNA is: " +dna1);
    String gene1 = findSimpleGene(dna1);
    System.out.println("Gene is: " +gene1);
    
    String dna2 = "ACTGCCGCGATGAGTAGCTGATAGCTTCGTAGCTGAGCTGATCG"; //no TAA
    System.out.println("String of DNA is: " +dna2);
    String gene2 = findSimpleGene(dna2);
    System.out.println("Gene is: " +gene2);
        
    String dna3 = "ACGCGATCGTGCTGATCGCGCTAAGCTGAGATCCG"; //no ATG
    System.out.println("String of DNA is: " +dna3);
    String gene3 = findSimpleGene(dna3);
    System.out.println("Gene is: " +gene3);
    
    String dna4 = "GCTGCTGATGTAGCTGCGACGAGATAATG"; //no gene
    System.out.println("String of DNA is: " +dna4);
    String gene4 = findSimpleGene(dna4);
    System.out.println("Gene is: " +gene4);
    
    String dna5 = "TAGCGCGTAGCTCGATAGCTCTAGGACTGATCGGATCGT";
    System.out.println("String of DNA is: " +dna5);
    String gene5 = findSimpleGene(dna5);
    System.out.println("Gene is: " +gene5);
    
    }
   
}