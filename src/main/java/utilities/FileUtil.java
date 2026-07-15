
package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.Comparator;
import java.util.stream.Stream;

    public final class FileUtil {

        private FileUtil() {
            // Prevent instantiation
        }

        /**
         * Upload file using By locator.
         */
        public static void uploadFile(WebDriver driver, By locator, String filePath) {

            File file = new File(filePath);

            if (!file.exists()) {
                throw new RuntimeException("File not found : " + filePath);
            }

            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }

        /**
         * Upload file using WebElement.
         */
        public static void uploadFile(WebElement element, String filePath) {

            File file = new File(filePath);

            if (!file.exists()) {
                throw new RuntimeException("File not found : " + filePath);
            }

            element.sendKeys(file.getAbsolutePath());
        }

        /**
         * Verify whether file exists.
         */
        public static boolean isFilePresent(String downloadPath, String fileName) {

            File file = new File(downloadPath + File.separator + fileName);

            return file.exists();
        }

        /**
         * Wait until file is downloaded.
         */
        public static boolean isFileDownloaded(String downloadPath,
                                               String fileName,
                                               Duration timeout) {

            File file = new File(downloadPath + File.separator + fileName);

            long endTime = System.currentTimeMillis() + timeout.toMillis();

            while (System.currentTimeMillis() < endTime) {

                if (file.exists() && file.length() > 0) {
                    return true;
                }

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }

            return false;
        }

        /**
         * Delete downloaded file.
         */
        public static boolean deleteFile(String downloadPath,
                                         String fileName) {

            File file = new File(downloadPath + File.separator + fileName);

            return file.exists() && file.delete();
        }

        /**
         * Wait until file is deleted.
         */
        public static boolean waitUntilFileDeleted(String downloadPath,
                                                   String fileName,
                                                   Duration timeout) {

            File file = new File(downloadPath + File.separator + fileName);

            long endTime = System.currentTimeMillis() + timeout.toMillis();

            while (System.currentTimeMillis() < endTime) {

                if (!file.exists()) {
                    return true;
                }

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }

            return false;
        }

        /**
         * Get file size.
         */
        public static long getFileSize(String downloadPath,
                                       String fileName) {

            File file = new File(downloadPath + File.separator + fileName);

            if (!file.exists()) {
                return 0;
            }

            return file.length();
        }

        /**
         * Get file extension.
         */
        public static String getFileExtension(String fileName) {

            int index = fileName.lastIndexOf(".");

            if (index == -1) {
                return "";
            }

            return fileName.substring(index + 1);
        }

        /**
         * Create download folder if it does not exist.
         */
        public static void createDirectory(String path) {

            File directory = new File(path);

            if (!directory.exists()) {
                directory.mkdirs();
            }
        }

        /**
         * Delete all files inside a directory.
         */
        public static void cleanDirectory(String path) {

            File directory = new File(path);

            if (!directory.exists()) {
                return;
            }

            File[] files = directory.listFiles();

            if (files == null) {
                return;
            }

            for (File file : files) {
                file.delete();
            }
        }

        /**
         * Returns the latest downloaded file.
         */
        public static File getLatestFile(String downloadPath) {

            File directory = new File(downloadPath);

            File[] files = directory.listFiles();

            if (files == null || files.length == 0) {
                return null;
            }

            File latest = files[0];

            for (File file : files) {

                if (file.lastModified() > latest.lastModified()) {
                    latest = file;
                }
            }

            return latest;
        }

        /**
         * Wait until download completes
         * (Chrome temporary file .crdownload removed).
         */
        public static boolean waitForDownloadCompletion(String downloadPath,
                                                        Duration timeout) {

            long endTime = System.currentTimeMillis() + timeout.toMillis();

            while (System.currentTimeMillis() < endTime) {

                try (Stream<Path> files = Files.list(Paths.get(downloadPath))) {

                    boolean downloading = files.anyMatch(file ->
                            file.toString().endsWith(".crdownload"));

                    if (!downloading) {
                        return true;
                    }

                } catch (IOException ignored) {
                }

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }

            return false;
        }

        /**
         * Delete directory recursively.
         */
        public static void deleteDirectory(String path) throws IOException {

            Path directory = Paths.get(path);

            if (!Files.exists(directory)) {
                return;
            }

            try (Stream<Path> walk = Files.walk(directory)) {

                walk.sorted(Comparator.reverseOrder())
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }


        /**
         * Delete a downloaded file.
         *
         * @param downloadPath Download directory
         * @param fileName     File name
         */
        public static void deleteDownloadedFile(String downloadPath,
                                                String fileName) {

            File file = new File(downloadPath + File.separator + fileName);

            if (file.exists()) {
                file.delete();
            }
        }

        /**
         * Returns the downloaded file size.
         */
        public static long getDownloadedFileSize(String downloadPath,
                                                 String fileName) {

            File file = new File(downloadPath + File.separator + fileName);

            return file.exists() ? file.length() : 0;
        }

    }

