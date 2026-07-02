
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

public class CommonActions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public CommonActions() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    /* =========================
       ELEMENT ACTIONS
       ========================= */

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void enterText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public void clearText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attributeName);
    }

    public boolean isDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public boolean isEnabled(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isEnabled();
    }

    public boolean isSelected(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isSelected();
    }

    /* =========================
       WAIT METHODS
       ========================= */

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
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

    public void hover(WebElement element) {
        waitForVisibility(element);
        actions.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    public void clickAndHold(WebElement element) {
        waitForVisibility(element);
        actions.clickAndHold(element).perform();
    }

    public void release(WebElement element) {
        waitForVisibility(element);
        actions.release(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        waitForVisibility(source);
        waitForVisibility(target);
        actions.dragAndDrop(source, target).perform();
    }

    public void sendKeysUsingActions(WebElement element, String text) {
        waitForVisibility(element);
        actions.moveToElement(element).click().sendKeys(text).perform();
    }

    /* =========================
       KEYBOARD ACTIONS
       ========================= */

    public void pressEnter() { actions.sendKeys(Keys.ENTER).perform(); }
    public void pressTab() { actions.sendKeys(Keys.TAB).perform(); }
    public void pressEscape() { actions.sendKeys(Keys.ESCAPE).perform(); }

    /* =========================
       JAVASCRIPT ACTIONS
       ========================= */

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
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

    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.border='3px solid red';", element);
    }

    /* =========================
       DROPDOWN METHODS
       ========================= */

    public void selectByText(WebElement element, String value) {
        new Select(element).selectByVisibleText(value);
    }

    public void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public String getSelectedOption(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

    public List<String> getAllOptions(WebElement element) {
        List<String> options = new ArrayList<>();
        for (WebElement option : new Select(element).getOptions()) {
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

    public void switchToFrame(WebElement frameElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void switchToFrame(int index) { driver.switchTo().frame(index); }

    public void switchToFrame(String nameOrId) { driver.switchTo().frame(nameOrId); }

    public void switchToParentFrame() { driver.switchTo().parentFrame(); }

    public void switchToDefaultContent() { driver.switchTo().defaultContent(); }

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

    public void navigateTo(String url) { driver.navigate().to(url); }
    public void navigateBack() { driver.navigate().back(); }
    public void navigateForward() { driver.navigate().forward(); }
    public void refreshPage() { driver.navigate().refresh(); }

    /* =========================
       BROWSER INFO
       ========================= */

    public String getCurrentUrl() { return driver.getCurrentUrl(); }
    public String getPageTitle() { return driver.getTitle(); }

    /* =========================
       SCREENSHOTS
       ========================= */

    public void takeScreenshot(String fileName) {
        saveFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), fileName);
    }

    public void takeElementScreenshot(WebElement element, String fileName) {
        saveFile(element.getScreenshotAs(OutputType.FILE), fileName);
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
}
