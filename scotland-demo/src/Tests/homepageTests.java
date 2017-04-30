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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class homepageTests 
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

	private boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
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

	    // Set the Origin and Destination values.
	    String origin = "Dunoon, Argyll & Bute";
	    String destination = "Glasgow, Glasgow";
	    homePage.setQJP_Locations(origin, destination);
	    
	    // Click on <Plan My Journey>.
	    homePage.clickQJP_PlanMyJourney();
	    
	    // There is no validation error shown for missing values.
	    // Expected behaviour: the Home screen is still shown.
	    assertTrue(lib.isCorrectScreenShown(constants.urlJourneyPlannerPage, driver.getCurrentUrl()));

	    // Verify that the journey was planned as requested.
	    
	}
}
