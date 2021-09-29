import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            Location current = qe.getLocation();
            //System.out.println(current.distanceTo(from) + " " + qe.getInfo());
            if ((current.distanceTo(from)/1000) < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> bigQuakesList = filterByMagnitude(list, 5.0);
        
        for (QuakeEntry qe : bigQuakesList) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + bigQuakesList.size() + "quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeToMeList = filterByDistanceFrom(list, 1000.0, city);
        
        for (QuakeEntry qe : closeToMeList) {
            System.out.print(qe.getLocation().distanceTo(city)/1000 + " ");
            System.out.println(qe.getInfo());
        }
        
        System.out.println("Found " + closeToMeList.size() + " quakes that match criteria");
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            double currentDepth = qe.getDepth();
            if ((currentDepth > minDepth) && (currentDepth < maxDepth)) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser =  new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgv.gov/earthqiakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        double minDepth = -10000.0;
        double maxDepth = -8000.0;
        
        ArrayList<QuakeEntry> quakesOfDepthList = filterByDepth(list, minDepth, maxDepth);
        
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);       
        for (QuakeEntry qe : quakesOfDepthList) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + quakesOfDepthList.size() + " quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData) {
            String quakeInfo = qe.getInfo();
            
            if (where.equals("start")) {
                if (quakeInfo.startsWith(phrase)) {
                    answer.add(qe);
                }
            }
            if (where.equals("end")) {
                if (quakeInfo.endsWith(phrase)) {
                    answer.add(qe);
                }
            }
            if (where.equals("any")) {
                if (quakeInfo.indexOf(phrase) != -1) {
                    answer.add(qe);
                }
            }
        }
        
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgv.gov/earthqiakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        //String where = "start";
        //String phrase = "Explosion";
        
        //String where = "end";
        //String phrase = "California";
        
        String where = "any";
        String phrase = "Creek";
                
        ArrayList<QuakeEntry> quakesByPhraseList = filterByPhrase(list, where, phrase);
        
        for (QuakeEntry qe : quakesByPhraseList) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + quakesByPhraseList.size() + " quakes that match " + phrase + " at " + where);
    }
}

