package FileUpload;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileUpload {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/fileUpload.html");
            obj_Page.waitForLoadState();
            System.out.println("Page Title: "+obj_Page.title());

            String filePath = "C:/Users/CCST/Desktop/SeleniumMaterial/actionClass_Menu.html";

            obj_Page.setInputFiles("#fileInput", Paths.get(filePath));
            Locator uploadBtn = obj_Page.locator("#uploadBtn");
            uploadBtn.click();

            Locator result = obj_Page.locator("#result");
            result.waitFor();
            assertThat(result).hasText("File 'actionClass_Menu.html' uploaded successfully!");

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
