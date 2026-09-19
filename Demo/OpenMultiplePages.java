package Demo;

import com.microsoft.playwright.*;

public class OpenMultiplePages {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("https://example.com");
            System.out.println("Page Title: "+obj_Page.title());

            obj_Page.waitForTimeout(2000);
            BrowserContext newobj_context = obj_browser.newContext();
            Page newobj_Page = newobj_context.newPage();
            newobj_Page.navigate("https://www.google.com");
            System.out.println("Page Title: "+newobj_Page.title());

            obj_Page.waitForTimeout(2000);

            obj_Page.close();
            newobj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
