
package utilities;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonActions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    private static final Logger logger = LogManager.getLogger(CommonActions.class);
    private static final String DOWNLOAD_PATH = ConfigReader.getProperty("download.path");

    public CommonActions() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    /* =========================
       ELEMENT ACTIONS
       ========================= */

    public void click(By locator) {
        try {
            logger.info("Clicking on element: {}", locator);

            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator));

            element.click();

            logger.info("Element clicked successfully.");
        } catch (ElementClickInterceptedException e) {
            logger.warn("Normal click failed. Trying JavaScript click.");

            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            logger.info("JavaScript click successful.");
        } catch (TimeoutException e) {
            logger.error("Element was not clickable: {}", locator, e);
            throw e;
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", locator, e);
            throw e;
        }
    }



    public void enterText(By locator, String text) {
        try {
            logger.info("Entering text: {}", text);

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            element.clear();
            element.sendKeys(text);

            logger.info("Text entered successfully.");
        } catch (Exception e) {
            logger.error("Unable to enter text into {}", locator, e);
            throw e;
        }
    }


    public void clearText(By locator) {
        try {
            logger.info("Clearing text from {}", locator);

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            element.clear();

            logger.info("Text cleared successfully.");

        } catch (Exception e) {
            logger.error("Unable to clear text from {}", locator, e);
            throw e;
        }
    }

    public String getText(By locator) {
        try {
            logger.info("Getting text from element: {}", locator);

            String text = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(locator))
                    .getText();

            logger.info("Text retrieved successfully: {}", text);

            return text;

        } catch (TimeoutException e) {
            logger.error("Element not visible: {}", locator, e);
            throw e;

        } catch (Exception e) {
            logger.error("Unable to get text from element: {}", locator, e);
            throw e;
        }
    }

    public String getAttribute(By locator, String attributeName) {

        try {

            logger.info("Getting attribute '{}' from {}", attributeName, locator);

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            return element.getAttribute(attributeName);

        } catch (Exception e) {
            logger.error("Unable to get attribute from {}", locator, e);
            throw e;
        }
    }

    public boolean isDisplayed(By locator) {

        try {
            return wait.until(
                            ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();

        } catch (Exception e) {
            logger.error("Element not displayed {}", locator, e);
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        try {
            return wait.until(
                            ExpectedConditions.visibilityOfElementLocated(locator))
                    .isEnabled();
        } catch (Exception e) {
            logger.error("Element not enabled {}", locator, e);
            return false;
        }
    }

    public boolean isSelected(By locator) {
        try {
            return wait.until(
                            ExpectedConditions.visibilityOfElementLocated(locator))
                    .isSelected();
        } catch (Exception e) {
            logger.error("Element not selected {}", locator, e);
            return false;
        }
    }

    /* =========================
       WAIT METHODS
       ========================= */

    public WebElement waitForVisibility(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisibility(By locator) {

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForText(By locator, String text) {

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    /* =========================
       MOUSE ACTIONS
       ========================= */

    public void hover(By locator) {
        WebElement element = waitForVisibility(locator);
        actions.moveToElement(element).perform();
    }

    public void doubleClick(By locator) {
        WebElement element = waitForClickable(locator);
        actions.doubleClick(element).perform();
    }

    public void rightClick(By locator) {
        WebElement element = waitForClickable(locator);
        actions.contextClick(element).perform();
    }

    public void clickAndHold(By locator) {
        WebElement element = waitForVisibility(locator);
        actions.clickAndHold(element).perform();
    }

    public void release(By locator) {
        WebElement element = waitForVisibility(locator);
        actions.release(element).perform();
    }

    public void dragAndDrop(By source, By target) {
        actions.dragAndDrop(
                        waitForVisibility(source),
                        waitForVisibility(target))
                .perform();
    }

    public void sendKeysUsingActions(By locator, String text) {
        actions.moveToElement(waitForVisibility(locator))
                .click()
                .sendKeys(text)
                .perform();
    }

    /* =========================
       KEYBOARD ACTIONS
       ========================= */

    public void pressEnter() {
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void pressTab() {
        actions.sendKeys(Keys.TAB).perform();
    }

    public void pressEscape() {
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    /* =========================
       JAVASCRIPT ACTIONS
       ========================= */

    public void jsClick(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0],arguments[1]);", x, y);
    }

    public void highlightElement(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].style.border='3px solid red';",
                        element);
    }

    /* =========================
       DROPDOWN METHODS
       ========================= */

    public void selectByText(By locator, String value) {
        try {
            logger.info("Selecting '{}' from dropdown: {}", value, locator);

            Select select = new Select(waitForVisibility(locator));
            select.selectByVisibleText(value);

            logger.info("Option selected successfully. ");

        } catch (Exception e) {
            logger.error("Unable to select '{}' from dropdown: {}", value, locator, e);
            throw e;
        }
    }

    public void selectByValue(By locator, String value) {
        try {
            logger.info("Selecting value '{}' from dropdown: {}", value, locator);

            Select select = new Select(waitForVisibility(locator));
            select.selectByValue(value);

            logger.info("Option selected successfully.");

        } catch (Exception e) {
            logger.error("Unable to select value '{}' from dropdown: {}", value, locator, e);
            throw e;
        }
    }

    public void selectByIndex(By locator, int index) {
        try {
            logger.info("Selecting index '{}' from dropdown: {}", index, locator);

            Select select = new Select(waitForVisibility(locator));
            select.selectByIndex(index);

            logger.info("Option selected successfully.");

        } catch (Exception e) {
            logger.error("Unable to select index '{}' from dropdown: {}", index, locator, e);
            throw e;
        }
    }

    public String getSelectedOption(By locator) {

        return new Select(waitForVisibility(locator))
                .getFirstSelectedOption()
                .getText();
    }

    public List<String> getAllOptions(By locator) {

        List<String> options = new ArrayList<>();

        for (WebElement option :
                new Select(waitForVisibility(locator)).getOptions()) {

            options.add(option.getText());
        }

        return options;
    }

    /* =========================
       ALERT METHODS
       ========================= */

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void sendAlertText(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    /* =========================
       FRAME METHODS
       ========================= */

    public void switchToFrame(By locator) {

        driver.switchTo().frame(waitForVisibility(locator));
    }

    public void switchToFrame(int index) {

        driver.switchTo().frame(index);
    }

    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();

    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /* =========================
       WINDOW METHODS
       ========================= */

    public void switchToWindow(String title) {
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(title)) break;
        }
    }

    public void switchToWindowByIndex(int index) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
    }

    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    /* =========================
       NAVIGATION
       ========================= */

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    /* =========================
       BROWSER INFO
       ========================= */

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    /* =========================
       SCREENSHOTS
       ========================= */

    public void takeScreenshot(String fileName) {
        try {
            logger.info("Capturing screenshot: {}", fileName);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            saveFile(source, fileName);

            logger.info("Screenshot captured successfully.");

        } catch (Exception e) {
            logger.error("Unable to capture screenshot: {}", fileName, e);
            throw new RuntimeException("Failed to capture screenshot.", e);
        }
    }

    public void takeElementScreenshot(By locator, String fileName) {

        saveFile(
                waitForVisibility(locator)
                        .getScreenshotAs(OutputType.FILE),
                fileName);
    }

    private void saveFile(File source, String fileName) {
        File destination = new File(System.getProperty("user.dir")
                + "/screenshots/" + fileName + ".png");
        destination.getParentFile().mkdirs();
        try {
            Files.copy(source.toPath(), destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save screenshot", e);
        }
    }

    public static boolean isFileDownloaded(String fileName,
                                           Duration timeout) {

        File file = new File(DOWNLOAD_PATH + File.separator + fileName);

        long endTime = System.currentTimeMillis() + timeout.toMillis();

        while (System.currentTimeMillis() < endTime) {

            if (file.exists() && file.length() > 0) {
                return true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        return false;
    }






}




