package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonActions;
import utilities.ConfigReader;

public class LoginPage extends CommonActions {

    // Read values from config.properties
    String Username = ConfigReader.getProperty("username");
    String Password = ConfigReader.getProperty("password");
    String url = ConfigReader.getProperty("url");

    /**
     * Constructor
     * Initializes all @FindBy WebElements
     */
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    // Username Text Field
    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement usernameInputTextField;

    // Password Text Field
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordInputTextField;

    // Login Button
    @FindBy(id = "login-button")
    WebElement loginButton;

    // Burger Menu Button
    @FindBy(xpath = "//*[text()='Open Menu']")
    WebElement burgerMenuBtn;

    // Logout Link
    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logOutLink;

    /**
     * Opens the application URL.
     */
    public void openSite() {

        driver.get(url);

    }

    /**
     * Logs into the application.
     */
    public void login(String username, String password) {

        enterText(usernameInputTextField, username);
        enterText(passwordInputTextField, password);
        click(loginButton);

    }

    /**
     * Logs out from the application.
     */
    public void userLogOut() {

        click(burgerMenuBtn);
        click(logOutLink);

    }

}