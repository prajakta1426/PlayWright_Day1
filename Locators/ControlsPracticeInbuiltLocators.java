package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class ControlsPracticeInbuiltLocators {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html");

            obj_Page.getByLabel("Username").fill("admin");
            obj_Page.getByLabel("Password").fill("admin");
            System.out.println("PASS :Filled Field using getlabel().");

            String paragraphtext=obj_Page.getByText("Forgot your password?").textContent();
            System.out.println("Pass : Found paragraph text using getByText(). "+paragraphtext);

            Page obj_controlsPage= obj_context.waitForPage(() -> {
                obj_Page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In")).click();
            });
            System.out.println("Pass :  Clicked Button using getByRoleOptions().");

            String pageTitle = obj_controlsPage.title();
            if(pageTitle.contains("Student Performance Report"))
            {
                System.out.println("Test passed : URL is "+pageTitle);
            }
            else
                System.out.println("Test fail : URL is "+pageTitle);







            obj_Page.waitForTimeout(2000);

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
