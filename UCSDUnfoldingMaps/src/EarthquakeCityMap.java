import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet
{
	private UnfoldingMap map;
	
	public void setup() {
		size (950, 600, OPENGL);
		map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		Location valLoc = new Location(-38.29f, -73.05f);
		
		PointFeature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdiva, Chile");
		valEq.addProperty("magnitude", 9.5);
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", 1960);
		//Marker valMk = new SimplePointMarker(valLoc, valEq.getProperties());
		//map.addMarker(valMk);
		
		Location alaskaLoc = new Location(61.02f, -147.65f);
		PointFeature alaskaEq = new PointFeature(alaskaLoc);
		alaskaEq.addProperty("title", "1964 Great Alaska Earthquake");
		alaskaEq.addProperty("magnitude", 9.2);
		alaskaEq.addProperty("date", "March 28, 1964");
		alaskaEq.addProperty("year", 1964);
		//Marker alaskaMk = new SimplePointMarker(alaskaLoc, alaskaEq.getProperties());
		//map.addMarker(alaskaMk);
		
		Location sumatraLoc = new Location(3.3f, 95.78f);
		PointFeature sumatraEq = new PointFeature(sumatraLoc);
		sumatraEq.addProperty("Title", "Off the West Coast of Northern Sumatra");
		sumatraEq.addProperty("magnitude", 9.1);
		sumatraEq.addProperty("date", "December 26, 2004");
		sumatraEq.addProperty("year", 2004);
		//Marker sumatraMk = new SimplePointMarker(sumatraLoc, sumatraEq.getProperties());
		//map.addMarker(sumatraMk);
		
		Location japanLoc = new Location(38.322f, 142.369f);
		PointFeature japanEq = new PointFeature(japanLoc);
		japanEq.addProperty("Title", "Near the East Coast of Honshu, Japan");
		japanEq.addProperty("magnitude", 9.0);
		japanEq.addProperty("date", "March 11, 2011");
		japanEq.addProperty("year", 2011);
		//Marker japanMk = new SimplePointMarker(japanLoc, japanEq.getProperties());
		//map.addMarker(japanMk);
		
		Location kamchatkaLoc = new Location(52.76f, 160.06f);
		PointFeature kamchatkaEq = new PointFeature(kamchatkaLoc);
		kamchatkaEq.addProperty("Title", "Kamchatka");
		kamchatkaEq.addProperty("magnitude", 9.0);
		kamchatkaEq.addProperty("date", "November 04, 1952");
		kamchatkaEq.addProperty("year", 1952);
		//Marker kamchatkaMk = new SimplePointMarker(kamchatkaLoc, kamchatkaEq.getProperties());
		//map.addMarker(kamchatkaMk);
		
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add(valEq);
		bigEqs.add(alaskaEq);
		bigEqs.add(sumatraEq);
		bigEqs.add(japanEq);
		bigEqs.add(kamchatkaEq);
		
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: bigEqs) {
			markers.add(new SimplePointMarker(eq.getLocation(), eq.getProperties()));
			
		}
		
		for (Marker mk : markers) 
		{
			if ((int)mk.getProperty("year") > 2000)
			{
				mk.setColor(255);
			}
		}
		
		map.addMarkers(markers);
	}
	
	public void draw() {
		background(220);
		map.draw();
		addKey();
		
		
		
	}

	private void addKey() {
		// TODO Auto-generated method stub
		
	}
	
}