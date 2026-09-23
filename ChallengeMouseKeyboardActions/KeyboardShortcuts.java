package ChallengeMouseKeyboardActions;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyboardShortcuts {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/keyboardShortcuts.html");
            System.out.println("Page Title: "+obj_Page.title());
            obj_Page.waitForLoadState();

            Locator sourceContainer = obj_Page.locator("#sourceContainer");
            sourceContainer.click();
            obj_Page.keyboard().press("Control+A");
            obj_Page.keyboard().press("Control+C");

            Locator targetContainer = obj_Page.locator("#targetContainer");
            targetContainer.click();
            obj_Page.keyboard().press("Control+V");

            assertThat(obj_Page.locator("#result")).hasText("Text copied successfully to Target !");

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
