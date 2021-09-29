
/**
 * Trieda {@code EfficientMarkovWord} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        buildMap();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
                
        sb.append(kGram.toString());
        sb.append(" ");
        
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            /*
            System.out.println("------- key: ------");
            for (int i = 0; i < kGram.length(); i++) {
                System.out.print(kGram.wordAt(i) + " ");
            }
            System.out.println("\n------- follows: --------");
            for (String s : follows) {
                System.out.print(s + " ");
            }
            
            System.out.println("\n-----------------");
            //*/
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        int size = target.length();
        for (int i = start; i < (words.length-size); i++) {
            WordGram tested = new WordGram(words, i, size);
            if (tested.equals(target)) {
                return i;
            }
        }
                
        return -1;
    }
        
    public void buildMap() {
        myMap = new HashMap<WordGram, ArrayList<String>>();
        for (int i = 0; i <= (myText.length - myOrder); i++) {
            WordGram gram = new WordGram(myText, i, myOrder);
                               
            if (!myMap.containsKey(gram)) {
                ArrayList<String> follows = new ArrayList<String>();
                
                if ((i + myOrder) < myText.length) {
                    follows.add(myText[i+myOrder]);
                }
            
                myMap.put(gram, follows);
            }
            else {
                ArrayList<String> follows = myMap.get(gram);
                follows.add(myText[i+myOrder]);
                myMap.put(gram, follows);
            }
         }        
        //printHashMapInfo();
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = myMap.get(kGram);
        return follows;
    }
    
    public void printHashMapInfo() {
        int maxSize = 0;
        
        for (WordGram gram : myMap.keySet()) {
            ArrayList<String> list = myMap.get(gram);
            /*
            System.out.println("---------------");
            System.out.println("key: " + gram.toString());
            System.out.println("follows: " + list);
            System.out.println("---------------");
            //*/
            
            int listSize = list.size();
            if (listSize > maxSize) {
                maxSize = listSize;
            }
            
            
        }
        System.out.println("Number of keys: " + myMap.size());
        System.out.println("Size of largest follow list: " + maxSize);
        System.out.println("Keys that have largest follow list: ");
        
        for (WordGram gram : myMap.keySet()) {
            if (myMap.get(gram).size() == maxSize) {
                System.out.println(gram.toString());
            }
        }
    }
  
}
