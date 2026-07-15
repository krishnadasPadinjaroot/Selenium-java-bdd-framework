package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.AllureUtil;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    /**
     * Executes before every scenario.
     * Launches the browser and opens the application.
     */
    @Before
    public void beforeScenario(Scenario scenario) {

        logger.info("====================================================");
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("====================================================");

        BaseTest.launchBrowser();
    }

    /**
     * Executes after every scenario.
     * Captures screenshot on failure and closes the browser.
     */
    @After
    public void afterScenario(Scenario scenario) {

        try {

            if (scenario.isFailed()) {

                logger.error("Scenario FAILED: {}", scenario.getName());

                byte[] screenshot = ((TakesScreenshot) BaseTest.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                // Attach to Cucumber Scenario to Allure report
                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );

                logger.info("Failure screenshot attached.");

            } else {

                logger.info("Scenario PASSED: {}", scenario.getName());
            }

        } finally {

            BaseTest.closeBrowser();
        }
    }
}