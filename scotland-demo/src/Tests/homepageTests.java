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
	public void test_nav01_NavigateTo_JourneyPlanner() 
	{
		logger.logTestStart(
				"Test Nav-01: Navigate to: Journey Planner",
				"Summary: This verifies that the user can navigate to the Journey Planner from the menu bar.");
	    driver.get(baseUrl);
	    homepagePOM homePage = new homepagePOM(driver);

	    assertTrue(homePage.clickMenu_JourneyPlanner());
	}
}
