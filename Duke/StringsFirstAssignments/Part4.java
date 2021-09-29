import edu.duke.*;
/**
 * Trieda {@code Part4} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
public class Part4 {
    public static void main (String arg[]) {
    URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    
    for (String s : url.words()) {
       String lowerCase = s.toLowerCase();
       int youtube = lowerCase.indexOf("youtube.com");
       
       if (youtube != -1) {
           int start = lowerCase.lastIndexOf("\"", youtube);
           int stop = lowerCase.indexOf("\"", youtube+1);
           String result = s.substring(start+1, stop);

           System.out.println(result);
        }
          
    }
    
    }
}
