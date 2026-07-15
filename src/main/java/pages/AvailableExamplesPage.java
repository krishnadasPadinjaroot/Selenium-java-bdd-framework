package pages;

import org.openqa.selenium.By;
import utilities.CommonActions;

public class AvailableExamplesPage extends CommonActions {

    /* =========================
       LOCATORS
       ========================= */

    private final By lnkABTesting = By.linkText("A/B Testing");

    private final By txtVariationMessage =
            By.xpath("//div[@class='example']/p");

    /* =========================
       PAGE ACTIONS
       ========================= */

    /**
     * Click on A/B Testing link
     */
    public void clickAorBTestingLink() {
        click(lnkABTesting);
    }

    /**
     * Get the variation message
     */
    public String getTextAorBTestVariationText() {
        return getText(txtVariationMessage);
    }
}