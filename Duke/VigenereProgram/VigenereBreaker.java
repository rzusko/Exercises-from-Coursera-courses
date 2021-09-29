import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder answer = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char ch = message.charAt(i);
            answer.append(ch);
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int i = 0; i < klength; i++) {
            String x = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(x);
        }
        
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource encrFile = new FileResource();
        String encrFileStr = encrFile.asString();
        
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] langs =  {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        
        for (int i = 0; i < langs.length; i++) {
            String lang = langs[i];
            FileResource dictFile = new FileResource("dictionaries/" + lang);
            HashSet<String> dictionary = readDictionary(dictFile);
            languages.put(lang, dictionary);
            System.out.println("Dictionary for " + lang + " language has beed red.");
        }
        
        String decrFileStr = breakForAllLangs(encrFileStr, languages);
        
        System.out.println(decrFileStr);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet dictionary = new HashSet();
        
        for (String line : fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] splitedMessage = message.split("\\W");
        
        for (int i = 0; i < splitedMessage.length; i++) {
            if (dictionary.contains(splitedMessage[i].toLowerCase())) {
                count++;
            }
        }
        
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decrypted = "";
        int maxCount = 0;
        int KLength = 0;
        String keyFinal = "";
        char mostCommon = mostCommonCharIn(dictionary);
                               
        for (int i = 1; i <= 100; i++) {
            int[] possibleKey = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(possibleKey);
            String decrMessage = vc.decrypt(encrypted);
            int count = countWords(decrMessage, dictionary);
            
            if (count > maxCount) {
                maxCount = count;
                decrypted = decrMessage;
                KLength = possibleKey.length;
                keyFinal = Arrays.toString(possibleKey);
            }
            
            if (i == 5) {
                System.out.println("For key length 5, there are " + count  + " valid words.");
                System.out.println(decrypted);
                System.out.println(Arrays.toString(possibleKey));
            }
        }
        
        System.out.println("Key length is: " + KLength);
        System.out.println("Key is: " + keyFinal);
        System.out.println("There are " + maxCount + " valid words.\n\n");
        
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostCommonChar = ' ';
        int maxValue = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < alphabet.length(); i++) {
            map.put(alphabet.charAt(i), 0);
        }
        
        for (String s : dictionary) {
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                                
                if (map.containsKey(ch)) {
                    int value = map.get(ch);
                    map.put(ch, value + 1);
                }
            }
        }
        
        for (char key : map.keySet()) {
            int value = map.get(key);
            
            if (value > maxValue) {
                maxValue = value;
                mostCommonChar = key;
            }
        }
                      
        return mostCommonChar;
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String decrypted = "";
        int maxCount = 0;
        String lang = "";
        
        for (String key : languages.keySet()) {
            HashSet<String> dictionary = languages.get(key);
            
            System.out.println("Language: " + key + "\n");
            
            String decrMessage = breakForLanguage(encrypted, dictionary);
            
            int count = countWords(decrMessage, dictionary);
            
            if (count > maxCount) {
                maxCount = count;
                decrypted = decrMessage;
                lang = key;
            }
            
        }
        
        System.out.println("Language of message is: " + lang);
        //System.out.println(decrypted);
        
        return decrypted;
    }
    
    public void testSliceString() {
        String x = "abcdefghijklm";
                
        for (int j = 3; j <= 5; j++) {
            for (int i = 0; i < j; i++) {
                String y = sliceString(x, i, j);
                System.out.println(y);
            }
        }
    }
    
    public void testTryKeyLength() {
        FileResource fs = new FileResource();
        String message = fs.asString();
        int keyL = 38;
        char mostComLet = 'e';
        
        int[] xyz = tryKeyLength(message, keyL, mostComLet);
        
        for (int i = 0; i < keyL; i++) {
            System.out.println(xyz[i]);
        }
    }
    
    public void testMostCommonCharIn() {
        FileResource fr = new FileResource();
        HashSet<String> hs = readDictionary(fr);
                     
        char ch = mostCommonCharIn(hs);
        System.out.println(ch);
    }
    
    public void test() {
        FileResource fr = new FileResource();
        String encrMessage = fr.asString();
        
        for (int i = 0; i < 5; i++) {
            String slice = sliceString(encrMessage, i, 5);
            System.out.println("Slice " + i + ":\n");
            System.out.println(slice + "\n\n");
            
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            int[] counts = new int[26];
            for (int k = 0; k < slice.length(); k++) {
                int index = alphabet.indexOf(Character.toLowerCase(slice.charAt(k)));
                if (index != -1) {
                    counts[index] += 1;
                }
            }
            
            int maxIndex = 0;
            
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > counts[maxIndex]) {
                    maxIndex = j;
                }
            }
            
            System.out.println("Most common char in slice is: " + alphabet.charAt(maxIndex) + "\n\n");
            
        }
        
    }
}
