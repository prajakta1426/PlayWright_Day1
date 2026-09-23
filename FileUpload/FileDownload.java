package FileUpload;

import com.microsoft.playwright.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class FileDownload {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setAcceptDownloads(true));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Downloads/fileDownload.html");
            obj_Page.waitForLoadState();
            System.out.println("Page Title: "+obj_Page.title());


            Download obj_download= obj_Page.waitForDownload(()->
            {
                obj_Page.click("#downloadBtn");
            });

            Path saveDir= Paths.get("C:\\Users\\CCST\\Desktop\\Playwright Material\\PlaywrightMaterial");
            Files.createDirectories(saveDir);

            Path savePath =saveDir.resolve(obj_download.suggestedFilename());
            obj_download.saveAs(savePath);
            System.out.println("File Saved to "+savePath.toAbsolutePath());

            assertTrue(Files.exists(savePath),"File found");

            Locator status= obj_Page.locator("#status");
            assertThat(status).hasText("Download triggered: sample.txt");

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
