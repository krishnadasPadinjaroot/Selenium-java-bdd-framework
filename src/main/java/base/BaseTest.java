package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ConfigReader;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public static void launchBrowser() {

        String browserName = ConfigReader.getProperty("browserName");

//        boolean headless = Boolean.parseBoolean(
//                ConfigReader.getProperty("headless"));

        switch (browserName.toLowerCase()) {

            case "firefox":



                driver = new FirefoxDriver();

                break;

            case "edge":



                driver = new EdgeDriver();

                break;

            default:



                driver = new ChromeDriver();

                break;
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfigReader.getProperty("url"));

    }

    public static void closeBrowser() {

        if (driver != null) {
            driver.quit();
        }

    }

}