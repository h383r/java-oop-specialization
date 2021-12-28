package module4;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.EsriProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * */
public class EarthquakeCityMap extends PApplet {
	
	// We will use member variables, instead of local variables, to store the data
	// that the setUp and draw methods will need to access (as well as other methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of earthquakes
	// per country.
	
	// You can ignore this.  It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFILINE, change the value of this variable to true
	private static final boolean offline = false;
	
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	//private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom";
	
	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap map;
	
	// Markers for each city
	private List<Marker> cityMarkers;
	
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(900, 700, OPENGL);
		
		AbstractMapProvider providerEsri = new EsriProvider.NatGeoWorldMap();
		AbstractMapProvider providerGoogle = new Google.GoogleMapProvider();
		
		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 650, 600, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom";  // The same feed, but saved August 7, 2015
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 650, 600, providerEsri);
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    //earthquakesURL = "2.5_week.atom";
		}
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// FOR TESTING: Set earthquakesURL to be one of the testing files by uncommenting
		// one of the lines below.  This will work whether you are online or offline
		//earthquakesURL = "test1.atom";
		//earthquakesURL = "test2.atom";
		
		// WHEN TAKING THIS QUIZ: Uncomment the next line
		earthquakesURL = "quiz1.atom";
		
		
		// (2) Reading in earthquake data and geometric properties
	    //     STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		//     STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) {
		  cityMarkers.add(new CityMarker(city));
		}
	    
		//     STEP 3: read in earthquake RSS feed
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {
		  //check if LandQuake
		  if(isLand(feature)) {
		    quakeMarkers.add(new LandQuakeMarker(feature));
		  }
		  // OceanQuakes
		  else {
		    quakeMarkers.add(new OceanQuakeMarker(feature));
		  }
	    }

	    // could be used for debugging
	    printQuakes();
	 		
	    // (3) Add markers to map
	    //     NOTE: Country markers are not added to the map.  They are used
	    //           for their geometric properties
	    map.addMarkers(quakeMarkers);
	    map.addMarkers(cityMarkers);
	    
	}  // End setup
	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();
		
	}
	
	// helper method to draw key in GUI
	// TODO: Update this method as appropriate
	private void addKey() {	
		
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240);
		rect(25, 50, 150, 250);
		
		textAlign(LEFT, CENTER);
		textSize(12);
		
		// Key Title
		fill(0, 0, 0);
		text("Earthquake Key", 50, (75 + 25 * 0));
		// 25 50 ############################################################
		// Line 1
		fill(0, 0, 0);
		text("City Marker", 75, (75 + 25 * 1));
		fill(150, 30, 30 );
		triangle(60, 100 - CityMarker.TRI_SIZE, 60 - CityMarker.TRI_SIZE, 100 + CityMarker.TRI_SIZE, 60 + CityMarker.TRI_SIZE, 100 + CityMarker.TRI_SIZE);

		// Line 2
		fill(0, 0, 0);
		text("Land Quake", 75, (75 + 25 * 2));
		fill(255, 255, 255);
		ellipse(60, (75 + 25 * 2), 10, 10);
		
		// Line 3
		fill(0, 0, 0);
		text("Ocean Quake", 75, (75 + 25 * 3));
		fill(255, 255, 255);
		rect(55, (75 + 25 * 3) - 5, 10, 10);
		
		// Line 4
		fill(0, 0, 0);
		text("Size - Magnitude", 50, (75 + 25 * 4));
		
		// Line 5
		fill(0, 0, 0);
		text("Shallow", 75, (75 + 25 * 5));
		fill(255, 255, 0);
		ellipse(60, (75 + 25 * 5), 12, 12);
		
		// Line 6
		fill(0, 0, 0);
		text("Intermediate", 75, (75 + 25 * 6));
		fill(0, 0, 255);
		ellipse(60, (75 + 25 * 6), 12, 12);
		
		// Line 7
		fill(0, 0, 0);
		text("Deep", 75, (75 + 25 * 7));
		fill(255, 0, 0);
		ellipse(60, (75 + 25 * 7), 12, 12);
		
		// Line 8
		fill(0);
		text("Past hour", 75, (75 + 25 * 8));
		fill(255, 255, 255);
		int pastHourElipseX = 60;
		int pastHourElipseY = 75 + 25 * 8;
		ellipse(pastHourElipseX,pastHourElipseY , 12, 12);
		strokeWeight(1);
		line(pastHourElipseX - 4, pastHourElipseY - 4, pastHourElipseX + 4, pastHourElipseY + 4);
		line(pastHourElipseX - 4, pastHourElipseY + 4, pastHourElipseX + 4, pastHourElipseY - 4);
				
	}

	
	
	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.
	private boolean isLand(PointFeature earthquake) {
		
		// Loop over all the country markers.
		for (Marker m : countryMarkers) {
		
			// For each, check if the earthquake PointFeature is in the 
			// country in m.  Notice that isInCountry takes a PointFeature
			// and a Marker as input.  
			if (isInCountry(earthquake, m)) {
				
				// If isInCountry ever returns true, isLand should return true.
				return true;
			}
		}
		
		// not inside any country
		return false;
	}
	
	/* prints countries with number of earthquakes as
	 * Country1: numQuakes1
	 * Country2: numQuakes2
	 * ...
	 * OCEAN QUAKES: numOceanQuakes
	 * */
	private void printQuakes() {
		
		int waterQuakes = quakeMarkers.size();
		
		// One (inefficient but correct) approach is to:
		// Loop over all of the countries
		for (Marker countryMarker : countryMarkers) {
			
			// Inside the loop, first initialize a quake counter.
			int quakeCounter = 0;
			
			// Then loop through all of the earthquake markers
			for (Marker earthquake : quakeMarkers) {
				
				EarthquakeMarker earthquakeMarker = (EarthquakeMarker)earthquake;
				
				// Check to see whether (1) that marker is on land
				if (earthquakeMarker.isOnLand()) {
					
					// and (2) if it is on land, that its country property matches 
					// the name property of the country marker.   
					if (earthquake.getProperty("country") == countryMarker.getProperty("name")) {
												
						// If so, increment the country's counter.
						quakeCounter = quakeCounter + 1;
					}	
				}
			}
			
			// Prints results
			if (quakeCounter > 0) {
				waterQuakes = waterQuakes - quakeCounter;
				
				String countryName = countryMarker.getStringProperty("name");
				System.out.println(countryName + ": " + quakeCounter);
			}
		}
		System.out.println("OCEAN QUAKES: " + waterQuakes);
		
		// Here is some code you will find useful:
		// 
		//  * To get the name of a country from a country marker in variable cm, use:
		//     String name = (String)cm.getProperty("name");
		//  * If you have a reference to a Marker m, but you know the underlying object
		//    is an EarthquakeMarker, you can cast it:
		//       EarthquakeMarker em = (EarthquakeMarker)m;
		//    Then em can access the methods of the EarthquakeMarker class 
		//       (e.g. isOnLand)
		//  * If you know your Marker, m, is a LandQuakeMarker, then it has a "country" 
		//      property set.  You can get the country with:
		//        String country = (String)m.getProperty("country");
		
		
	}
	
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake 
	// feature if it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}

}
