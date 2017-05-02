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
	public void test() throws InterruptedException 
	{
		logger.logTestStart(
				"Test JP-01: Plan Journey",
				"Summary: This test verifies that the QJP panel passes the locations through to the Journey Planner.");
	    driver.get(baseUrl);
	    journeyPlannerPOM jpPage = new journeyPlannerPOM(driver);
	    
	    jpPage.setOrigin("Cooee");
	    Thread.sleep(5000);
	    jpPage.clearOrigin();
	    Thread.sleep(5000);
	}

}
