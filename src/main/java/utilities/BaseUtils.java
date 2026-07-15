package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    protected final Logger logger = LogManager.getLogger(getClass());

    public BaseUtils() {

        driver = BaseTest.driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        actions = new Actions(driver);

        js = (JavascriptExecutor) driver;
    }
}