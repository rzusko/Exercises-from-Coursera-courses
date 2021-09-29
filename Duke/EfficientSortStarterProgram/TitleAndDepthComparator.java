
/**
 * Trieda {@code TitleAndDepthComparator} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int result = q1.getInfo().compareTo(q2.getInfo());
        
        if (result == 0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
               
        return result;
    }

}
