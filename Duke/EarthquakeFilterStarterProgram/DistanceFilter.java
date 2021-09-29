
/**
 * Trieda {@code DiestaceFilter} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class DistanceFilter implements Filter {
    private Location refPos; // reference position
    private double maxDist;
    private String filterName;
    
    public DistanceFilter(String name, Location where, double max) {
        refPos = where;
        maxDist = max;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        Location currPos = qe.getLocation(); //current position
        
        return (currPos.distanceTo(refPos) < maxDist); 
        
    }
    
    public String getName() {
        return filterName;
    }

}
