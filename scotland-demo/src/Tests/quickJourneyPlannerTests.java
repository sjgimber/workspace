package Tests;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import HelperFunctions.*;
import Pages.homepagePOM;
import Pages.journeyPlannerPOM;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class quickJourneyPlannerTests 
{
	private WebDriver driver;
	private String baseUrl;
	private logFunctions logger = new logFunctions();
	private helperFunctions lib = new helperFunctions();
	
	@Before
	public void setUp() throws Exception 
	{
		// Before each test is run...
		System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		driver = new ChromeDriver();
		baseUrl = constants.baseURL;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception 
	{
		// After each test is completed...
		driver.close();
		
		// Log the test case end time in the debug trace.
	    logger.logTestEnd();
	}
	
	@Test
	public void test_QJP01_MissingMandatoryValues() throws InterruptedException 
	{
		logger.logTestStart(
				"Test QJP-01: Invalid: Missing Mandatory Values",
				"Summary: This test verifies that the QJP panel refuses to start with missing mandatory values.");
	    driver.get(baseUrl);
	    homepagePOM homePage = new homepagePOM(driver);

	    // Click on the <Plan my journey> button without providing any details.
	    // The Home page should still be shown.
	    homePage.clickQJP_PlanMyJourney();
	    
	    // There is no validation error shown for missing values.
	    // Expected behaviour: the Home screen is still shown.
	    assertTrue(lib.isCorrectScreenShown(constants.titleHomePage, driver.getTitle()));
	}
	
	@Test
	public void test_QJP02_PlanJourney() throws InterruptedException 
	{
		logger.logTestStart(
				"Test QJP-02: Plan Journey",
				"Summary: This test verifies that the QJP panel passes the locations through to the Journey Planner.");
	    driver.get(baseUrl);
	    homepagePOM homePage = new homepagePOM(driver);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    // Set the Origin and Destination values.
	    String origin = "Dunoon, Argyll & Bute";
	    String destination = "Glasgow, Glasgow";
	    logger.logMessage("QJP planning:");
	    homePage.setQJP_Locations(origin, destination);

	    // Click on <Plan My Journey>.
	    homePage.clickQJP_PlanMyJourney();
	    
	    // Expected behaviour: the Journey Planner screen is still shown.
	    assertTrue(lib.isCorrectScreenShown("travelInfo", driver.getCurrentUrl()));

	    // Verify that the journey was planned as requested.
	    logger.logMessage("Verifying details on Journey Planner...");
	    assertTrue(jpPage.getOrigin().equals(origin));
	    assertTrue(jpPage.getDestination().equals(destination));

	    // Verify that a number of journeys were planned.
	    assertTrue(jpPage.getJourneyCount() > 0);
	}
	
	@Test
	public void test_QJP03_PlanJourneysWithAllStopTypes() 
	{
		logger.logTestStart(
				"Test QJP-03: Plan Journey With All Stop Types",
				"Summary: This test verifies all stop types are used during journey planning.",
				"This ensures that the back-end location data has been processed correctly and is usable.");
	    homepagePOM homePage = new homepagePOM(driver);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    // Loop through the list of available test locations (which contains a stop, street, locality, etc.) and plan a journey between them.
	    String origin = constants.testLocations[0];
	    String destination = "";
	    
	    for (int locn = 1; locn < constants.testLocations.length; locn++)
	    {
	    	// Start on the Homepage.
		    logger.logMessage("QJP planning:");
		    driver.get(baseUrl);
		   
		    // Set the locations.
		    destination = constants.testLocations[locn];
		    homePage.setQJP_Locations(origin, destination);
	    	
		    // Click on <Plan My Journey>.
		    homePage.clickQJP_PlanMyJourney();

		    // Expected behaviour: the Journey Planner screen is shown.
		    assertTrue(lib.isCorrectScreenShown("travelInfo", driver.getCurrentUrl()));

		    // Verify that the journey was planned as requested.
		    logger.logMessage("Verifying details on Journey Planner...");
		    assertTrue(jpPage.getOrigin().equals(origin));
		    assertTrue(jpPage.getDestination().equals(destination));

		    // Verify that a number of journeys were planned.
		    assertTrue(jpPage.getJourneyCount() > 0);
		    
		    logger.logMessage("");
		}
	}
	
	@Test
	public void test_QJP04_PlanJourneyNoneFound() 
	{
		logger.logTestStart(
				"Test QJP-04: Plan Journey (No Journeys Found)",
				"Summary: This test verifies that the QJP panel passes the locations through to the Journey Planner.",
				"In this case, the journey can't be planned, so this should be handled correctly.");
	    driver.get(baseUrl);
	    homepagePOM homePage = new homepagePOM(driver);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    // Set the Origin and Destination values.
	    String origin = "Dunoon, Argyll & Bute";
	    String destination = "Dunoon, Argyll & Bute";
	    logger.logMessage("QJP planning:");
	    homePage.setQJP_Locations(origin, destination);

	    // Click on <Plan My Journey>.
	    homePage.clickQJP_PlanMyJourney();
	    
	    // Expected behaviour: the Journey Planner is shown, but no journeys are planned.
	    assertTrue(lib.isCorrectScreenShown("travelInfo", driver.getCurrentUrl()));

	    // Verify that the journey was planned as requested.
	    logger.logMessage("Verifying details on Journey Planner...");
	    assertTrue(jpPage.getOrigin().equals(origin));
	    assertTrue(jpPage.getDestination().equals(destination));

	    // Verify that a number of journeys were planned.
	    assertTrue(jpPage.getJourneyCount() == 0);
	}
}
