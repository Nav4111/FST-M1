package stepDefinitions;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BaseClass {

    Actions builder = new Actions(driver);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.firefoxdriver().setup();
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "NULL");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("user is on CRM login page")
    public void loginToCRMPage() {
        //Open web page
        driver.get("http://alchemy.hguy.co/crm");
    }

    @Then("user reads the title and verify text")
    public void verifyTitle() {
        String pageTitle = driver.getTitle();
        System.out.println("Page title is: " + pageTitle);
        Assert.assertEquals(pageTitle, "SuiteCRM");
    }

    @Then("user gets the url of header image")
    public void getURLImage() {
        WebElement headerImg = driver.findElement(By.xpath("//img[@alt='SuiteCRM']"));
        System.out.println("URL of the header image: " + headerImg.getAttribute("src"));
    }

    @Then("user gets the first copyright text in the footer")
    public void getCopyrightText() {
        WebElement footer1 = driver.findElement(By.xpath("//div[@class='p_login_bottom']/a[1]"));
        System.out.println("The first copyright text in the footer: " + footer1.getText());
    }

    @When("user enters credentials to login")
    public void loginWithCredntials() {
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
        //Click Login
        driver.findElement(By.id("bigbutton")).click();
    }

    @Then("verify the homepage")
    public void verifyHomePage() {
        int homeElemSize = driver.findElements(By.id("moduleTab_Home")).size();
        Assert.assertTrue(homeElemSize > 0);
        System.out.println("Home Page has opened");
    }

    @Then("user gets the color of navigation menu")
    public void getNavigationMenuColor() {
        WebElement navBar = driver.findElement(By.xpath("//*[@class='navbar navbar-inverse navbar-fixed-top']"));
        String rgb_val = navBar.getCssValue("color");
        String hex_val = Color.fromString(rgb_val).asHex();
        System.out.println(rgb_val + "," + hex_val);
        Assert.assertEquals(hex_val, "#534d64");
    }

    @Then("user finds and selects Activities menu item")
    public void selectActivities() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("grouptab_3")));
        String tabName = driver.findElement(By.id("grouptab_3")).getText();
        Assert.assertEquals(tabName, "ACTIVITIES");
    }

    @Then("user reads additional info about lead")
    public void readAddtnlInfo() throws InterruptedException {
        // select and click on Sales->lead option
        WebElement sales_tab = driver.findElement(By.id("grouptab_0"));
        builder.moveToElement(sales_tab).build().perform();
        driver.findElement(By.xpath("//*[@id='grouptab_0']/following-sibling::ul/li/a[@id='moduleTab_9_Leads']")).click();

        // find and click on additional details icon
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[1]/td/span/span[@title='Additional Details']"))).click();
        Thread.sleep(1000);

        // get phone number from pop up
        String phoneNum = driver.findElement(By.xpath("//*[@id='ui-id-5']/span[@class='phone']")).getText();
        System.out.println("Phone Number of selected lead: " + phoneNum);
    }

    @Then("user navigates to accounts and print the table contents")
    public void getNamesOfAccounts() {
        // navigates to sales->accounts page
        WebElement sales_tab = driver.findElement(By.id("grouptab_0"));
        builder.moveToElement(sales_tab).build().perform();
        driver.findElement(By.xpath("//*[@id='grouptab_0']/following-sibling::ul/li/a[@id='moduleTab_9_Accounts']")).click();

        // wait for the table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='list view table-responsive']")));

        System.out.println("The names of first 5 odd-numbered ordered accounts: ");
        for (int i = 1; i <= 5 ; i++) {
            String row = "//table[@class='list view table-responsive']/tbody/tr[@class='oddListRowS1']["+i+"]/td[@type='name']/b/a";
            String names = driver.findElement(By.xpath(row)).getText();
            System.out.println(names);
        }
    }

    @Then("user navigates to leads page and print their fullnames and usernames")
    public void getNamesOfLeads() {
        // select and click on Sales->lead option
        WebElement sales_tab = driver.findElement(By.id("grouptab_0"));
        builder.moveToElement(sales_tab).build().perform();
        driver.findElement(By.xpath("//*[@id='grouptab_0']/following-sibling::ul/li/a[@id='moduleTab_9_Leads']")).click();

        // wait for the table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='list view table-responsive']")));
        System.out.println("The fullnames and usernames of first 10 leads: ");
        for (int i = 1; i <= 10 ; i++) {
            String name = "//table/tbody/tr["+i+"]/td[@type='name']/b/a";
            String user = "//table/tbody/tr["+i+"]/td[@field='assigned_user_name']/a";
            String fnames = driver.findElement(By.xpath(name)).getText();
            String unames = driver.findElement(By.xpath(user)).getText();
            System.out.println(fnames + "   " + unames);
        }
    }

}
