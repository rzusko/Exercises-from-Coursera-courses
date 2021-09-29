
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
       System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 42;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
    
    public void testHashMap() {
        int seed = 615;
        //*
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //*/
        //String st = "yes-this-is-a-thin-pretty-pink-thistle";
        
        int size = 50;
        
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        runModel(markov, st, size, seed);
    }
    
    public void compareMethods() {
        int seed = 42;
        int size = 1000;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        long start = System.nanoTime();
        
        MarkovModel markov = new MarkovModel(2);
        runModel(markov, st, size, seed);
        
        long end1 = System.nanoTime();
        
        EfficientMarkovModel eMarkov = new EfficientMarkovModel(2);
        runModel(eMarkov, st, size, seed);
        
        long end2 = System.nanoTime();
        
        System.out.println(start);
        System.out.println(end1 - start);
        System.out.println(end2 - end1);
    }
}
