package pages;

import org.openqa.selenium.By;
import utilities.BaseUtils;
import utilities.ConfigReader;

public class ProductsPage extends BasePage {

    private final String firstName = ConfigReader.getProperty("firstname");
    private final String lastName = ConfigReader.getProperty("lastname");
    private final String zipCode = ConfigReader.getProperty("Zipcode");

    /* =========================
       PRODUCT LOCATORS
       ========================= */

    private final By backpackBtn =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By bikeLightBtn =
            By.id("add-to-cart-sauce-labs-bike-light");

    private final By boltTShirtBtn =
            By.id("add-to-cart-sauce-labs-bolt-t-shirt");

    private final By redTShirtBtn =
            By.id("add-to-cart-test.allthethings()-t-shirt-(red)");

    private final By sortDropdown =
            By.cssSelector("[data-test='product-sort-container']");

    /* =========================
       CART LOCATORS
       ========================= */

    private final By shoppingCartLink =
            By.cssSelector(".shopping_cart_link");

    private final By checkoutButton =
            By.id("checkout");

    /* =========================
       CHECKOUT LOCATORS
       ========================= */

    private final By firstNameTextBox =
            By.id("first-name");

    private final By lastNameTextBox =
            By.id("last-name");

    private final By zipCodeTextBox =
            By.id("postal-code");

    private final By continueButton =
            By.id("continue");

    private final By finishButton =
            By.id("finish");

    private final By lastNameRequiredMsg =
            By.xpath("//*[text()='Error: Last Name is required']");

    private final By successMessage =
            By.cssSelector("#checkout_complete_container h2");
    private final By lastNameRequiredErrorMsg =
            By.xpath("//h3[contains(text(),'Last Name is required')]");

    /* =========================
       PAGE ACTIONS
       ========================= */

    public void addProductsToCart() {

        element.click(backpackBtn);
        element.click(bikeLightBtn);
        element.click(boltTShirtBtn);
        element.click(redTShirtBtn);

        dropdown.selectByText(sortDropdown, "Name (Z to A)");
        dropdown.selectByText(sortDropdown, "Name (A to Z)");
    }

    public void navigateToCartPage() {
        element.click(shoppingCartLink);
    }

    public void navigateToCheckoutPage() {
        element.click(checkoutButton);
    }

    public void enterCheckoutInfoAndFinish() {

        element.click(shoppingCartLink);
        element.click(checkoutButton);

        element.enterText(firstNameTextBox, firstName);
        element.enterText(lastNameTextBox, lastName);
        element.enterText(zipCodeTextBox, zipCode);

        element.click(continueButton);
        element.click(finishButton);
    }

    public void enterCheckoutInfoAndVerifyLastNameErrorMsg() {

        element.click(shoppingCartLink);
        element.click(checkoutButton);

        element.enterText(firstNameTextBox, firstName);
        element.enterText(lastNameTextBox, "");
       element. enterText(zipCodeTextBox, zipCode);
        element.click(continueButton);
    }

    public String getSuccessMessage() {
        return element.getText(successMessage);
    }
    public String getLastNameRequiredErrorMessage() {
        return element.getText(lastNameRequiredErrorMsg);
    }
}