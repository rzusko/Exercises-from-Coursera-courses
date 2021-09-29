
/**
 * Trieda {@code EfficientMarkovModel} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int n;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovModel(int i) {
        myRandom = new Random();
        n = i;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public void buildMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        
        for (int i = 0; i <= (myText.length() - n); i++) {
            String s = myText.substring(i, i + n);
                        
            if (!myMap.containsKey(s)) {
                ArrayList<String> list = new ArrayList<String>();
                int pos = 0;
                
                while(true) {
                    int index = myText.indexOf(s, pos);
                    
                    if ((index == -1) || ((index == myText.length() - s.length()))) {
                        break;
                    }
                    
                    int followPos = index + s.length();
                    list.add(myText.substring(followPos, followPos+1));
                    
                    pos = index + 1;
                }
                
                myMap.put(s, list);
            }
        }
        
        printHashMapInfo(myMap);            
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> list = myMap.get(key);
        return list;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        
        buildMap();
              
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
                
        for(int k=0; k < (numChars - n); k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return ("MarkovModel of order " + n);
    }
    
    public void printHashMapInfo(HashMap<String, ArrayList<String>> map) {
        int maxSize = 0;

        for (String key : map.keySet()) {
            ArrayList<String> list = map.get(key);
            /*
            System.out.print("\'" + key + "\'   ");
            for (String s : list) {
                System.out.print("\'" + s + "\' ");
            }
            System.out.println();
            */
            int listSize = list.size();
            if (listSize > maxSize) {
                maxSize = listSize;              
            }
        }
        
        System.out.println("-----------------------------------");
        System.out.println("There are " + map.size() + " keys in map.");
        System.out.println("The size of largest value is " + maxSize);
        System.out.println("Keys that have maximum size value:");
        
        for (String key : map.keySet()) {
            if (map.get(key).size() == maxSize) {
                System.out.print("\'" + key + "\' ");
            }
        }
    }

}
