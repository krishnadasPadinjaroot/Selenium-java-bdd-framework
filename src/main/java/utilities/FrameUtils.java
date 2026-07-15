package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FrameUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(FrameUtils.class);

    public FrameUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Switch to frame using locator.
     */
    public void switchToFrame(By locator) {

        try {

            logger.info("Switching to frame: {}", locator);

            WebElement frame = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            driver.switchTo().frame(frame);

            logger.info("Switched to frame successfully.");

        } catch (Exception e) {

            logger.error("Unable to switch to frame: {}", locator, e);
            throw e;
        }
    }

    /**
     * Switch to frame using index.
     */
    public void switchToFrame(int index) {

        try {

            logger.info("Switching to frame by index: {}", index);

            driver.switchTo().frame(index);

            logger.info("Switched to frame successfully.");

        } catch (NoSuchFrameException e) {

            logger.error("Frame not found with index: {}", index, e);
            throw e;
        }
    }

    /**
     * Switch to frame using name or id.
     */
    public void switchToFrame(String nameOrId) {

        try {

            logger.info("Switching to frame: {}", nameOrId);

            driver.switchTo().frame(nameOrId);

            logger.info("Switched to frame successfully.");

        } catch (NoSuchFrameException e) {

            logger.error("Frame not found: {}", nameOrId, e);
            throw e;
        }
    }

    /**
     * Switch to parent frame.
     */
    public void switchToParentFrame() {

        try {

            logger.info("Switching to parent frame.");

            driver.switchTo().parentFrame();

            logger.info("Switched to parent frame successfully.");

        } catch (Exception e) {

            logger.error("Unable to switch to parent frame.", e);
            throw e;
        }
    }

    /**
     * Switch to default content.
     */
    public void switchToDefaultContent() {

        try {

            logger.info("Switching to default content.");

            driver.switchTo().defaultContent();

            logger.info("Switched to default content successfully.");

        } catch (Exception e) {

            logger.error("Unable to switch to default content.", e);
            throw e;
        }
    }

    /**
     * Check if frame exists.
     */
    public boolean isFramePresent(By locator) {

        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}