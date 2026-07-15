package pages;

import org.openqa.selenium.By;
import utilities.CommonActions;
import utilities.ConfigReader;

public class ProductsPage extends CommonActions {

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

    /* =========================
       PAGE ACTIONS
       ========================= */

    public void addProductsToCart() {

        click(backpackBtn);
        click(bikeLightBtn);
        click(boltTShirtBtn);
        click(redTShirtBtn);

        selectByText(sortDropdown, "Name (Z to A)");
        selectByText(sortDropdown, "Name (A to Z)");
    }

    public void navigateToCartPage() {
        click(shoppingCartLink);
    }

    public void navigateToCheckoutPage() {
        click(checkoutButton);
    }

    public void enterCheckoutInfoAndFinish() {

        click(shoppingCartLink);
        click(checkoutButton);

        enterText(firstNameTextBox, firstName);
        enterText(lastNameTextBox, lastName);
        enterText(zipCodeTextBox, zipCode);

        click(continueButton);
        click(finishButton);
    }

    public void enterCheckoutInfoAndVerifyLastNameErrorMsg() {

        click(shoppingCartLink);
        click(checkoutButton);

        enterText(firstNameTextBox, firstName);
        enterText(lastNameTextBox, "");
        enterText(zipCodeTextBox, zipCode);

        click(continueButton);
    }

    public String getLastNameRequiredMessage() {
        return getText(lastNameRequiredMsg);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}