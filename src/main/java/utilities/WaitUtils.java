package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(WaitUtils.class);

    public WaitUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Wait until element is visible.
     */
    public WebElement waitForVisibility(By locator) {

        logger.info("Waiting for visibility of {}", locator);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until element is clickable.
     */
    public WebElement waitForClickable(By locator) {

        logger.info("Waiting for element to be clickable {}", locator);

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait until element is present.
     */
    public WebElement waitForPresence(By locator) {

        logger.info("Waiting for presence of {}", locator);

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait until element becomes invisible.
     */
    public boolean waitForInvisibility(By locator) {

        logger.info("Waiting for invisibility of {}", locator);

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait until text is present.
     */
    public boolean waitForText(By locator, String text) {

        logger.info("Waiting for text '{}' in {}", text, locator);

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait until attribute contains value.
     */
    public boolean waitForAttribute(By locator,
                                    String attribute,
                                    String value) {

        logger.info("Waiting for attribute {}={} in {}",
                attribute, value, locator);

        return wait.until(
                ExpectedConditions.attributeContains(
                        locator,
                        attribute,
                        value));
    }

    /**
     * Wait for page title.
     */
    public boolean waitForTitle(String title) {

        logger.info("Waiting for page title: {}", title);

        return wait.until(
                ExpectedConditions.titleContains(title));
    }

    /**
     * Wait for URL.
     */
    public boolean waitForUrl(String url) {

        logger.info("Waiting for URL: {}", url);

        return wait.until(
                ExpectedConditions.urlContains(url));
    }

    /**
     * Wait for alert.
     */
    public boolean waitForAlert() {

        logger.info("Waiting for alert.");

        return wait.until(
                ExpectedConditions.alertIsPresent()) != null;
    }

    /**
     * Wait for page load.
     */
    public void waitForPageLoad() {

        logger.info("Waiting for page load.");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

    /**
     * Fluent wait.
     */
    public WebElement fluentWait(By locator) {

        logger.info("Applying FluentWait for {}", locator);

        FluentWait<WebDriver> fluentWait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(30))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(driver ->
                driver.findElement(locator));
    }

    /**
     * Wait for custom duration.
     */
    public void waitForSeconds(int seconds) {

        try {

            logger.info("Waiting for {} seconds", seconds);

            Thread.sleep(Duration.ofSeconds(seconds).toMillis());

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            logger.error("Interrupted while waiting.", e);
        }
    }

    /**
     * Wait until number of windows.
     */
    public boolean waitForNumberOfWindows(int count) {

        logger.info("Waiting for {} browser windows.", count);

        return wait.until(
                ExpectedConditions.numberOfWindowsToBe(count));
    }

    /**
     * Wait until element is selected.
     */
    public boolean waitForSelection(By locator) {

        logger.info("Waiting for element selection {}", locator);

        return wait.until(
                ExpectedConditions.elementToBeSelected(locator));
    }

    /**
     * Wait until element is refreshed.
     */
    public WebElement waitForRefresh(By locator) {

        logger.info("Waiting for refreshed element {}", locator);

        return wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOfElementLocated(locator)));
    }
}