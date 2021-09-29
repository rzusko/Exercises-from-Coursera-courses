import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        //*
        Filter f1 = new MagnitudeFilter("Magnitude", 4.0, 5.0); 
        Filter f2 = new DepthFilter("Depth", -35000.0, -12000.0);
        ArrayList<QuakeEntry> answer1 = filter(list, f1); 
        ArrayList<QuakeEntry> answer2 = filter(answer1, f2);
        for (QuakeEntry qe: answer2) { 
            System.out.println(qe);
        } 
        //*
        
        /*
        Location reference = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter("Distance",reference, 10000000);
        Filter f2 = new PhraseFilter("Phrase", "end", "Japan");
        ArrayList<QuakeEntry> answer1 = filter(list, f1);
        ArrayList<QuakeEntry> answer2 = filter(answer1, f2);
        for (QuakeEntry qe : answer2) {
            System.out.println(qe);
        }
        */
        System.out.println("Found " + answer2.size() + " earthquakes.");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        //for (QuakeEntry qe : list) {
        //    System.out.println(qe);
        //}
        System.out.println("read data for : " + list.size() + "quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("Magnitude", 0.0, 2.0));
        maf.addFilter(new DepthFilter("Depth", -100000.0, -10000.0));
        maf.addFilter(new PhraseFilter("Phrase", "any", "a"));
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " earthquakes.");
        System.out.println("Filter used are: " + maf.getName());
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        //for (QuakeEntry qe : list) {
        //    System.out.println(qe);
        //}
        System.out.println("read data for : " + list.size() + "quakes");
        
        Location Tulsa = new Location(36.1314, -95.9372);
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter("Magnitude", 0.0, 3.0));
        maf.addFilter(new DistanceFilter("Distance", Tulsa, 10000000));
        maf.addFilter(new PhraseFilter("Phrase", "any", "Ca"));
        
        ArrayList<QuakeEntry> answer = filter(list, maf);
        
        for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + answer.size() + " earthquakes.");
    }
}
