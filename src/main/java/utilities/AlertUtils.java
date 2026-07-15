package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(AlertUtils.class);

    public AlertUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Wait for alert and return Alert object.
     */
    private Alert waitForAlert() {
        logger.info("Waiting for alert...");
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Accept Alert
     */
    public void acceptAlert() {
        try {
            logger.info("Accepting alert");

            waitForAlert().accept();

            logger.info("Alert accepted successfully.");

        } catch (TimeoutException e) {
            logger.error("Alert not found.", e);
            throw e;
        }
    }

    /**
     * Dismiss Alert
     */
    public void dismissAlert() {
        try {
            logger.info("Dismissing alert");

            waitForAlert().dismiss();

            logger.info("Alert dismissed successfully.");

        } catch (TimeoutException e) {
            logger.error("Alert not found.", e);
            throw e;
        }
    }

    /**
     * Get Alert Text
     */
    public String getAlertText() {
        try {
            logger.info("Getting alert text");

            String text = waitForAlert().getText();

            logger.info("Alert text: {}", text);

            return text;

        } catch (TimeoutException e) {
            logger.error("Alert not found.", e);
            throw e;
        }
    }

    /**
     * Enter text into Alert
     */
    public void sendAlertText(String text) {
        try {
            logger.info("Sending text to alert");

            Alert alert = waitForAlert();
            alert.sendKeys(text);

            logger.info("Text entered successfully.");

        } catch (TimeoutException e) {
            logger.error("Alert not found.", e);
            throw e;
        }
    }

    /**
     * Check whether alert is present.
     */
    public boolean isAlertPresent() {

        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Accept alert only if present.
     */
    public void acceptAlertIfPresent() {

        if (isAlertPresent()) {
            acceptAlert();
        } else {
            logger.info("No alert present.");
        }
    }

    /**
     * Dismiss alert only if present.
     */
    public void dismissAlertIfPresent() {

        if (isAlertPresent()) {
            dismissAlert();
        } else {
            logger.info("No alert present.");
        }
    }
}