package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DropdownUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(DropdownUtils.class);

    public DropdownUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Returns Select object.
     */
    private Select getSelect(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));

        return new Select(element);
    }

    /**
     * Select by visible text.
     */
    public void selectByText(By locator, String text) {
        try {
            logger.info("Selecting '{}' from {}", text, locator);

            getSelect(locator).selectByVisibleText(text);

            logger.info("Option selected successfully.");

        } catch (Exception e) {
            logger.error("Unable to select '{}' from {}", text, locator, e);
            throw e;
        }
    }

    /**
     * Select by value.
     */
    public void selectByValue(By locator, String value) {
        try {
            logger.info("Selecting value '{}' from {}", value, locator);

            getSelect(locator).selectByValue(value);

            logger.info("Option selected successfully.");

        } catch (Exception e) {
            logger.error("Unable to select value '{}' from {}", value, locator, e);
            throw e;
        }
    }

    /**
     * Select by index.
     */
    public void selectByIndex(By locator, int index) {
        try {
            logger.info("Selecting index '{}' from {}", index, locator);

            getSelect(locator).selectByIndex(index);

            logger.info("Option selected successfully.");

        } catch (Exception e) {
            logger.error("Unable to select index '{}' from {}", index, locator, e);
            throw e;
        }
    }

    /**
     * Get selected option.
     */
    public String getSelectedOption(By locator) {
        try {
            String selectedOption = getSelect(locator)
                    .getFirstSelectedOption()
                    .getText();

            logger.info("Selected option : {}", selectedOption);

            return selectedOption;

        } catch (Exception e) {
            logger.error("Unable to get selected option.", e);
            throw e;
        }
    }

    /**
     * Get all dropdown options.
     */
    public List<String> getAllOptions(By locator) {

        List<String> options = new ArrayList<>();

        try {

            for (WebElement option : getSelect(locator).getOptions()) {
                options.add(option.getText());
            }

            logger.info("Retrieved {} options.", options.size());

            return options;

        } catch (Exception e) {
            logger.error("Unable to retrieve dropdown options.", e);
            throw e;
        }
    }

    /**
     * Returns number of options.
     */
    public int getOptionsCount(By locator) {
        return getSelect(locator).getOptions().size();
    }

    /**
     * Check if dropdown supports multiple selection.
     */
    public boolean isMultiple(By locator) {
        return getSelect(locator).isMultiple();
    }

    /**
     * Deselect all options.
     */
    public void deselectAll(By locator) {

        if (isMultiple(locator)) {
            getSelect(locator).deselectAll();
        }
    }

    /**
     * Deselect by visible text.
     */
    public void deselectByText(By locator, String text) {

        if (isMultiple(locator)) {
            getSelect(locator).deselectByVisibleText(text);
        }
    }

    /**
     * Deselect by value.
     */
    public void deselectByValue(By locator, String value) {

        if (isMultiple(locator)) {
            getSelect(locator).deselectByValue(value);
        }
    }

    /**
     * Deselect by index.
     */
    public void deselectByIndex(By locator, int index) {

        if (isMultiple(locator)) {
            getSelect(locator).deselectByIndex(index);
        }
    }
}