package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class LocatorFiltering {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/LocatorFiltering.html");
            obj_Page.waitForLoadState();

            Locator sourcelocator = obj_Page.locator(".container")
                            .filter(new Locator.FilterOptions()
                                    .setHas(obj_Page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("TO DO"))));

            Locator targetlocator = obj_Page.locator(".container")
                    .filter(new Locator.FilterOptions()
                            .setHas(obj_Page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Done"))));

            System.out.println("Source container visible : "+sourcelocator.isVisible());
            System.out.println("Target container visible : "+targetlocator.isVisible());

            Locator item1locator = obj_Page.locator("#item1");
            System.out.println("Item to drag : "+item1locator.innerText());

            item1locator.dragTo(targetlocator);
            obj_Page.waitForTimeout(500);

            String resultText = obj_Page.locator("#result").innerText();
            System.out.println("Result text:"+resultText);

            if(!resultText.equals("Write Selenium Tests moved to Done")){
                throw new AssertionError("Unexpected result : "+resultText);
            }
            else
                System.out.println("Test failed");

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
