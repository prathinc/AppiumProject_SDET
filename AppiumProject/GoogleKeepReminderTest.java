package AppiumProject;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class GoogleKeepReminderTest {
	private AppiumDriver<MobileElement> driver = null;
	private WebDriverWait wait = null;
  
@BeforeTest
public void beforeTest() throws MalformedURLException {
    // Set the Desired Capabilities
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("deviceId", "2fb5cf35");
    caps.setCapability("deviceName", "OnePlus 6");
    caps.setCapability("platformName", "Android");
    caps.setCapability("appPackage", "com.google.android.keep");
    caps.setCapability("appActivity", ".activities.BrowseActivity");
    caps.setCapability("noReset", true);

        // Initialize driver
    URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
    driver = new AndroidDriver<MobileElement>(appServer, caps);
    wait = new WebDriverWait(driver, 10);
}

  @Test
  public void googleAddNote() {
	  driver.findElementByAccessibilityId("New text note").click();
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.keep:id/menu_pin")));
	  //add note
	  MobileElement addNote = driver.findElement(By.id("com.google.android.keep:id/edit_note_text"));
	  addNote.sendKeys("Reminder Notes");
	  
	  
	  //add note title
	  driver.findElement(By.id("com.google.android.keep:id/title_editor_fragment")).click();
	  MobileElement addTitle = driver.findElement(By.id("com.google.android.keep:id/title_editor_fragment"));
	  Actions builder = new Actions(driver);
      builder.moveToElement(addTitle).pause(1000).click(addNote).pause(1000).sendKeys("Reminder Title");
      Action compAction = builder.build();
      compAction.perform();
      
	  //Click Reminder
	  driver.findElementByAccessibilityId("Reminder").click();
	  
	  //check if reminder appeared
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.keep:id/dialog_title")));
	  
	  //Click save
	  driver.findElement(By.id("com.google.android.keep:id/save")).click();
	  
	  //Go back
	  driver.findElementByAccessibilityId("Open navigation drawer").click();
	  
	  //check if note saved with reminder
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout/android.widget.TextView[1]")));
	  Assert.assertTrue(driver.findElement(By.id("com.google.android.keep:id/reminder_chip_text")).isEnabled(),"Reminder note added");  
	  }
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
