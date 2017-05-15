package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HelperFunctions.*;

public class homepagePOM 
{
	WebDriver driver;
	private logFunctions logger = new logFunctions();
	private helperFunctions lib = new helperFunctions();

	@FindBy(xpath="//input[@id='origin']") WebElement txtOrigin;								// Quick Journey Planner "Origin" field.
	@FindBy(xpath="//*[@id='origin']/../following-sibling::span/i") WebElement btnOriginClear;	// "Origin" field <clear> button.
	
	@FindBy(css="#destination") WebElement txtDestination;										// Quick Journey Planner "Destination" field.
	@FindBy(xpath=".//*[@id='destination']/../following-sibling::span/i") WebElement btnDestinationClear;	// "Destination" field <clear> button.
	
	@FindBy(xpath="html/body/div/div[3]/button") WebElement btnPlanMyJourney;					// Quick Journey Planner <Plan my journey> button.

	@FindBy(xpath="//div[@class='journey-plan-dialog-container']/iframe") WebElement frameQJP;	// QJP iframe.
	
	@FindBy(linkText="Journey Planner") WebElement menuJourneyPlanner;							// Menu: Journey Planner
	
	public homepagePOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setOriginNoSelect(String origin)
	{
		// This places a value into the Origin field, but DOESN'T verify the value from the location lookup list.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		logger.logMessage("\tSet From:\t" + origin);
		txtOrigin.clear();
		txtOrigin.sendKeys(origin);
		
		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}
	
	public void setOriginAndSelect(String origin)
	{
		// This places a value into the Origin field, then selects the value from the location lookup list.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		logger.logMessage("\tSet From:\t" + origin);
		lib.setLocationField(driver, txtOrigin, origin);

		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}	
	
	public String getOrigin()
	{
		// Return the content of the Origin field.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		String origin = txtOrigin.getAttribute("value");
		if (origin.equals(""))
		{
			// There is no value set, so this *may* have been reset.  Check the placeholder text instead.
			origin = txtOrigin.getAttribute("placeholder");
		}
		logger.logMessage("\tFound From:\t" + origin);

		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();

		return origin;	
	}
	
	public void clearOrigin()
	{
		// Click on the <x> button next to the Origin field.
		
		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		// Expected result: The field clears, and is replaced with the text "From".
		logger.logMessage("Click <x> for the Origin field...");
		btnOriginClear.click();

		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}

	public void setDestinationNoSelect(String destination)
	{
		// This places a value into the Destination field, but DOESN'T verify the value from the location lookup list.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		logger.logMessage("\tSet To:  \t" + destination);
		txtDestination.clear();
		txtDestination.sendKeys(destination);
		
		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}

	public void setDestinationAndSelect(String destination)
	{
		// This places a value into the Destination field, then selects the value from the location lookup list.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		logger.logMessage("\tSet To:  \t" + destination);
		lib.setLocationField(driver, txtDestination, destination);
		
		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}	

	public String getDestination()
	{
		// Return the content of the Destination field.

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		String destination = txtDestination.getAttribute("value");
		if (destination.equals(""))
		{
			// There is no value set, so this *may* have been reset.  Check the placeholder text instead.
			destination = txtDestination.getAttribute("placeholder");
		}
		logger.logMessage("\tFound To:\t" + destination);

		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();

		return destination;
	}
	
	public void clearDestination()
	{
		// Click on the <x> button next to the Destination field.
		// Expected result: The field clears, and is replaced with the text "To".

		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		logger.logMessage("Click <x> for the Destination field...");
		btnDestinationClear.click();
		
		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}
	
	public void setQJP_Locations(String origin, String destination)
	{
		setOriginAndSelect(origin);
		setDestinationAndSelect(destination);
	}
	
	public void clickQJP_PlanMyJourney() 
	{
		// The QJP is embedded in an iframe inside the main page.
		// Need to switch to this frame to access its content.
		driver.switchTo().frame(frameQJP);

		btnPlanMyJourney.click();

		// Now switch back to the main screen content.
		driver.switchTo().defaultContent();
	}
	
	public boolean clickMenu_JourneyPlanner()
	{
		// Select the <Journey Planner> menu option.
		// Expected result: The Journey Planner screen is shown.
		menuJourneyPlanner.click();
		return lib.isCorrectScreenShown(constants.urlJourneyPlannerPage, driver.getCurrentUrl());
	}
}
