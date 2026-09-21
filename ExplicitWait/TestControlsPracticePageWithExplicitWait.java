package ExplicitWait;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;


public class TestControlsPracticePageWithExplicitWait {
    public static void main(String[] args) {
        try(Playwright playwright =Playwright.create()) {

            //Launch browser in headed mode
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            //Create browser context object
            BrowserContext context = browser.newContext();
            //Create Page Object
            Page page = context.newPage();
            page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/ControlsPractice.html");

            Locator rows = page.locator("table tbody tr");

            int intialrowCount = rows.count();
            System.out.println("Intial Count "+intialrowCount);


            Locator moduleDropdown = page.locator("#module");
            moduleDropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            moduleDropdown.selectOption(new SelectOption().setLabel("CCST"));
            System.out.println("Selected 'CCST' from dropdown");


            rows.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            int updatedCount = rows.count();
            System.out.println("new Count: "+updatedCount);

            if(intialrowCount != updatedCount){
                System.out.println("Test Pass: Row handled ");
            }else {
                System.out.println("Test failed: Row Mismatched");
            }


            Locator saveBtn = page.locator("#saveButton");
            System.out.println("Save Button enabled: "+saveBtn.isEnabled());

            saveBtn.click();

            page.waitForTimeout(2000);
            page.close();
            page.close();

        } catch (Exception e) {
            System.out.println("Test failed");
            e.printStackTrace();
        }
    }
}

