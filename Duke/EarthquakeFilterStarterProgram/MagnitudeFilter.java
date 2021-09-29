
/**
 * Trieda {@code MagnitudeFilter} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String filterName;
    
    public MagnitudeFilter(String name, double min, double max) {
        minMag = min;
        maxMag = max;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double currMag = qe.getMagnitude();
        
        return ((currMag <= maxMag) && (currMag >= minMag));
    }
    
    public String getName() {
        return filterName;
    }

}
