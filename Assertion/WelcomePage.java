package Assertion;

import com.microsoft.playwright.*;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WelcomePage {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                    .setArgs(Arrays.asList("--start-maximized")));
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/welcome.html");
            obj_Page.waitForLoadState();

            //Assertion starts here
            Locator nameField = obj_Page.locator("#nameField");
            assertThat(nameField).isDisabled();
            System.out.println("Name Field is disabled");

            obj_Page.locator("#enterNameBtn").click();
            System.out.println("Enter Name button is clicked");

            assertThat(nameField).isEnabled();;
            System.out.println("Name Field is enabled");

            obj_Page.close();

        } catch (RuntimeException | AssertionError e) {
            throw new RuntimeException(e);
        }

    }
}
