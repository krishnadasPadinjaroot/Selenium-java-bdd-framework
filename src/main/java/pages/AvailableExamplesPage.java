package pages;

import org.openqa.selenium.By;
import utilities.BaseUtils;

public class AvailableExamplesPage extends BasePage  {

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
      element.click(lnkABTesting);
    }

    /**
     * Get the variation message
     */
    public String getTextAorBTestVariationText() {

        return element.getText(txtVariationMessage);
    }
}