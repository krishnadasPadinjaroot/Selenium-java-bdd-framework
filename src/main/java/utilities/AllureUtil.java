package utilities;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class AllureUtil {

    /**
     * Attaches a screenshot to the Allure Report.
     *
     * @param screenshot Screenshot captured as a byte array.
     */
    public static void attachScreenshot(byte[] screenshot) {

        // Add screenshot to the Allure report
        Allure.addAttachment(
                "Failure Screenshot",
                new ByteArrayInputStream(screenshot)
        );


    }
}