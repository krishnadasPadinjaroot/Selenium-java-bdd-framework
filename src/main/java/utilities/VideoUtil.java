package utilities;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VideoUtil {

    public static void attachVideo(Path videoPath) {

        try {
            Allure.addAttachment(
                    "Execution Video",
                    "video/webm",
                    Files.newInputStream(videoPath),
                    ".webm"
            );
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}