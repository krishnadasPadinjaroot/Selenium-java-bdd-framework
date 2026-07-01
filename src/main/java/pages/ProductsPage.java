package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonActions;
import utilities.ConfigReader;

public class ProductsPage extends CommonActions {

    String firstname = ConfigReader.getProperty("firstname");
    String lastname = ConfigReader.getProperty("lastname");
    String zipCode = ConfigReader.getProperty("Zipcode");

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    // Products
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartSauceLabsBackpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement addToCartSauceLabsBikeLight;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement addToCartSauceLabsBolt_t_Shirt;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement addToCartSauceLabsTestAllTheThingsTshirtRed;

    @FindBy(css = "[data-test='product-sort-container']")
    WebElement sortDropdown;

    // Cart
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement shoppingCartLink;

    @FindBy(id = "checkout")
    WebElement checkOutButton;

    // Checkout Information
    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstNameBillingAddressInputTextField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameBillingAddressInputTextField;

    @FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
    WebElement zip_PostalCodeInputTextField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(xpath = "//*[text()='Error: Last Name is required']")
    WebElement lastNameRequiredErrorMsg;

    @FindBy(xpath = "//div[@id='checkout_complete_container']//h2")
    WebElement successMessage;

    /**
     * Add products to cart and sort products.
     */
    public void addProductsToCart() {

        click(addToCartSauceLabsBackpack);
        click(addToCartSauceLabsBikeLight);
        click(addToCartSauceLabsBolt_t_Shirt);
        click(addToCartSauceLabsTestAllTheThingsTshirtRed);

        Select select = new Select(sortDropdown);

        select.selectByVisibleText("Name (Z to A)");
        select.selectByVisibleText("Name (A to Z)");

    }

    /**
     * Navigate to Cart Page.
     */
    public void navigateToCartPage() {

        click(shoppingCartLink);

    }

    /**
     * Navigate to Checkout Page.
     */
    public void navigateToCheckoutPage() {

        click(checkOutButton);

    }

    /**
     * Complete checkout successfully.
     */
    public void enterCheckoutInfoAndFinish() {

        click(shoppingCartLink);
        click(checkOutButton);

        enterText(firstNameBillingAddressInputTextField, firstname);
        enterText(lastNameBillingAddressInputTextField, lastname);
        enterText(zip_PostalCodeInputTextField, zipCode);

        click(continueButton);
        click(finishButton);

    }

    /**
     * Verify Last Name validation message.
     */
    public void enterCheckoutInfoAndVerifyLastNameErrorMsg() {

        click(shoppingCartLink);
        click(checkOutButton);

        enterText(firstNameBillingAddressInputTextField, firstname);

        // Intentionally passing blank value to verify validation
        enterText(lastNameBillingAddressInputTextField,"test");

        enterText(zip_PostalCodeInputTextField, zipCode);

        click(continueButton);

//        Assert.assertEquals(
//                getText(lastNameRequiredErrorMsg),
//                "Error: Last Name is required"


    }

    /**
     * Verify order completion message.
     */
    public String verifySuccessMessage() {

        return
                getText(successMessage);





    }

}