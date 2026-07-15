package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowUtils extends BaseUtils {

    protected WebDriver driver;

    private static final Logger logger =
            LogManager.getLogger(WindowUtils.class);

    public WindowUtils() {
        driver = BaseTest.driver;
    }

    /**
     * Switch to window by title.
     */
    public void switchToWindow(String title) {

        try {

            logger.info("Switching to window with title: {}", title);

            for (String window : driver.getWindowHandles()) {

                driver.switchTo().window(window);

                if (driver.getTitle().equals(title)) {

                    logger.info("Successfully switched to window: {}", title);
                    return;
                }
            }

            throw new NoSuchWindowException(
                    "Window with title '" + title + "' not found.");

        } catch (Exception e) {

            logger.error("Unable to switch to window: {}", title, e);
            throw e;
        }
    }

    /**
     * Switch to window by index.
     */
    public void switchToWindowByIndex(int index) {

        try {

            List<String> windows =
                    new ArrayList<>(driver.getWindowHandles());

            if (index >= windows.size()) {
                throw new IndexOutOfBoundsException(
                        "Invalid window index : " + index);
            }

            driver.switchTo().window(windows.get(index));

            logger.info("Switched to window index : {}", index);

        } catch (Exception e) {

            logger.error("Unable to switch window by index.", e);
            throw e;
        }
    }

    /**
     * Switch to latest opened window.
     */
    public void switchToLatestWindow() {

        try {

            List<String> windows =
                    new ArrayList<>(driver.getWindowHandles());

            driver.switchTo().window(
                    windows.get(windows.size() - 1));

            logger.info("Switched to latest window.");

        } catch (Exception e) {

            logger.error("Unable to switch to latest window.", e);
            throw e;
        }
    }

    /**
     * Switch to first window.
     */
    public void switchToFirstWindow() {

        try {

            List<String> windows =
                    new ArrayList<>(driver.getWindowHandles());

            driver.switchTo().window(windows.get(0));

            logger.info("Switched to first window.");

        } catch (Exception e) {

            logger.error("Unable to switch to first window.", e);
            throw e;
        }
    }

    /**
     * Get current window handle.
     */
    public String getCurrentWindowHandle() {

        return driver.getWindowHandle();
    }

    /**
     * Get all window handles.
     */
    public Set<String> getAllWindowHandles() {

        return driver.getWindowHandles();
    }

    /**
     * Get number of windows.
     */
    public int getWindowCount() {

        return driver.getWindowHandles().size();
    }

    /**
     * Close current window.
     */
    public void closeCurrentWindow() {

        try {

            logger.info("Closing current window.");

            driver.close();

        } catch (Exception e) {

            logger.error("Unable to close current window.", e);
            throw e;
        }
    }

    /**
     * Close all child windows and switch back to parent.
     */
    public void closeChildWindows() {

        String parent = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {

            if (!window.equals(parent)) {

                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(parent);

        logger.info("All child windows closed.");
    }

    /**
     * Switch to parent window.
     */
    public void switchToParentWindow(String parentHandle) {

        try {

            driver.switchTo().window(parentHandle);

            logger.info("Switched to parent window.");

        } catch (Exception e) {

            logger.error("Unable to switch to parent window.", e);
            throw e;
        }
    }

    /**
     * Open a new browser tab.
     */
    public void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
        logger.info("New tab opened.");
    }

    /**
     * Open a new browser window.
     */
    public void openNewWindow() {

        driver.switchTo().newWindow(WindowType.WINDOW);

        logger.info("New browser window opened.");
    }
}