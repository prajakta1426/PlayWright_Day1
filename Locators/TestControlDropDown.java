package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class TestControlDropDown {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html");

            Locator selectModules = obj_Page.locator("#module");
            selectModules.selectOption("CCST");
            System.out.println("The selected value is : "+selectModules.inputValue());
            Thread.sleep(2000);
            selectModules.selectOption(new SelectOption().setLabel("DAI"));
            System.out.println("The selected value is : "+selectModules.inputValue());
            Thread.sleep(2000);
            selectModules.selectOption(new SelectOption().setIndex(3));
            System.out.println("The selected value is : "+selectModules.inputValue());
            Thread.sleep(2000);

            obj_Page.close();

        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
