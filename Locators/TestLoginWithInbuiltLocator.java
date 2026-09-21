package Locators;

import com.microsoft.playwright.*;

public class TestLoginWithInbuiltLocator {
    static final String Login_URL="file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html";
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_context = obj_browser.newContext();

            Page obj_Page = obj_context.newPage();
            Thread.sleep(2000);
            obj_Page.navigate(Login_URL);
            obj_Page.getByPlaceholder("Enter username").fill("validUser");
            obj_Page.getByTestId("password-input").fill("validPassword");
            Thread.sleep(2000);
            Page obj_controlsPage= obj_context.waitForPage(() -> {
                obj_Page.getByText("Sign In").click();
            });
            Thread.sleep(2000);
            obj_controlsPage.waitForLoadState();

            obj_Page.waitForTimeout(2000);
            String url=obj_controlsPage.url();
            System.out.println("Navigated to :"+url);
            if(!url.contains("ControlsPractice.html")){
                throw new AssertionError("Navigation Failed. Actual url :"+url);
            }
            String title=obj_controlsPage.title();
            System.out.println("Page title: "+title);
            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
