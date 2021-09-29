
/**
 * Trieda {@code FindGeneWhile} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class FindGeneWhile {
    public String fingGene(String dna) {
        //Find first occurance of "ATG", call its index "startIndex"
       int startIndex = dna.indexOf("ATG");
       //Find the "TAA" starting from (startIndex+3), call this result "currIndex"
       int currIndex = dna.indexOf("TAA", startIndex + 3);
       //As long as currIndex is not equal to -1
       while (currIndex != -1) {
            //Check if (currIndex - startIndex) is a multiple of 3
            if ((currIndex - startIndex)%3 == 0) {
                //If so, the text between startIndex and currIndex+3 is result
                return dna.substring(startIndex, currIndex+3);
            }
            else {
                //if not, update currIndex to the index of the next...
                currIndex = dna.indexOf("TAA", currIndex + 1);
            }
            }
       //Your answer is the empty string
       return"";
    }

}
