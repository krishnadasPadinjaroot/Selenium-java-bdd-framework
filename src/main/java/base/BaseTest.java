package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.ConfigReader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract  class BaseTest {

    public static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    /**
     * Launches the browser based on config.properties
     */
    public static void launchBrowser() {

        String browserName = ConfigReader.getProperty("browserName");

        logger.info("Initializing WebDriver");
        logger.info("Selected Browser: {}", browserName);

        try {

            switch (browserName.toLowerCase()) {

                case "firefox":
                    driver = new FirefoxDriver();
                    logger.info("Firefox browser launched successfully.");
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    logger.info("Edge browser launched successfully.");
                    break;

                case "chrome":
                default:


                    ChromeOptions options = new ChromeOptions();

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.password_manager_leak_detection", false);

                    options.setExperimentalOption("prefs", prefs);

                    options.addArguments("--incognito");

                    driver = new ChromeDriver(options);

                    logger.info("Chrome browser launched successfully.");

                    break;
            }

            logger.info("Maximizing browser window.");
            driver.manage().window().maximize();

            logger.info("Applying implicit wait of 10 seconds.");
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            String url = ConfigReader.getProperty("url");

            logger.info("Navigating to URL: {}", url);
            driver.get(url);

            logger.info("Application launched successfully.");

        } catch (Exception e) {

            logger.error("Failed to launch browser '{}'.", browserName, e);
            throw e;
        }
    }

    /**
     * Returns the current WebDriver instance.
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Closes the browser safely.
     */
    public static void closeBrowser() {

        if (driver != null) {

            logger.info("Closing browser.");

            driver.quit();
            driver = null;

            logger.info("Browser closed successfully.");
        } else {

            logger.warn("Driver instance is already null.");
        }
    }
}