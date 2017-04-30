package HelperFunctions;

public class constants 
{
	// Constant values used across the project.
	
	// ChromeDriver location
	public static final String chromeDriverPath = "/home/john/eclipse/chromedriver";
	
	// The URL to be used for the project.
	// NOTE: In future, it should be possible to set this via command-line, to allow for flexible calling from CI systems.
	public static final String baseURL = "http://scotland-demo.trapezegroup.co.uk";

	// Screen URLs.  Used for navigation checking.
	public static final String urlHomePage 		= "/?lang=en";
	public static final String titleHomePage 	= "Home | Traveline Scotland";
	
	public static final String urlJourneyPlannerPage = "/travelInfo";
	public static final String urlDeparturesPage = "/liveDepartures";
	public static final String urlTimetablesPage = "/timetables";
	public static final String urlTravelUpdatesPage = "/updates";
	
	// List of invalid date values to use for validation checking of each date field.
    public static final String[] invalidDates =	{"2017", "2017-02-29", "invalidvalue"};
}