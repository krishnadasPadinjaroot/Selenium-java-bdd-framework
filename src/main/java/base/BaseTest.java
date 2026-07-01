package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ConfigReader;

import java.time.Duration;


public class BaseTest {

    public static WebDriver driver;

    /**
     * Launches the browser based on config.properties
     */
    public static void launchBrowser() {

        String browserName = ConfigReader.getProperty("browserName");

        switch (browserName.toLowerCase()) {

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfigReader.getProperty("url"));
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
            driver.quit();
            driver = null;
        }
    }
}