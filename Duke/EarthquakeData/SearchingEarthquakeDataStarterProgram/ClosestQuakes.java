
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        
        for (int i = 0; i < howMany; i++) {
            double minDist = current.distanceTo(quakeDataCopy.get(0).getLocation());
            int entryPos = 0;
            for (int k = 1; k < quakeDataCopy.size(); k++) {
                QuakeEntry entry = quakeDataCopy.get(k);
                double currDist = current.distanceTo(entry.getLocation());
                
                if (currDist < minDist) {
                    entryPos = k;
                    minDist = currDist;
                }
            }
            ret.add(quakeDataCopy.get(entryPos));
            quakeDataCopy.remove(entryPos);
            if (quakeDataCopy.size() == 0) {
                i = howMany;
            }
        }

        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size() + " quakes.");

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,50);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
