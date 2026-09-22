package Assertion;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGLocaleCustomisation {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;
    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setLocale("ja-JP")
                .setViewportSize(null));
        obj_Page = obj_context.newPage();
    }
    @Test
    public void validateLocaleCustomisation()
    {
        obj_Page.navigate("https://wikipedia.com");
        System.out.println("Page Title: " + obj_Page.title());
        Locator obj_langlabel = obj_Page.locator("#jsLangLabel");
        String langValue = obj_langlabel.textContent();
        System.out.println("Language: " + langValue);

        assertThat(obj_langlabel).containsText("ja");
    }

    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
