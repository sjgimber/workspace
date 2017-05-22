package HelperFunctions;

import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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
	
	
	
	public void waitForAlert(WebDriver driver) throws InterruptedException
	{
		int i = 0;
		logger.logMessage("\tWaiting for alert...");
		while (i++ < 5)
		{
			try
			{
				driver.switchTo().alert();
				break;
			}
			catch (NoAlertPresentException e)
			{
				logger.logMessage("\tWaiting");
				Thread.sleep(1000);
				continue;
			}
		}
	}
	
	public String getAlertTitle(WebDriver driver)
	{
		driver.switchTo().alert();
		String title = driver.findElement(By.xpath("//h3")).getText();
		logger.logMessage("\tFound Alert Title:\t" + title);
		driver.switchTo().defaultContent();
		return title;
	}
	
	public boolean isErrorMessageShown(WebDriver driver)
	{
		// This detects if an error dialog is shown.  
		// This is done via a div that is made visible.
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
	
	public String getErrorTitle(WebDriver driver)
	{
		// Get the title from the displayed error dialog.
		String title = driver.findElement(By.xpath(".//*[@class='modal-header']/h3")).getText();
		logger.logMessage("\tAlert title:\t" + title);
		return title;
	}
	
	public String getErrorMessage(WebDriver driver)
	{
		// Get the title from the displayed error dialog.
		String message = driver.findElement(By.xpath(".//*[@class='modal-body']/span")).getText();
		logger.logMessage("\tAlert message:\t" + message);
		return message;
	}
	
	public void closeErrorMessage(WebDriver driver)
	{
		// Close the error message dialog by clicking on the <Close> button.
		logger.logMessage("\tClosing alert dialog.");
		driver.findElement(By.xpath(".//*[@class='modal-footer']/button")).click();
	}
}
