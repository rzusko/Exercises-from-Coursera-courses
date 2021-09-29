
/**
 * Trieda {@code MarkovModel} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int n;
    
    public MarkovModel(int i) {
        myRandom = new Random();
        n = i;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        
        while (true) {
            int index = myText.indexOf(key, pos);
            
            if ((index == -1) || (index == (myText.length()-key.length()))) {
                break;
            }
            
            pos = index + key.length();
            follows.add(myText.substring(pos, pos+1));
        }
                
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        
        for(int k=0; k < (numChars - n); k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
}
