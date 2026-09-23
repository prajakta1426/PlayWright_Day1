package TestLoginPage;

import com.microsoft.playwright.*;

public class TestLoginWaitForPopUp {
    static final String Login_URL="file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html";
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_desktopSafariContext = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15")
                    .setViewportSize(1280, 720)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(false)
                    .setHasTouch(false)
            );

            Page obj_Page = obj_desktopSafariContext.newPage();
            Thread.sleep(2000);
            obj_Page.navigate(Login_URL);
            obj_Page.locator("[data-testid='username-input']").fill("validUser");
            obj_Page.locator("[data-testid='password-input']").fill("validPassword");
            Thread.sleep(2000);
            Page new_page = obj_Page.waitForPopup(()->{
                obj_Page.getByTestId("submit-btn").click();
            });
            Thread.sleep(2000);
            new_page.waitForLoadState();

            obj_Page.waitForTimeout(2000);
            String url=new_page.url();
            System.out.println("Navigated to :"+url);
            if(!url.contains("ControlsPractice.html")){
                throw new AssertionError("Navigation Failed. Actual url :"+url);
            }
            String title=new_page.title();
            System.out.println("Page title: "+title);
            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
