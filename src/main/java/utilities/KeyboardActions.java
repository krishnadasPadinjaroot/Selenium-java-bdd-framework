package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KeyboardActions extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    private static final Logger logger =
            LogManager.getLogger(KeyboardActions.class);

    public KeyboardActions() {
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
     * Press ENTER key.
     */
    public void pressEnter() {
        logger.info("Pressing ENTER key.");
        actions.sendKeys(Keys.ENTER).perform();
    }

    /**
     * Press TAB key.
     */
    public void pressTab() {
        logger.info("Pressing TAB key.");
        actions.sendKeys(Keys.TAB).perform();
    }

    /**
     * Press ESCAPE key.
     */
    public void pressEscape() {
        logger.info("Pressing ESCAPE key.");
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    /**
     * Press BACKSPACE key.
     */
    public void pressBackspace() {
        logger.info("Pressing BACKSPACE key.");
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }

    /**
     * Press DELETE key.
     */
    public void pressDelete() {
        logger.info("Pressing DELETE key.");
        actions.sendKeys(Keys.DELETE).perform();
    }

    /**
     * Press ARROW UP key.
     */
    public void pressArrowUp() {
        logger.info("Pressing ARROW UP key.");
        actions.sendKeys(Keys.ARROW_UP).perform();
    }

    /**
     * Press ARROW DOWN key.
     */
    public void pressArrowDown() {
        logger.info("Pressing ARROW DOWN key.");
        actions.sendKeys(Keys.ARROW_DOWN).perform();
    }

    /**
     * Press ARROW LEFT key.
     */
    public void pressArrowLeft() {
        logger.info("Pressing ARROW LEFT key.");
        actions.sendKeys(Keys.ARROW_LEFT).perform();
    }

    /**
     * Press ARROW RIGHT key.
     */
    public void pressArrowRight() {
        logger.info("Pressing ARROW RIGHT key.");
        actions.sendKeys(Keys.ARROW_RIGHT).perform();
    }

    /**
     * Press SPACE key.
     */
    public void pressSpace() {
        logger.info("Pressing SPACE key.");
        actions.sendKeys(Keys.SPACE).perform();
    }

    /**
     * Send keys to an element.
     */
    public void sendKeys(By locator, String text) {
        try {
            logger.info("Sending keys '{}' to {}", text, locator);

            getElement(locator).sendKeys(text);

            logger.info("Keys sent successfully.");

        } catch (Exception e) {
            logger.error("Unable to send keys.", e);
            throw e;
        }
    }

    /**
     * Send keyboard shortcut (CTRL + Key).
     */
    public void pressControlKey(CharSequence key) {
        logger.info("Pressing CTRL + {}", key);

        actions.keyDown(Keys.CONTROL)
                .sendKeys(key)
                .keyUp(Keys.CONTROL)
                .perform();
    }

    /**
     * CTRL + A
     */
    public void selectAll() {
        pressControlKey("a");
    }

    /**
     * CTRL + C
     */
    public void copy() {
        pressControlKey("c");
    }

    /**
     * CTRL + V
     */
    public void paste() {
        pressControlKey("v");
    }

    /**
     * CTRL + X
     */
    public void cut() {
        pressControlKey("x");
    }

    /**
     * CTRL + Z
     */
    public void undo() {
        pressControlKey("z");
    }

    /**
     * CTRL + Y
     */
    public void redo() {
        pressControlKey("y");
    }

    /**
     * Press ENTER on a specific element.
     */
    public void pressEnter(By locator) {
        getElement(locator).sendKeys(Keys.ENTER);
    }

    /**
     * Press TAB on a specific element.
     */
    public void pressTab(By locator) {
        getElement(locator).sendKeys(Keys.TAB);
    }

    /**
     * Clear text using CTRL + A + DELETE.
     */
    public void clearUsingKeyboard(By locator) {
        WebElement element = getElement(locator);

        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }
}