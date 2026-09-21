package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class TestDragTo {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/LocatorFiltering.html");
            obj_Page.waitForLoadState();

            //Locator sourcelocator = obj_Page.locator("#sourceContainer");
            Locator targetlocator = obj_Page.locator("#targetContainer");
            Locator item1locator = obj_Page.locator("#item1");
            Locator item2locator = obj_Page.locator("#item2");
            Locator item3locator = obj_Page.locator("#item3");

            item1locator.dragTo(targetlocator);
            item2locator.dragTo(targetlocator);
            item3locator.dragTo(targetlocator);
            Thread.sleep(1000);

            System.out.println("Item 1 is in target container "+targetlocator.innerHTML().contains(item1locator.textContent()));
            System.out.println("Item 2 is in target container "+targetlocator.innerHTML().contains(item2locator.textContent()));
            System.out.println("Item 3 is in target container "+targetlocator.innerHTML().contains(item3locator.textContent()));

            String item1 =  obj_Page.locator("#item1").textContent();
            String item2 =  obj_Page.locator("#item2").textContent();
            String item3 =  obj_Page.locator("#item3").textContent();

            System.out.println("Item 1 is in target container "+targetlocator.innerHTML().contains(item1));
            System.out.println("Item 2 is in target container "+targetlocator.innerHTML().contains(item2));
            System.out.println("Item 3 is in target container "+targetlocator.innerHTML().contains(item3));

            obj_Page.close();

        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
