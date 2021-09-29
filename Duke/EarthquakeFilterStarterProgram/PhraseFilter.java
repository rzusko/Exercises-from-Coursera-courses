
/**
 * Trieda {@code PhraseFilter} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class PhraseFilter implements Filter {
    private String searchPos; //where to search ("start", "end", or "any")
    private String searchPhrase; //phrase to search
    private String filterName;
    
    public PhraseFilter(String name, String where, String phrase) {
        searchPos = where;
        searchPhrase = phrase;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        
        if (searchPos == "start") {
            return (title.startsWith(searchPhrase));
        }
        
        if (searchPos == "end") {
            return (title.endsWith(searchPhrase));
        }
        
        if (searchPos == "any") {
            if (title.indexOf(searchPhrase) != -1) {
                return true;
            }
        }
        
        return false;
    }
    
    public String getName() {
        return filterName;
    }
}
