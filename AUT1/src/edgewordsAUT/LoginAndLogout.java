/**
 * 
 */
package edgewordsAUT;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import Pages.*
;
public class LoginAndLogout {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Class startup...");
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Class teardown...");
	}


	@Before
	public void setUp() throws Exception {
		System.out.println("Pre-test setup...");
		
		/*
		System.setProperty("webdriver.gecko.driver", "/home/john/eclipse/geckodriver");

		// Required for multithreaded firefox workaround.
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.tabs.remote.autostart", false);
		profile.setPreference("browser.tabs.remote.autostart.1", false);
		profile.setPreference("browser.tabs.remote.autostart.2", false);
		profile.setPreference("browser.tabs.remote.force-enable", "false");
		driver = new FirefoxDriver(profile); */

		System.setProperty("webdriver.chrome.driver", "/home/john/eclipse/chromedriver");
		driver = new ChromeDriver();
		
		baseUrl = "http://www.edgewordstraining.co.uk/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@After
	public void tearDown() throws Exception {
		System.out.println("Teardown...");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@Test
	public void test2() {
		System.out.println("Test 2");
	}

	@Test
	public void test1() throws InterruptedException {
		System.out.println("Test 1");
	    driver.get(baseUrl + "webdriver/");
	    driver.findElement(By.partialLinkText("Login")).click();;
	    System.out.print("Waiting...");
	    Thread.sleep(5000);
	    System.out.println("Done");

	    POMLogin loginPage = new POMLogin(driver);
	    loginPage.setUserName("EWordsJBSM");
	    loginPage.setPassword("Millichamp1");
	    
	    
	}
}
