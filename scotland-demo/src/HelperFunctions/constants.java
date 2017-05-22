package HelperFunctions;

public class constants 
{
	// Constant values used across the project.
	
	// ChromeDriver location
	public static final String chromeDriverPath = "/home/john/eclipse/chromedriver";
	
	// The URL to be used for the project.
	// NOTE: In future, it should be possible to set this via command-line, to allow for flexible calling from CI systems.
	// public static final String baseURL = "http://scotland-demo.trapezegroup.co.uk";
	public static final String baseURL = "http://www.travelinescotland.com";

	// Screen URLs.  Used for navigation checking.
	public static final String urlHomePage 		= "/?lang=en";
	public static final String titleHomePage 	= "Home | Traveline Scotland";
	
	public static final String urlJourneyPlannerPage = "/lts/#/travelInfo";
	public static final String urlDeparturesPage = "/liveDepartures";
	public static final String urlTimetablesPage = "/timetables";
	public static final String urlTravelUpdatesPage = "/updates";
	
	public static final String titleErrorDialog = "Error";
	
	// List of error messages used in the error dialog.
	public static final String errorJPFailed = "Journey planning failed.";
	
	
	
	// List of invalid date values to use for validation checking of each date field.
    public static final String[] invalidDates =	{"2017", "2017-02-29", "invalidvalue"};
    
    // Sample journeys using different styles of location.
    public static final String[] testLocations = {
    		"Dunoon, Argyll & Bute", 								// Locality
    		"Binnie Lane, Gourock",									// Street
    		"Buchanan Street Spt Subway Station",					// Subway station
    		"Edinburgh Airport (Edinburgh Trams)",					// Tram station
    		"Greenside, Stop Jc Leith Street",						// Bus stop
    		"Edinburgh Rail Station",								// Rail station
    		"Kirkwall (Northlink Ferry Terminal), Orkney Islands"	// Ferry stop
    		};
    }