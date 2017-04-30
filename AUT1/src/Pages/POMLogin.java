package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMLogin {
	WebDriver driver;

	// Define our controls...
	@FindBy(id="username") WebElement txtName;			// Text Field: username
	@FindBy(id="password") WebElement txtPassword;		// Text Field: password
	@FindBy(linkText="Submit") WebElement btnSubmit;	// Button: Submit
	
	public void setUserName(String strUsername)
	{
		txtName.clear();
		txtName.sendKeys(strUsername);
	}
	
	public void setPassword(String strPassword)
	{
		txtPassword.clear();
		txtPassword.sendKeys(strPassword);
	}
	
	public POMLogin (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
