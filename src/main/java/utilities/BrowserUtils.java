package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class BrowserUtils extends BaseUtils {

    protected WebDriver driver;

    private static final Logger logger =
            LogManager.getLogger(BrowserUtils.class);

    public BrowserUtils() {
        driver = BaseTest.driver;
    }

    /**
     * Maximize browser window.
     */
    public void maximizeWindow() {
        logger.info("Maximizing browser window.");
        driver.manage().window().maximize();
    }

    /**
     * Minimize browser window.
     */
    public void minimizeWindow() {
        logger.info("Minimizing browser window.");
        driver.manage().window().minimize();
    }

    /**
     * Full screen browser window.
     */
    public void fullscreenWindow() {
        logger.info("Switching browser to fullscreen.");
        driver.manage().window().fullscreen();
    }

    /**
     * Set browser window size.
     */
    public void setWindowSize(int width, int height) {
        logger.info("Setting browser size to {} x {}", width, height);
        driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Set browser window position.
     */
    public void setWindowPosition(int x, int y) {
        logger.info("Setting browser position to ({}, {})", x, y);
        driver.manage().window().setPosition(new Point(x, y));
    }

    /**
     * Get current page title.
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Page Title : {}", title);
        return title;
    }

    /**
     * Get current URL.
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL : {}", url);
        return url;
    }

    /**
     * Get browser window handle.
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Get browser name.
     */
    public String getBrowserName() {
        return driver.getClass().getSimpleName();
    }

    /**
     * Delete all cookies.
     */
    public void deleteAllCookies() {
        logger.info("Deleting all browser cookies.");
        driver.manage().deleteAllCookies();
    }

    /**
     * Delete a cookie by name.
     */
    public void deleteCookie(String cookieName) {
        logger.info("Deleting cookie: {}", cookieName);
        driver.manage().deleteCookieNamed(cookieName);
    }

    /**
     * Refresh browser.
     */
    public void refreshBrowser() {
        logger.info("Refreshing browser.");
        driver.navigate().refresh();
    }

    /**
     * Close current browser window.
     */
    public void closeCurrentWindow() {
        logger.info("Closing current browser window.");
        driver.close();
    }

    /**
     * Quit browser.
     */
    public void quitBrowser() {
        logger.info("Quitting browser.");
        driver.quit();
    }
}