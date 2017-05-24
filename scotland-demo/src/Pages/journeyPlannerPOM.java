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

	@FindBy(xpath=".//*[@name='time']") WebElement txtTime;											// Time field.
	@FindBy(xpath=".//*[@class='icon-time']") WebElement btnTime;									// Time button.
	
	
	
	@FindBy(xpath=".//*[@class='modal-dialog']") WebElement dlgPopup;								// Popup dialog shown over the JP screen.

	
	
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
		logger.logMessage("Click <x> for the Origin field...");
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
		logger.logMessage("Click <x> for the Destination field...");
		btnDestinationClear.click();
	}
	
	public void setTimeByKeyboard(String time)
	{
		// Set the content of the Time field (in the format "hh:mm").  
		logger.logMessage("\tSet Time:  \t" + time);
		txtTime.clear();
		txtTime.sendKeys(time);
	}
	
	public void setTimeByPicker(String time)
	{
		// Set the content of the Time field (in the format "hh:mm").
		// NOTE: 
		//		The hh component goes in 1h increments, format h:mm.
		//		The mm component increases in 5m increments.
		String[] parts = time.split(":");
		String hour = parts[0];
		String minute = parts[1];
		
		logger.logMessage("\tSet Hour:\t" + hour);
		btnTime.click();
		String xpath = ".//*[@class='hour' and text()='" + hour + ":00']";
		driver.findElement(By.xpath(xpath)).click();
		
		logger.logMessage("\tSet Minute:\t" + minute);
		xpath = ".//*[@class='minute' and text()='" + hour + ":" + minute + "']";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	public String getTime()
	{
		// Retrieve the time value in the Time field.
		String time = txtTime.getAttribute("value");
		logger.logMessage("\tFound Time:\t" + time);
		return time;
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
	
	public boolean isErrorMessageShown()
	{
		// This detects if an error dialog is shown.  
		// On the JP, this is done via a div that is made visible.
		boolean present;
		try
		{
			driver.findElement(By.xpath(".//*[@class='modal-dialog']"));
			logger.logMessage("\tAlert dialog shown.");
			present = true;
		}
		catch (NoSuchElementException e)
		{
			logger.logMessage("\tAlert dialog not shown.");
			present = false;
		}
		return present;
	}
}
