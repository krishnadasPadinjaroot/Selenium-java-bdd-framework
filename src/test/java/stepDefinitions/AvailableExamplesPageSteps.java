package stepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.AvailableExamplesPage;

public class AvailableExamplesPageSteps {

    AvailableExamplesPage AvailableExamplesPage = new AvailableExamplesPage();

    @Then("user verify AorBTesting")
     public void verifyAorBTesting(){
    AvailableExamplesPage.clickAorBTestingLink();
    String actual=AvailableExamplesPage.getTextAorBTestVariation1Text();
    String expected= "Also known as split testing. This is a way in which businesses are able to simultaneously test and learn different versions of a page to see which text and/or functionality works best towards a desired outcome (e.g. a user action such as a click-through).";
    Assert.assertEquals(actual,expected);



}



}
