package Locators;

import com.microsoft.playwright.*;

public class ControlPageLocatorChaining {
    public static void main(String[] args) throws InterruptedException {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html");
            obj_Page.waitForTimeout(1000);

            Locator selectModules = obj_Page.locator("#module");
            selectModules.selectOption("CCST");
            obj_Page.waitForTimeout(1000);

            Locator rows =obj_Page.locator("table").locator("tbody").locator("tr");
            int rowCount= rows.count();

            for(int i=0;i<rowCount;i++){
                Locator row = rows.nth(i);
                Locator marksInput=row.locator("input[type='number']");
                marksInput.fill("90");
                System.out.println("Added marks to : "+row.textContent());
            }
            Locator savebtn= obj_Page.locator("#saveButton");
            System.out.println("Save Button is Enabled "+savebtn.isEnabled());
            savebtn.click();
            System.out.println("Save button is clicked");
            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
