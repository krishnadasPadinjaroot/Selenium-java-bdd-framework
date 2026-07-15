package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    private static final Logger logger =
            LogManager.getLogger(JavaScriptUtils.class);

    public JavaScriptUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    /**
     * Returns WebElement after wait.
     */
    private WebElement getElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Click using JavaScript.
     */
    public void jsClick(By locator) {

        try {

            logger.info("JavaScript clicking: {}", locator);

            js.executeScript(
                    "arguments[0].click();",
                    getElement(locator));

            logger.info("JavaScript click successful.");

        } catch (Exception e) {

            logger.error("Unable to perform JavaScript click.", e);
            throw e;
        }
    }

    /**
     * Scroll element into view.
     */
    public void scrollToElement(By locator) {

        try {

            logger.info("Scrolling to element: {}", locator);

            js.executeScript(
                    "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});",
                    getElement(locator));

        } catch (Exception e) {

            logger.error("Unable to scroll to element.", e);
            throw e;
        }
    }

    /**
     * Scroll to top.
     */
    public void scrollToTop() {

        logger.info("Scrolling to top.");

        js.executeScript("window.scrollTo(0,0);");
    }

    /**
     * Scroll to bottom.
     */
    public void scrollToBottom() {

        logger.info("Scrolling to bottom.");

        js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    /**
     * Scroll by X and Y.
     */
    public void scrollBy(int x, int y) {

        logger.info("Scrolling by X={} Y={}", x, y);

        js.executeScript(
                "window.scrollBy(arguments[0],arguments[1]);",
                x,
                y);
    }

    /**
     * Highlight element.
     */
    public void highlightElement(By locator) {

        try {

            logger.info("Highlighting element: {}", locator);

            js.executeScript(
                    "arguments[0].style.border='3px solid red';",
                    getElement(locator));

        } catch (Exception e) {

            logger.error("Unable to highlight element.", e);
            throw e;
        }
    }

    /**
     * Remove highlight.
     */
    public void removeHighlight(By locator) {

        js.executeScript(
                "arguments[0].style.border='';",
                getElement(locator));
    }

    /**
     * Set value using JavaScript.
     */
    public void setValue(By locator, String value) {

        try {

            logger.info("Setting value using JavaScript.");

            js.executeScript(
                    "arguments[0].value=arguments[1];",
                    getElement(locator),
                    value);

        } catch (Exception e) {

            logger.error("Unable to set value.", e);
            throw e;
        }
    }

    /**
     * Get page title.
     */
    public String getTitle() {

        return (String) js.executeScript("return document.title;");
    }

    /**
     * Get current URL.
     */
    public String getURL() {

        return (String) js.executeScript("return document.URL;");
    }

    /**
     * Get page ready state.
     */
    public String getReadyState() {

        return (String) js.executeScript(
                "return document.readyState;");
    }

    /**
     * Wait until page is completely loaded.
     */
    public void waitForPageLoad() {

        logger.info("Waiting for page to load.");

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(driver ->
                        js.executeScript("return document.readyState")
                                .equals("complete"));
    }

    /**
     * Refresh page using JavaScript.
     */
    public void refreshPage() {

        logger.info("Refreshing page using JavaScript.");

        js.executeScript("history.go(0)");
    }

    /**
     * Navigate back using JavaScript.
     */
    public void navigateBack() {

        js.executeScript("window.history.back()");
    }

    /**
     * Navigate forward using JavaScript.
     */
    public void navigateForward() {

        js.executeScript("window.history.forward()");
    }

    /**
     * Execute custom JavaScript.
     */
    public Object executeScript(String script, Object... args) {

        return js.executeScript(script, args);
    }

    /**
     * Execute async JavaScript.
     */
    public Object executeAsyncScript(String script, Object... args) {

        return js.executeAsyncScript(script, args);
    }
}