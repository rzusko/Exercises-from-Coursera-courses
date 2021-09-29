
/**
 * Trieda {@code MarkovWord} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
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
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        
        for (int i = 0; i < kGram.length(); i++) {
            sb.append(kGram.wordAt(i));
            sb.append(" ");
        }
        
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
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int size = kGram.length();
        
        while (true) {
            int index = indexOf(myText, kGram, pos);
            if ((index == -1) || (index == (myText.length-size))) {
                break;
            }
            follows.add(myText[index+size]);
            
            pos = index + 1;
        }
        
        return follows;
    }
       
}
