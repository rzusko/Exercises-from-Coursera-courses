
/**
 * Trieda {@code Tester} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> answer = markov.getFollows("t");
        
        for (String s : answer) {
            System.out.println(s);
        }
        
        System.out.println("Size of array: " + answer.size());
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("th");
        /*
        for (String s : follows) {
            System.out.println(s);
        }
        */
        System.out.println("Size of array: " + follows.size());
    }
}
