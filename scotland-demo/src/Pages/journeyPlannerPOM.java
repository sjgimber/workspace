package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HelperFunctions.helperFunctions;
import HelperFunctions.logFunctions;

public class journeyPlannerPOM 
{
	WebDriver driver;
	private logFunctions logger = new logFunctions();
	private helperFunctions lib = new helperFunctions();
	
	@FindBy(xpath=".//*[@id='origin']") WebElement txtOrigin;										// "Origin" field.
	@FindBy(xpath=".//*[@id='origin']/../following-sibling::span/i") WebElement btnOriginClear;		// "Origin" field <clear> button.
	
	@FindBy(xpath=".//*[@id='destination']") WebElement txtDestination;										// "Destination" field.
	@FindBy(xpath=".//*[@id='destination']/../following-sibling::span/i") WebElement btnDestinationClear;	// "Destination" field <clear> button.

	

	public journeyPlannerPOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setOrigin(String location)
	{
		// Set the content of the Origin field.
		logger.logMessage("\tFrom:\t" + location);
		txtOrigin.clear();
		txtOrigin.sendKeys(location);
	}
	
	public String getOrigin()
	{
		// Retrieve the location value in the Origin field.
		String origin = txtOrigin.getAttribute("value");
		logger.logMessage("\tFound From:\t" + origin);
		return origin;
	}
	
	public void clearOrigin()
	{
		btnOriginClear.click();
	}
	
	public void setDestination(String location)
	{
		// Set the content of the Destination field.
		logger.logMessage("\tTo:\t" + location);
		txtDestination.clear();
		txtDestination.sendKeys(location);
	}
	
	public String getDestination()
	{
		// Retrieve the location value in the Destination field.
		String destination = txtDestination.getAttribute("value");
		logger.logMessage("\tFound To:\t" + destination);
		return destination;
	}
	
	public void clearDestination() 
	{
		btnDestinationClear.click();
	}
	
	public Integer getJourneyCount()
	{
		// Determine the number of matching journeys found.  These are located in the Journeys Found panel on the left.
		int journeysFound = driver.findElements(By.xpath("//*[@class='journey-plan']")).size(); 
		logger.logMessage("\tJourneys:\t" + journeysFound);
		return journeysFound;
	}
}
