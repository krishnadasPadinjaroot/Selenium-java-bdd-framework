package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MouseActions extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    private static final Logger logger =
            LogManager.getLogger(MouseActions.class);

    public MouseActions() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    /**
     * Returns WebElement after waiting for visibility.
     */
    private WebElement getElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Hover over element.
     */
    public void hover(By locator) {
        try {
            logger.info("Hovering over element: {}", locator);

            actions.moveToElement(getElement(locator)).perform();

            logger.info("Hover performed successfully.");

        } catch (Exception e) {
            logger.error("Unable to hover over element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Double click.
     */
    public void doubleClick(By locator) {
        try {
            logger.info("Double clicking on element: {}", locator);

            actions.doubleClick(getElement(locator)).perform();

            logger.info("Double click successful.");

        } catch (Exception e) {
            logger.error("Unable to double click on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Right click.
     */
    public void rightClick(By locator) {
        try {
            logger.info("Right clicking on element: {}", locator);

            actions.contextClick(getElement(locator)).perform();

            logger.info("Right click successful.");

        } catch (Exception e) {
            logger.error("Unable to right click on element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Click and hold.
     */
    public void clickAndHold(By locator) {
        try {
            logger.info("Click and hold on element: {}", locator);

            actions.clickAndHold(getElement(locator)).perform();

            logger.info("Click and hold successful.");

        } catch (Exception e) {
            logger.error("Unable to click and hold: {}", locator, e);
            throw e;
        }
    }

    /**
     * Release held mouse button.
     */
    public void release(By locator) {
        try {
            logger.info("Releasing mouse on element: {}", locator);

            actions.release(getElement(locator)).perform();

            logger.info("Mouse released successfully.");

        } catch (Exception e) {
            logger.error("Unable to release mouse: {}", locator, e);
            throw e;
        }
    }

    /**
     * Drag and drop.
     */
    public void dragAndDrop(By source, By target) {
        try {
            logger.info("Dragging {} to {}", source, target);

            actions.dragAndDrop(
                            getElement(source),
                            getElement(target))
                    .perform();

            logger.info("Drag and drop successful.");

        } catch (Exception e) {
            logger.error("Unable to drag and drop.", e);
            throw e;
        }
    }

    /**
     * Drag and drop by offset.
     */
    public void dragAndDropBy(By locator, int xOffset, int yOffset) {
        try {
            logger.info("Dragging element {} by X={} Y={}", locator, xOffset, yOffset);

            actions.dragAndDropBy(
                            getElement(locator),
                            xOffset,
                            yOffset)
                    .perform();

            logger.info("Drag by offset successful.");

        } catch (Exception e) {
            logger.error("Unable to drag by offset.", e);
            throw e;
        }
    }

    /**
     * Move to element.
     */
    public void moveToElement(By locator) {
        try {
            logger.info("Moving to element: {}", locator);

            actions.moveToElement(getElement(locator)).perform();

            logger.info("Moved to element successfully.");

        } catch (Exception e) {
            logger.error("Unable to move to element: {}", locator, e);
            throw e;
        }
    }

    /**
     * Move to element with offset.
     */
    public void moveToElement(By locator, int xOffset, int yOffset) {
        try {
            logger.info("Moving to element {} with offset X={} Y={}",
                    locator, xOffset, yOffset);

            actions.moveToElement(
                            getElement(locator),
                            xOffset,
                            yOffset)
                    .perform();

            logger.info("Move with offset successful.");

        } catch (Exception e) {
            logger.error("Unable to move with offset.", e);
            throw e;
        }
    }

    /**
     * Click using Actions class.
     */
    public void clickUsingActions(By locator) {
        try {
            logger.info("Clicking using Actions: {}", locator);

            actions.moveToElement(getElement(locator))
                    .click()
                    .perform();

            logger.info("Action click successful.");

        } catch (Exception e) {
            logger.error("Unable to click using Actions.", e);
            throw e;
        }
    }

    /**
     * Send keys using Actions class.
     */
    public void sendKeysUsingActions(By locator, String text) {
        try {
            logger.info("Sending keys '{}' using Actions to {}", text, locator);

            actions.moveToElement(getElement(locator))
                    .click()
                    .sendKeys(text)
                    .perform();

            logger.info("Keys sent successfully.");

        } catch (Exception e) {
            logger.error("Unable to send keys using Actions.", e);
            throw e;
        }
    }

    /**
     * Scroll to element using Actions.
     */
    public void scrollToElement(By locator) {
        try {
            logger.info("Scrolling to element using Actions: {}", locator);

            actions.scrollToElement(getElement(locator))
                    .perform();

            logger.info("Scrolled successfully.");

        } catch (Exception e) {
            logger.error("Unable to scroll to element.", e);
            throw e;
        }
    }
}