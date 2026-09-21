package ExplicitWait;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Arrays;

public class ExplicitWait {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                    .setArgs(Arrays.asList("--start-maximized")));
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/welcome.html");

            Locator message = obj_Page.locator("#message");
            long start = System.currentTimeMillis();
            message.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            String labelText= message.innerText();
            long elapsed=System.currentTimeMillis() - start;

            System.out.println("Label Text : "+labelText);
            System.out.println("Time Waited : "+elapsed + "ms");


            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
