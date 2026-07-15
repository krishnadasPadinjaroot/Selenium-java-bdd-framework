package pages;

import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import utilities.BaseUtils;
import utilities.ConfigReader;

public class LoginPage extends BasePage {

    // Read values from config.properties
    private final String url = ConfigReader.getProperty("url");
    private final String username = ConfigReader.getProperty("username");
    private final String password = ConfigReader.getProperty("password");

    // Locators
    private final By usernameTxt = By.xpath("//input[@placeholder='Username']");
    private final By passwordTxt = By.xpath("//input[@placeholder='Password']");
    private final By loginBtn = By.id("login-button");
    private final By menuBtn = By.xpath("//*[text()='Open Menu']");
    private final By logoutLink = By.xpath("//*[text()='Logout']");
    private final By userLockedOutErrorMessage = By.xpath("//*[text()='Epic sadface: Sorry, this user has been locked out.']");
    /**
     * Opens application
     */
    public void openSite() {
        navigation.navigateTo(url);
    }

    /**
     * Login using parameters
     */
    public void login(String userName, String passWord) {
        element.enterText(usernameTxt, userName);
        element.enterText(passwordTxt, passWord);
        element.click(loginBtn);
    }

    /**
     * Login using credentials from config.properties
     */
    public void login() {
        login(username, password);
    }

    /**
     * Logout
     */
    public void userLog0ut() {
        element.click(menuBtn);
        element.click(logoutLink);
    }

    /**
            * verify User Locked Out Error Message
     */
    public String verifyUserLockedOutErrorMessage() {
        String msge= element.getText(userLockedOutErrorMessage);
     return msge;

    }


}