package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils extends BaseUtils {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(ScreenshotUtils.class);

    private static final String SCREENSHOT_FOLDER =
            System.getProperty("user.dir") + "/screenshots/";

    public ScreenshotUtils() {
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Capture full page screenshot.
     */
    public void takeScreenshot(String fileName) {

        try {

            logger.info("Capturing screenshot : {}", fileName);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            saveFile(source, fileName);

            logger.info("Screenshot captured successfully.");

        } catch (Exception e) {

            logger.error("Unable to capture screenshot.", e);
            throw e;
        }
    }

    /**
     * Capture screenshot with timestamp.
     */
    public void takeScreenshotWithTimeStamp(String fileName) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        takeScreenshot(fileName + "_" + timestamp);
    }

    /**
     * Capture element screenshot.
     */
    public void takeElementScreenshot(By locator, String fileName) {

        try {

            logger.info("Capturing element screenshot : {}", locator);

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));

            File source = element.getScreenshotAs(OutputType.FILE);

            saveFile(source, fileName);

            logger.info("Element screenshot captured successfully.");

        } catch (Exception e) {

            logger.error("Unable to capture element screenshot.", e);
            throw e;
        }
    }

    /**
     * Save screenshot to screenshots folder.
     */
    private void saveFile(File source, String fileName) {

        File destination =
                new File(SCREENSHOT_FOLDER + fileName + ".png");

        destination.getParentFile().mkdirs();

        try {

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            logger.info("Screenshot saved at : {}",
                    destination.getAbsolutePath());

        } catch (IOException e) {

            logger.error("Unable to save screenshot.", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * Returns screenshot path.
     */
    public String getScreenshotPath(String fileName) {

        return SCREENSHOT_FOLDER + fileName + ".png";
    }

    /**
     * Check if screenshot exists.
     */
    public boolean isScreenshotPresent(String fileName) {

        File file =
                new File(SCREENSHOT_FOLDER + fileName + ".png");

        return file.exists();
    }

    /**
     * Delete screenshot.
     */
    public boolean deleteScreenshot(String fileName) {

        File file =
                new File(SCREENSHOT_FOLDER + fileName + ".png");

        if (file.exists()) {

            logger.info("Deleting screenshot : {}", fileName);

            return file.delete();
        }

        return false;
    }
}