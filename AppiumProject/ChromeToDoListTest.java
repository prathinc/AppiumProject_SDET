package AppiumProject;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ChromeToDoListTest {
	AndroidDriver<MobileElement> driver = null;
	WebDriverWait wait = null;
	
  @Test
  public void addTasksList() throws InterruptedException {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Open the browser with the URL
		  String websiteUrl = "https://www.training-support.net/selenium";
		  driver.get(websiteUrl);
		  
		// Wait for the page to load
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View")));
		  
		  //Scroll TO DO List
	      driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"To-Do List\"))")).click();
		  //add task 1
	      MobileElement taskField1 = driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.EditText"));
	      taskField1.click();
	      taskField1.sendKeys("Add tasks to list");
	      //Save task 1
	      driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.Button")).click();
	      Thread.sleep(1000);
	      
	    //add task 2
	      MobileElement taskField2 = driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.EditText"));
	      taskField2.click();
	      taskField2.sendKeys("Get number of tasks");
	      //Save task 2
	      driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.Button")).click();
	      Thread.sleep(1000);
	      
	    //add task 3
	      MobileElement taskField3 = driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.EditText"));
	      taskField3.click();
	      taskField3.sendKeys("Clear the list");
	      //Save task 3
	      driver.findElement(By.xpath("//android.view.View/android.view.View[3]/android.view.View[1]/android.widget.Button")).click();
	      Thread.sleep(1000);
	      
	      String TodoTask1= driver.findElement(MobileBy.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[2]")).getText();
	  	  String TodoTask2= driver.findElement(MobileBy.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[3]")).getText();
	  	  String TodoTask3= driver.findElement(MobileBy.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[4]")).getText();
	  	  
	  	  Assert.assertEquals(TodoTask1, "Add tasks to list");
	  	  Assert.assertEquals(TodoTask2, "Get number of tasks");
	  	  Assert.assertEquals(TodoTask3, "Clear the list");
	  	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Add tasks to list\"))")).click();
	  	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Get number of tasks\"))")).click();
	  	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Clear the list\"))")).click();
	  	  
	  	  //delete tasks
	  	driver.findElement(MobileBy.xpath("//android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
	  	  
	      
	      
	  }
  @BeforeTest
  public void beforeTest() throws MalformedURLException {
	  DesiredCapabilities caps = new DesiredCapabilities();
	  caps.setCapability("deviceId", "2fb5cf35");
      caps.setCapability("deviceName", "OnePlus 6");
	  caps.setCapability("platformName", "Android");
	  caps.setCapability("appPackage", "com.android.chrome");
	  caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
	  caps.setCapability("noReset", true);

	  // Instantiate Appium Driver
	  URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
	  driver = new AndroidDriver<MobileElement>(appServer, caps);
	  wait = new WebDriverWait(driver, 10);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
