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

public class ChromeLogin2Test {
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
	      
	      
		  
		  //Scroll to Login Form
	      driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true)).scrollIntoView(text(\"Login Form\"))")).click();
	      wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.EditText[@text='']")));

			driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("admin");
			
			driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("password");
			
			driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
			
			String loginSuccess = driver.findElementByXPath("//android.view.View[@text='Welcome Back, admin']").getText();
			
			Assert.assertEquals(loginSuccess, "Welcome Back, admin","Login successful");
			
			driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").click();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").clear();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='username']").sendKeys("Admin");
			
			driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").click();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").clear();
			driver.findElementByXPath("//android.widget.EditText[@resource-id='password']").sendKeys("Password");
			
			driver.findElementByXPath("//android.widget.Button[@text='Log in']").click();
			
			String loginFailure = driver.findElementByXPath("//android.view.View[@text='Invalid Credentials']").getText();
			
			Assert.assertEquals(loginFailure, "Invalid Credentials","Login failed");

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
