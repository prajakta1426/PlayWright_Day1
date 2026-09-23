package ChallengeMouseKeyboardActions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ChallengeMouseKeyboardActions {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/challenge_MouseKeyboardActions.html");
            System.out.println("Page Title: "+obj_Page.title());
            obj_Page.waitForLoadState();

            Locator documentsMenu= obj_Page.locator("#documentsMenu");
            documentsMenu.hover();

            Locator uploadDocument = obj_Page.getByText("Upload Document");
            uploadDocument.waitFor();
            uploadDocument.click();

            Path filePath = Paths.get("C:\\Users\\CCST\\Desktop\\Playwright Material\\PlaywrightMaterial\\FileForUpload.txt");
            Locator fileInput = obj_Page.locator("#fileInput");
            fileInput.setInputFiles(filePath);
            obj_Page.waitForLoadState();

            assertThat(obj_Page.locator("#fileName")).hasText("Selected file: FileForUpload.txt");

            obj_Page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Upload")).click();
            obj_Page.waitForLoadState();

            assertThat(obj_Page.locator("#result")).hasText("You greedy fellow !!");

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
