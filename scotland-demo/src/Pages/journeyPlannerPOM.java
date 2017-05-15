package Pages;

import java.util.NoSuchElementException;

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
		// NOTE: This doesn't select a value from the location lookup list.
		logger.logMessage("\tSet From:\t" + location);
		txtOrigin.clear();
		txtOrigin.sendKeys(location);
	}

	public void setOriginAndSelect(String location)
	{
		// Set the content of the Origin field.  
		// NOTE: This also selects the value from the location lookup list.
		logger.logMessage("\tSet From:\t" + location);
		lib.setLocationField(driver, txtOrigin, location);
	}

	public String getOrigin()
	{
		// Retrieve the location value in the Origin field.
		String origin = txtOrigin.getAttribute("value");
		if (origin.equals(""))
		{
			// There is no value set, so this *may* have been reset.  Check the placeholder text instead.
			origin = txtOrigin.getAttribute("placeholder");
		}
		logger.logMessage("\tFound From:\t" + origin);
		return origin;
	}
	
	public void clearOrigin()
	{
		// Click on the <x> button next to the Origin field.
		// Expected behaviour: The field is cleared, and replaced with the word "From".
		btnOriginClear.click();
	}
	
	public void setDestination(String location)
	{
		// This places a value into the Destination field, then selects the value from the location lookup list.
		logger.logMessage("\tSet To:  \t" + location);
		lib.setLocationField(driver, txtDestination, location);
	}
	
	public void setDestinationAndSelect(String location)
	{
		// Set the content of the Destination field.  
		// NOTE: This also selects the value from the location lookup list.
		logger.logMessage("\tSet To:  \t" + location);
		lib.setLocationField(driver, txtDestination, location);
	}

	public String getDestination()
	{
		// Retrieve the location value in the Destination field.
		String destination = txtDestination.getAttribute("value");
		if (destination.equals(""))
		{
			// There is no value set, so this *may* have been reset.  Check the placeholder text instead.
			destination = txtDestination.getAttribute("placeholder");
		}
		logger.logMessage("\tFound To:\t" + destination);
		return destination;
	}
	
	public void clearDestination() 
	{
		// Click on the <x> button next to the Destination field.
		// Expected behaviour: The field is cleared, and replaced with the word "To".
		btnDestinationClear.click();
	}
	
	// --------------------------------------------------------------------------------
	
	public void setJP_Locations(String origin, String destination)
	{
		// This sets both origin and destination fields, selecting from the location lookup fields as well.
		setOriginAndSelect(origin);
		setDestinationAndSelect(destination);
	}
		
	public Integer getJourneyCount()
	{
		// Determine the number of matching journeys found.  These are located in the Journeys Found panel on the left.
		int journeysFound;
		
		try
		{
			// In the absence of "no journeys found", this is "journeys found".  Get the number of journeys.
			
			journeysFound = driver.findElements(By.xpath("//*[@class='journey-plan']")).size();
		}
		catch  (NoSuchElementException e)
		{
			// No journeys found.
			journeysFound = 0;
		}
		
		logger.logMessage("\tFound Journeys:\t" + journeysFound);
		return journeysFound;
	}
}
