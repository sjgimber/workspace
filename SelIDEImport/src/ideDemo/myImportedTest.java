package ideDemo;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class myImportedTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.out.println("Setting up...");
	System.setProperty("webdriver.gecko.driver", "/home/john/eclipse/geckodriver");
	
	// Required for multithreaded firefox workaround.
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("browser.tabs.remote.autostart", false);
    profile.setPreference("browser.tabs.remote.autostart.1", false);
    profile.setPreference("browser.tabs.remote.autostart.2", false);
    profile.setPreference("browser.tabs.remote.force-enable", "false");
    driver = new FirefoxDriver(profile);
    
    baseUrl = "http://www.edgewordstraining.co.uk/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testExport1() throws Exception {
	  System.out.println("Begin test...");
	String newpath = baseUrl + "webdriver/";
	System.out.println(newpath);
    driver.get(baseUrl + "webdriver/");
    driver.findElement(By.cssSelector("li.last > a > span")).click();
    driver.findElement(By.xpath("//div[@id='menu']/ul/li[2]/a/span")).click();
    
    System.out.print("Assertion: paragraph1 is present...");
    try 
    {
      assertTrue(isElementPresent(By.id("paragraph1"))); 
    } catch (Error e) 
    {
      verificationErrors.append(e.toString());
    }
    System.out.println("OK");
    
    System.out.print("Asserting: div1 present...");
    assertTrue(isElementPresent(By.id("div1")));
    System.out.println("OK");
    
    driver.findElement(By.linkText("Home")).click();
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
