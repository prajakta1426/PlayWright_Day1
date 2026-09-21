package Locators;

import com.microsoft.playwright.*;

import java.util.Iterator;
import java.util.List;

public class TestDragToViaLoop {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/LocatorFiltering.html");
            obj_Page.waitForLoadState();

            Locator sourcelocator = obj_Page.locator("#sourceContainer");
            Locator targetlocator = obj_Page.locator("#targetContainer");
            Locator item1locator = obj_Page.locator("#item1");
            Locator item2locator = obj_Page.locator("#item2");
            Locator item3locator = obj_Page.locator("#item3");

            Locator itemList= obj_Page.locator(".draggable-item");
/*            for(int i=0;i<itemList.count();i++){
                itemList.first().dragTo(targetlocator);
                Thread.sleep(1000);
            }*/

            List<Locator> allItems = itemList.all();
            for(int i=0;i<allItems.size();i++){
                Locator item = sourcelocator.locator(".draggable-item").first();
                System.out.println(item);
                item.dragTo(targetlocator);
                obj_Page.waitForLoadState();
            }

            System.out.println("Item 1 is in target container "+targetlocator.innerHTML().contains(item1locator.textContent()));
            System.out.println("Item 2 is in target container "+targetlocator.innerHTML().contains(item2locator.textContent()));
            System.out.println("Item 3 is in target container "+targetlocator.innerHTML().contains(item3locator.textContent()));
            System.out.println("There are "+targetlocator.locator(".draggable-item").count()+" items in target container");
            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
