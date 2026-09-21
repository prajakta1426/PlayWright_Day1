package GeolocationCustomisation;

import com.microsoft.playwright.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LocatorLocaleCustomisation {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                    .setArgs(Arrays.asList("--start-maximized")));

            //Australia/Sydney
            BrowserContext obj_context = obj_browser.newContext(new Browser.NewContextOptions().setTimezoneId("Australia/Sydney")
                    .setViewportSize(null));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("https://wikipedia.com");
            System.out.println("Page Title: "+obj_Page.title());
            Locator obj_langlabel=obj_Page.locator("#jsLangLabel");
            String langValue=obj_langlabel.textContent();
            System.out.println("Language: "+langValue);

            System.out.println(obj_Page.evaluate(
                    "()=> Intl.DateTimeFormat().resolvedOptions().timeZone"
            ));

            //Europe/Paris
            BrowserContext obj_context1 = obj_browser.newContext(new Browser.NewContextOptions().setTimezoneId("Europe/Paris").setViewportSize(null));
            Page obj_Page1 = obj_context1.newPage();
            obj_Page1.navigate("https://wikipedia.com");
            System.out.println("Page Title: "+obj_Page1.title());
            Locator obj_langlabel1=obj_Page1.locator("#jsLangLabel");
            String langValue1=obj_langlabel1.textContent();
            System.out.println("Language: "+langValue1);
            System.out.println(obj_Page1.evaluate(
                    "()=> Intl.DateTimeFormat().resolvedOptions().timeZone"
            ));

            //Asia/Kolkata
            BrowserContext obj_context2 = obj_browser.newContext(new Browser.NewContextOptions().setTimezoneId("Asia/Kolkata").setViewportSize(null));
            Page obj_Page2 = obj_context2.newPage();
            obj_Page2.navigate("https://wikipedia.com");
            System.out.println("Page Title: "+obj_Page2.title());
            Locator obj_langlabel2=obj_Page2.locator("#jsLangLabel");
            String langValue2=obj_langlabel2.textContent();
            System.out.println("Language: "+langValue2);
            System.out.println(obj_Page2.evaluate(
                    "()=> Intl.DateTimeFormat().resolvedOptions().timeZone"
            ));
            obj_Page.close();
            obj_Page1.close();
            obj_Page2.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

