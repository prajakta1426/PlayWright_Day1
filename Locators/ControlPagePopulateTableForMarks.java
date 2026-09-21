package Locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class ControlPagePopulateTableForMarks {
    public static void main(String[] args) {
        try(Playwright playwright =Playwright.create()) {

            //Launch browser in headed mode
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //Create browser context object
            BrowserContext context = browser.newContext();
            Page obj_Page = context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html");
            obj_Page.waitForLoadState();

            //By value
            Locator moduleDropdown = obj_Page.locator("#module");
            Locator option = moduleDropdown.locator("option");
            Locator target_val = option.filter(new Locator.FilterOptions().setHasText("CCST"));

            String value = target_val.getAttribute("value");
            moduleDropdown.selectOption(value);
            Thread.sleep(1000);
            System.out.println("Select option visible: "+ moduleDropdown.isVisible());

            //Locators Chaining
            Locator rows = obj_Page.locator("table").locator("tbody").locator("tr");
            Locator rowWithMarksInput =rows.filter(new Locator.FilterOptions().setHas(obj_Page.locator("input[type='number']")));

            List<Locator> inputs = rowWithMarksInput.locator("input[type='number']").all();
            for (Locator input : inputs) {
                input.fill("95");
            }

            Locator saveBtn = obj_Page.locator("#saveButton");
            System.out.println("Save Button enabled: "+saveBtn.isEnabled());

            saveBtn.click();

            obj_Page.waitForTimeout(2000);
            obj_Page.close();
            obj_Page.close();

        } catch (Exception e) {
            System.out.println("Test failed");
            e.printStackTrace();
        }
    }
}
