package utilities;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public class AllureUtil {

    public static void attachScreenshot(byte[] screenshot) {

        // Add screenshot to the Allure report
        Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(screenshot),
                ".png");
    }
}