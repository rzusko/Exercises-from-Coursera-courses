
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             
             if (!uniqueIPs.contains(ip)) {
                 uniqueIPs.add(ip);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             
             if (status > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) { 
         //someday in format Mmm DD ("Dec 05")
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String dStr = d.toString();
                         
             if (dStr.contains(someday)) {
                 String ip = le.getIpAddress();
                 
                 if (!uniqueIPs.contains(ip)) {
                     uniqueIPs.add(ip);
                 }
             }
         }
         
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             int status = le.getStatusCode();
             
             if ((status >= low) && (status <= high)) {
                 if (!uniqueIPs.contains(ip)) {
                     uniqueIPs.add(ip);
                 }
             }
         }
         
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             
             if (! counts.containsKey(ip)) {
                 counts.put(ip, 1);
             }
             else {
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
         int maxVisits = 0;
         
         for (Integer v : counts.values()) {
             if (v > maxVisits) {
                 maxVisits = v;
             }
         }
         
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
         ArrayList<String> IPsWithMaxVisits = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         
         for (String key : counts.keySet()) {
             if (counts.get(key) == max) {
                 IPsWithMaxVisits.add(key);
             }
         }
         
         return IPsWithMaxVisits;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records) {
             Date day = le.getAccessTime();
             String dayStr = day.toString().substring(4,10);
             String ip = le.getIpAddress();
             
             if (!map.containsKey(dayStr)){
                 ArrayList<String> list = new ArrayList<String>();
                 list.add(ip);
                 map.put(dayStr, list);
             }
             else {
                 ArrayList<String> list = map.get(dayStr);
                 list.add(ip);
                 map.put(dayStr, list);
             }
             
         }
                  
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IPsForDays) {
         String dayWithMostIP = "";
         int max = 0;
         
         for (String day : IPsForDays.keySet()) {
             int count = IPsForDays.get(day).size();
             if (count > max) {
                 max = count;
                 dayWithMostIP = day;
             }
         }
         
         return dayWithMostIP;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay
                (HashMap<String,ArrayList<String>> IPsForDay, String day) {
        ArrayList<String> list = IPsForDay.get(day);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (String ip : list) {
            if (!map.containsKey(ip)) {
                map.put(ip,1);
            }
            else {
                map.put(ip, map.get(ip)+1);
            }
        }
        
        ArrayList<String> result = iPsMostVisits(map);
                
        return result;
     }
}
