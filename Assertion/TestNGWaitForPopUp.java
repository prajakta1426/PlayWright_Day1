package Assertion;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGWaitForPopUp {
    static final String Login_URL="file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html";
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")).setSlowMo(1000));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
    }

    @Test
    public void validateWaitForPopUp()
    {
        obj_Page.navigate(Login_URL);
        obj_Page.locator("[data-testid='username-input']").fill("validUser");
        obj_Page.locator("[data-testid='password-input']").fill("validPassword");
        Page new_page = obj_Page.waitForPopup(()->{
            obj_Page.getByTestId("submit-btn").click();
        });
        new_page.waitForLoadState();
        assertThat(new_page).hasURL("file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html?");
    }
    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
