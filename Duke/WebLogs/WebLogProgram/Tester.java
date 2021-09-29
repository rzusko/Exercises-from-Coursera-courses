
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("There are " + la.countUniqueIPs() + " unique IPs.");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> list = la.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("There are " + list.size() + " unique IPs on that day:");
        for (String rec : list) {
            System.out.println(rec);
        }
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int count = la.countUniqueIPsInRange(200,299);
        System.out.println("There are " + count + " unique IPs.");
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("The maximum number of visits by a single IP is: " + max);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> list = la.iPsMostVisits(counts);
        System.out.println(list);
    }
    
    public void testIPsForDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> IPsForDay = la.iPsForDays();
        for (String s : IPsForDay.keySet()) {
            System.out.println(s);
            for (String x : IPsForDay.get(s)) {
                System.out.println("    - " + x);
            }
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,ArrayList<String>> IPsForDay = la.iPsForDays();
        
        System.out.println("Day with most IP visits is: " + la.dayWithMostIPVisits(IPsForDay));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String,ArrayList<String>> IPsForDay = la.iPsForDays();
        String day = "Mar 17";
        ArrayList<String> list = la.iPsWithMostVisitsOnDay(IPsForDay, day);
        System.out.println(list);
    }
}
