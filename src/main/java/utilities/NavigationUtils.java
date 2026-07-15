package utilities;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class NavigationUtils extends BaseUtils {

    protected WebDriver driver;

    private static final Logger logger =
            LogManager.getLogger(NavigationUtils.class);

    public NavigationUtils() {
        driver = BaseTest.driver;
    }

    /**
     * Navigate to URL.
     */
    public void navigateTo(String url) {
        try {
            logger.info("Navigating to URL: {}", url);

            driver.navigate().to(url);

            logger.info("Navigation successful.");

        } catch (Exception e) {
            logger.error("Unable to navigate to URL: {}", url, e);
            throw e;
        }
    }

    /**
     * Navigate back.
     */
    public void navigateBack() {
        try {
            logger.info("Navigating back.");

            driver.navigate().back();

            logger.info("Navigation back successful.");

        } catch (Exception e) {
            logger.error("Unable to navigate back.", e);
            throw e;
        }
    }

    /**
     * Navigate forward.
     */
    public void navigateForward() {
        try {
            logger.info("Navigating forward.");

            driver.navigate().forward();

            logger.info("Navigation forward successful.");

        } catch (Exception e) {
            logger.error("Unable to navigate forward.", e);
            throw e;
        }
    }

    /**
     * Refresh current page.
     */
    public void refreshPage() {
        try {
            logger.info("Refreshing current page.");

            driver.navigate().refresh();

            logger.info("Page refreshed successfully.");

        } catch (Exception e) {
            logger.error("Unable to refresh page.", e);
            throw e;
        }
    }

    /**
     * Open application URL from config.
     */
    public void openApplication() {
        try {
            String url = ConfigReader.getProperty("url");

            logger.info("Opening application: {}", url);

            driver.get(url);

            logger.info("Application opened successfully.");

        } catch (Exception e) {
            logger.error("Unable to open application.", e);
            throw e;
        }
    }

    /**
     * Navigate to URL using driver.get().
     */
    public void openUrl(String url) {
        try {
            logger.info("Opening URL: {}", url);

            driver.get(url);

            logger.info("URL opened successfully.");

        } catch (Exception e) {
            logger.error("Unable to open URL: {}", url, e);
            throw e;
        }
    }

    /**
     * Get current URL.
     */
    public String getCurrentUrl() {
        try {
            String url = driver.getCurrentUrl();

            logger.info("Current URL: {}", url);

            return url;

        } catch (Exception e) {
            logger.error("Unable to get current URL.", e);
            throw e;
        }
    }

    /**
     * Get page title.
     */
    public String getPageTitle() {
        try {
            String title = driver.getTitle();

            logger.info("Page Title: {}", title);

            return title;

        } catch (Exception e) {
            logger.error("Unable to get page title.", e);
            throw e;
        }
    }
}