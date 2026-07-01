package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.AllureUtil;


public class Hooks {

    /**
     * Executes before every scenario.
     * Launches the browser and opens the application.
     */
    @Before
    public void beforeScenario() {

        BaseTest.launchBrowser();

    }

    /**
     * Executes after every scenario.
     * 1. Captures screenshot if the scenario fails.
     * 2. Closes the browser irrespective of test result.
     */
    @After
    public void afterScenario(Scenario scenario) {

        try {

            // Check whether the current scenario has failed
            if (scenario.isFailed()) {

                // Capture screenshot as byte array
                byte[] screenshot = ((TakesScreenshot) BaseTest.driver)
                        .getScreenshotAs(OutputType.BYTES);

                // Attach screenshot to Allure Report
                AllureUtil.attachScreenshot(screenshot);

            }

        } finally {

            // Always close the browser
            // finally block ensures cleanup even if an exception occurs
            BaseTest.closeBrowser();

        }

    }

}