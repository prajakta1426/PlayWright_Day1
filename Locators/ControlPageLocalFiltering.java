package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

public class ControlPageLocalFiltering {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html");
            obj_Page.waitForLoadState();

            Locator modulesDD = obj_Page.locator("#module");
            Locator options = modulesDD.locator("option").filter(new Locator.FilterOptions().setHasText("CCST"));
            System.out.println(modulesDD);
/*            Locator modulesDD = obj_Page.locator("#module");
            Locator options = modulesDD.locator("option");
            Locator target_value = options.filter(new Locator.FilterOptions().setHasText("CCST"));*/
            obj_Page.locator("#module").click();
            obj_Page.waitForTimeout(1000);
            System.out.println(options.getAttribute("value"));
            String value = options.getAttribute("value");
            System.out.println(value);
            modulesDD.selectOption(value);
            obj_Page.waitForTimeout(5000);

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
