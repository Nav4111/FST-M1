package stepDefinitions;

import io.cucumber.java.AfterAll;

public class CloseBrowser extends BaseClass {

    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }
}
