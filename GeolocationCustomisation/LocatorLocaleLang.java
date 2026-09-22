package GeolocationCustomisation;

import com.microsoft.playwright.*;

import java.util.Arrays;

public class LocatorLocaleLang {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                    .setArgs(Arrays.asList("--start-maximized")));

            //Australia/Sydney
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setLocale("ja-JP")
                    .setViewportSize(null));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("https://wikipedia.com");
            System.out.println("Page Title: "+obj_Page.title());
            Locator obj_langlabel=obj_Page.locator("#jsLangLabel");
            String langValue=obj_langlabel.textContent();
            System.out.println("Language: "+langValue);



            obj_Page.close();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
