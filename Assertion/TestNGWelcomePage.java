package Assertion;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGWelcomePage {
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
    public void validateWelcomePage()
    {
        obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/welcome.html");
        obj_Page.waitForLoadState();
        Locator nameField = obj_Page.locator("#nameField");
        assertThat(nameField).isDisabled();
        System.out.println("Name Field is disabled");

        obj_Page.locator("#enterNameBtn").click();
        System.out.println("Enter Name button is clicked");

        assertThat(nameField).isEnabled();;
        System.out.println("Name Field is enabled");
    }
    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
