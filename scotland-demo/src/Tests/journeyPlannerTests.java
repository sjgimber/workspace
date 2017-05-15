package Tests;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import HelperFunctions.*;
import Pages.homepagePOM;
import Pages.journeyPlannerPOM;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class journeyPlannerTests 
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
		baseUrl = constants.baseURL + constants.urlJourneyPlannerPage;
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
	public void test_JP01_From_ClearField()  
	{
		logger.logTestStart(
				"Test JP-01: From: Clear Field",
				"Summary: This test verifies that the Journey Planner From field clears when the <x> button is clicked.");
	    driver.get(baseUrl);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    // Set the Origin and Destination values. 
	    String origin = "Dunoon, Argyll & Bute";
	    String destination = "Fair Isle Airport, Shetland Islands";
	    logger.logMessage("Journey planning:");
	    jpPage.setJP_Locations(origin, destination);

	    // Click on <x> for the From field.
	    jpPage.clearOrigin();
	    
	    // Expected behaviour: the From field now contains the text "From".
	    assertTrue(jpPage.getOrigin().equals("From"));
	}
	
	@Test
	public void test_JP02_To_ClearField()  
	{
		logger.logTestStart(
				"Test JP-02: To: Clear Field",
				"Summary: This test verifies that the Journey Planner To field clears when the <x> button is clicked.");
	    driver.get(baseUrl);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    // Set the Origin and Destination values. 
	    String origin = "Dunoon, Argyll & Bute";
	    String destination = "Fair Isle Airport, Shetland Islands";
	    logger.logMessage("Journey planning:");
	    jpPage.setJP_Locations(origin, destination);

	    // Click on <x> for the To field.
	    jpPage.clearDestination();
	    
	    // Expected behaviour: the To field now contains the text "To".
	    assertTrue(jpPage.getDestination().equals("To"));
	}	
}
