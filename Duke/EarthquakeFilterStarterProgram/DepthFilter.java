
/**
 * Trieda {@code DepthFilter} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(String name, double min, double max) {
        minDepth = min;
        maxDepth = max;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double currDepth = qe.getDepth();
        
        return ((currDepth <= maxDepth) && (currDepth >= minDepth));
        
    }
    
    public String getName() {
        return filterName;
    }

}
