package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(ElementActions.class);

    public ElementActions() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Returns WebElement after waiting for visibility.
     */
    private WebElement getElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Click on element.
     */
    public void click(By locator) {

        try {

            logger.info("Clicking on {}", locator);

            wait.until(ExpectedConditions.elementToBeClickable(locator))
                    .click();

            logger.info("Element clicked successfully.");

        } catch (ElementClickInterceptedException e) {

            logger.warn("Normal click failed. Using JavaScript click.");

            WebElement element = driver.findElement(locator);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);

        } catch (Exception e) {

            logger.error("Unable to click {}", locator, e);
            throw e;
        }
    }

    /**
     * Enter text.
     */
    public void enterText(By locator, String text) {

        try {

            logger.info("Entering text into {}", locator);

            WebElement element = getElement(locator);

            element.clear();
            element.sendKeys(text);

            logger.info("Text entered successfully.");

        } catch (Exception e) {

            logger.error("Unable to enter text.", e);
            throw e;
        }
    }

    /**
     * Clear text.
     */
    public void clearText(By locator) {

        try {

            logger.info("Clearing {}", locator);

            getElement(locator).clear();

        } catch (Exception e) {

            logger.error("Unable to clear text.", e);
            throw e;
        }
    }

    /**
     * Get text.
     */
    public String getText(By locator) {

        try {

            String text = getElement(locator).getText();

            logger.info("Retrieved text : {}", text);

            return text;

        } catch (Exception e) {

            logger.error("Unable to get text.", e);
            throw e;
        }
    }

    /**
     * Get attribute.
     */
    public String getAttribute(By locator, String attribute) {

        try {

            return getElement(locator).getAttribute(attribute);

        } catch (Exception e) {

            logger.error("Unable to get attribute.", e);
            throw e;
        }
    }

    /**
     * Check element displayed.
     */
    public boolean isDisplayed(By locator) {

        try {

            return getElement(locator).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Check element enabled.
     */
    public boolean isEnabled(By locator) {

        try {

            return getElement(locator).isEnabled();

        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Check element selected.
     */
    public boolean isSelected(By locator) {

        try {

            return getElement(locator).isSelected();

        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Submit form.
     */
    public void submit(By locator) {

        try {

            getElement(locator).submit();

            logger.info("Form submitted.");

        } catch (Exception e) {

            logger.error("Unable to submit form.", e);
            throw e;
        }
    }

    /**
     * Get tag name.
     */
    public String getTagName(By locator) {

        return getElement(locator).getTagName();
    }

    /**
     * Get CSS value.
     */
    public String getCssValue(By locator, String cssProperty) {

        return getElement(locator).getCssValue(cssProperty);
    }

    /**
     * Check checkbox/radio button.
     */
    public void check(By locator) {

        if (!isSelected(locator)) {
            click(locator);
        }
    }

    /**
     * Uncheck checkbox.
     */
    public void uncheck(By locator) {

        if (isSelected(locator)) {
            click(locator);
        }
    }

    /**
     * Upload file.
     */
    public void uploadFile(By locator, String filePath) {

        getElement(locator).sendKeys(filePath);
    }
}