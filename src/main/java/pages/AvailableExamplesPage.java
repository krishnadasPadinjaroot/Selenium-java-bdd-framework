package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonActions;

public class AvailableExamplesPage extends CommonActions {

    public AvailableExamplesPage() {
        PageFactory.initElements(BaseTest.driver, this);
    }

    // A/B Testing Link
    @FindBy(linkText = "A/B Testing")
    WebElement lnkABTesting;

    // Variation Text
    @FindBy(xpath = "//div[@class='example']/p")
    WebElement txtVariationMessage;

    /**
     * Click on A/B Testing link
     */
    public void clickAorBTestingLink() {
        click(lnkABTesting);
    }

    /**
     * Get the variation message
     */
    public String getTextAorBTestVariation1Text() {
        return getText(txtVariationMessage);
    }
}