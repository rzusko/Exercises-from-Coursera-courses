
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markov = new MarkovWord(5); 
        runModel(markov, st, 200, 844); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void testGetFollows() {
        String s = "this is a test this is a test this is a test this is a simple test";
        
        MarkovWord markov = new MarkovWord(3);
        runModel(markov, s, 10, 45);
        
    }
    
    public void testHashMap() {
        String st = "this is a test yes this is really a test yes a test this is wow";
        
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        runModel(markov, st, 50, 42);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        long start = System.nanoTime();
        
        MarkovWord markov = new MarkovWord(2);
        runModel(markov, st, 100, 42);
        
        long end = System.nanoTime();
        
        EfficientMarkovWord markovE = new EfficientMarkovWord(2);
        runModel(markovE, st, 100, 42);
        
        long endE = System.nanoTime();
        
        System.out.println(end-start);
        System.out.println(endE-end);                
    }
    
    public void testPrintHashMapInfo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovWord markovE = new EfficientMarkovWord(2);
        markovE.setTraining(st);
        markovE.setRandom(65);
        markovE.buildMap();
        markovE.printHashMapInfo();
        
    }
}
