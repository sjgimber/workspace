package HelperFunctions;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class helperFunctions 
{
	private logFunctions logger = new logFunctions();
	
	public boolean isCorrectScreenShown(String expectedScreenValue, String actualScreenValue)
	{
		// Check that the correct screen is shown.
		// This is a little complicated by the fact that multiple screens have the same title,
		// so the comparison is done either on URL content or on title, depending on the values fed in.
		
		logger.logMessage("Checking screen URL / Title:");
		
		if (actualScreenValue.contains(expectedScreenValue))
		{
			// The correct screen is shown.
			logger.logMessage("\tCorrect screen shown: " + actualScreenValue);
			return true;
		}
		else
		{
			// Incorrect screen is shown.
			logger.logMessage("\tExpected:\t" + expectedScreenValue);
			logger.logMessage("\tActual:  \t" + actualScreenValue);
			return false;
		}
	}

	

	public void setLocationField(WebDriver driver, WebElement control, String location)
	{
		// Using the browser control, set the location control to the selected location value (and click it).
		control.clear();
		control.sendKeys(location);
		
		// Drop-down list is shown.  Select the closest match from it.
		driver.findElement(By.linkText(location)).click();
	}
	
	public boolean isElementPresent(WebDriver driver, By by)
	{
		try
		{
			driver.findElement(by);
			logger.logMessage("\tFound control");
			return true;
		}
		catch (NoSuchElementException e)
		{
			logger.logMessage("\tNOT Found control");
			return false;
		}
	}
}
