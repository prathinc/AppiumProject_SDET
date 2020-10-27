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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class GoogleTaskTest {
	private AppiumDriver<MobileElement> driver = null;
	private WebDriverWait wait = null;
  
@BeforeTest
public void beforeTest() throws MalformedURLException {
    // Set the Desired Capabilities
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("deviceId", "2fb5cf35");
    caps.setCapability("deviceName", "OnePlus 6");
    caps.setCapability("platformName", "Android");
    caps.setCapability("appPackage", "com.google.android.apps.tasks");
    caps.setCapability("appActivity", ".ui.TaskListsActivity");
    caps.setCapability("noReset", true);

        // Initialize driver
    URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
    driver = new AndroidDriver<MobileElement>(appServer, caps);
    wait = new WebDriverWait(driver, 10);
}

  @Test
  public void googleTasks1() {
	  driver.findElementByAccessibilityId("Create new task").click();
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
	  MobileElement addTask = driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_title"));
	  //add Task1
	  addTask.sendKeys("Complete Activity with Google Tasks");
	  //save Task1
	  driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_done")).click();
	  //check if task is saved
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.google.android.apps.tasks:id/task_item_layout")));
	  Assert.assertTrue(driver.findElement(By.id("com.google.android.apps.tasks:id/task_item_layout")).isDisplayed(), "Task 1 is added successfully");
	  }
  
  @Test
  public void googleTask2() {
	  driver.findElementByAccessibilityId("Create new task").click();
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
	  MobileElement addTask = driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_title"));
	  //add Task2
	  addTask.sendKeys("Complete Activity with Google Keep");
	  //save Task2
	  driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_done")).click();
	  //check if task is saved
	  MobileElement task2View = driver.findElementByAccessibilityId("Complete Activity with Google Keep");
	  Assert.assertTrue(task2View.isDisplayed(), "Task 2 is added successfully");  
  }
  
  @Test
  public void googleTask3() {
	  driver.findElementByAccessibilityId("Create new task").click();
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.google.android.apps.tasks:id/add_task_title")));
	  MobileElement addTask = driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_title"));
	  //add Task3
	  addTask.sendKeys("Complete the second Activity Google Keep");
	  //save Task3
	  driver.findElement(By.id("com.google.android.apps.tasks:id/add_task_done")).click();
	  //check if task is saved
	  MobileElement task3View = driver.findElementByAccessibilityId("Complete the second Activity Google Keep");
	  Assert.assertTrue(task3View.isDisplayed(), "Task 3 is added successfully");  
  }
  @Test
  public void checkTasksAdded() {
	  List<MobileElement> tasks = driver.findElements(By.id("com.google.android.apps.tasks:id/task_name"));
	  int taskNum = 0;
	  for (MobileElement i:tasks) {
		 System.out.println(i.getText()); 
		 taskNum +=1;
	  }
	  Assert.assertEquals(taskNum, 3,"All 3 tasks added successfully");		
  }
	
  @AfterTest
  public void afterMethod() {
  }

}
