package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ProductsPage;

public class ProductsPageSteps {


    ProductsPage productsPage = new ProductsPage();

    @When("user add products to cart")
    public void addProductsToCart() {

        productsPage.addProductsToCart();

    }

    @And("user navigate to cart page")
    public void navigateToCartPage() {

        productsPage.navigateToCartPage();

    }

    @And("user navigate to check out page")
    public void navigateToCheckoutPage() {

        productsPage.navigateToCheckoutPage();

    }

    @And("user enter Checkout Info And Finish")
    public void enterCheckoutInfoAndFinish() {

        productsPage.enterCheckoutInfoAndFinish();

    }
    @Then("user enter Checkout Info And Verify LastName Error Msg")
    public void enterCheckoutInfoAndVerifyLastNameErrorMsg() {

        productsPage.enterCheckoutInfoAndVerifyLastNameErrorMsg();
//        Assert.assertEquals(getText(lastNameRequiredErrorMsg),
//                "Error: Last Name is required"
//        );

    }

    @Then("user verify success message")
    public void verifySuccessMessage() {
        String ActualSuccessMessage= productsPage.getSuccessMessage();
        Assert.assertEquals(ActualSuccessMessage,"Thank you for your order!");

    }

}