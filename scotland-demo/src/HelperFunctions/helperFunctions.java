package HelperFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class helperFunctions 
{
	private logFunctions logger = new logFunctions();
	
	public boolean isCorrectScreenShown(String expectedScreenTitle, String actualScreenTitle)
	{

		logger.logMessage("Checking screen titles:");
		
		if (expectedScreenTitle.equals(actualScreenTitle))
		{
			// The correct screen is shown.
			logger.logMessage("\tCorrect screen shown: " + expectedScreenTitle);
			return true;
		}
		else
		{
			// Incorrect screen is shown.
			logger.logMessage("\tExpected:\t" + expectedScreenTitle);
			logger.logMessage("\tActual:  \t" + actualScreenTitle);
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
}
