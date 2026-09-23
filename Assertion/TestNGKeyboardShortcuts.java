package Assertion;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGKeyboardShortcuts {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
    }

    @Test
    public void validateKeyboardShortcuts()
    {
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
    }


    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
