package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {



    LoginPage loginPage = new LoginPage();

    @Given("user opens HomePage")
    public void openSite() {
        loginPage.openSite();
    }


    @When("user login with username {string} and password {string}")
    public void login(String username, String password) {

        loginPage.login(username,password);

    }

    @And("user logOut")
    public void userLogOut() {

        loginPage.userLogOut();

    }




}