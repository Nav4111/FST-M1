package liveProject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static examples.W3CActionsBase.doSwipe;

public class ChromeActivity {
    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        // server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // driver initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open selenium page
        driver.get("https://www.training-support.net/selenium");
    }

    @Test
    public void webAppTest() throws InterruptedException {
        Dimension dims = driver.manage().window().getSize();

        // Wait for the page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Get Started!']")));

        // scroll to end of the page, Set start and end points
        Point start = new Point((int) (dims.width * 0.5), (int) (dims.height * 0.8));
        Point end = new Point((int) (dims.width * 0.5), (int) (dims.height * 0.6));
        // Perform swipe on slider
        doSwipe(driver, start, end, 80);

        // locate element and click it
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.view.View[contains(@text,'To-Do List')]"))).click();

        // wait for the page to load
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")));

        WebElement addTaskInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']"));
        WebElement addTaskBtn = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']"));
        WebElement clearList = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@text,'Clear List')]"));

        clearList.click();

        Thread.sleep(2000);
        addTaskInput.click();
        addTaskInput.sendKeys("task1");
        addTaskBtn.click();

        Thread.sleep(2000);
        addTaskInput.click();
        addTaskInput.sendKeys("task2");
        addTaskBtn.click();

        Thread.sleep(2000);
        addTaskInput.click();
        addTaskInput.sendKeys("task3");
        addTaskBtn.click();

        Thread.sleep(2000);
        List<WebElement> tasksList = driver.findElements(AppiumBy.xpath("//android.view.View[@resource-id='tasksList']/android.view.View"));
        int count = tasksList.size();
        System.out.println("Number of tasks in the list: " + count);

        for (WebElement task : tasksList) {
                task.click();
                Thread.sleep(1000);
        }

        Thread.sleep(2000);
        clearList.click();

        // Assertions
        wait.until(ExpectedConditions.invisibilityOfAllElements(tasksList));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
