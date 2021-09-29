
/**
 * Trieda {@code TitleLastAndMagnitudeComparator} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Info = q1.getInfo();
        String q2Info = q2.getInfo();
        String q1LastWord = q1Info.substring(q1Info.lastIndexOf(' '));
        String q2LastWord = q2Info.substring(q2Info.lastIndexOf(' '));
                      
        int result = q1LastWord.compareTo(q2LastWord);
        
        if (result == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        
        return result;
    }
}
