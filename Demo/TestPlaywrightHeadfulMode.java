package Demo;

import com.microsoft.playwright.*;

public class TestPlaywrightHeadfulMode {
    public static void main(String[] args) {
        //try with resources to close object
        try(Playwright obj_playwright = Playwright.create())
        {
            //create Chrome browser in headful mode (Chrome window is popped up when test starts)
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //create browser context object to maintain separation different window, and it doesn't affect other
            BrowserContext obj_context = obj_browser.newContext();
            //obj_context creates a new tab inside a page
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("https://example.com");
            System.out.println("Page Title: "+obj_Page.title());
            System.out.println("Browser Version : "+obj_browser.version());
            System.out.println("Playwright installation is done");

            obj_Page.waitForTimeout(2000);
            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
