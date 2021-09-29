
/**
 * Trieda {@code LargestQuakes} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        //for (QuakeEntry qe : list) {
        //    System.out.println(qe);
        //}
                
        System.out.println("read data for " + list.size() + " quakes.");
        
        //int locationIndex = indexOfLargest(list);
        //System.out.println("Largest magnitude earthquake is at location " + locationIndex + ":");
        //System.out.println(list.get(locationIndex));
        
        ArrayList<QuakeEntry> largest = getLargest(list, 5);
        
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        double maxMag = 0.0;
        
        for (int i = 0; i < data.size(); i++) {
            double currMag = data.get(i).getMagnitude();
            
            if (currMag > maxMag) {
                index = i;
                maxMag = currMag;
            }
        }
        
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        
        for (int i = 0; i < howMany; i++) {
            int locationIndex = indexOfLargest(quakeDataCopy);
            answer.add(quakeDataCopy.get(locationIndex));
            quakeDataCopy.remove(locationIndex);
            
            if (quakeDataCopy.size() == 0) {
                i = howMany;
            }
        }
        
        return answer;
    }

}
