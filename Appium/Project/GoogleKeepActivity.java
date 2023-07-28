package liveProject;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GoogleKeepActivity {
    AndroidDriver driver;
    WebDriverWait wait;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Desired Capabilities
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.google.android.keep");
        options.setAppActivity(".activities.BrowseActivity");
        options.noReset();

        // Server Address
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Test method
    @Test
    public void addKeepNotes() {

        // find the elements and do necessary actions
        driver.findElement(AppiumBy.accessibilityId("New text note")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("editable_title"))).click();
        driver.findElement(AppiumBy.id("editable_title")).sendKeys("Sample Reminder!!");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("edit_note_text"))).click();
        driver.findElement(AppiumBy.id("edit_note_text")).sendKeys("Remind me to text Bob");
        driver.findElement(AppiumBy.accessibilityId("Navigate up")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("browse_note_interior_content")));
        String title = driver.findElement(AppiumBy.id("index_note_title")).getText();
        String note = driver.findElement(AppiumBy.id("index_note_text_description")).getText();

        // Assertions of text
        Assert.assertEquals(title, "Sample Reminder!!");
        Assert.assertEquals(note, "Remind me to text Bob");

    }

    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}
