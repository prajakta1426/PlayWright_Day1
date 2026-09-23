package JavaScriptAlerts;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JavascriptAlertWithPlayWright {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/javascriptAlerts.html");
            System.out.println("Page Title: "+obj_Page.title());

            //Simple Dialog - accept()
            //CLick on alert
            obj_Page.onceDialog(dialog -> {
                System.out.println("Alert text : "+dialog.message());
                dialog.accept();
            });
            obj_Page.click("#alertBtn");
            obj_Page.waitForTimeout(1000);

            //Confirmation Dialog - accept()
            //Click on Ok to Confirm
            Locator alertResult = obj_Page.locator("#alertResult");
            assertThat(alertResult).containsText("Alert was shown and accepted.");


            obj_Page.onceDialog(dialog -> {
                System.out.println("Confirm Text: "+dialog.message());
                dialog.accept();
            });
            obj_Page.click("#confirmBtn");
            obj_Page.waitForTimeout(1000);

            Locator confirmResult = obj_Page.locator("#confirmResult");
            assertThat(confirmResult).containsText("You clicked OK.");

            //Prompt Dialog - dismiss()
            //Clicked on cancel
            obj_Page.onceDialog(Dialog::dismiss);

            obj_Page.click("#confirmBtn");
            obj_Page.waitForTimeout(1000);
            assertThat(confirmResult).containsText("You clicked Cancel.");


            //Prompt Dialog - accept()
            obj_Page.onceDialog(dialog -> {
                System.out.println("Confirm Text: "+dialog.message());
                dialog.accept("This is Playwright");
            });
            obj_Page.click("#promptBtn");
            obj_Page.waitForTimeout(1000);

            Locator promptResult = obj_Page.locator("#promptResult");
            assertThat(promptResult).containsText("You entered: This is Playwright");

            //Prompt Dialog - dismiss()
            obj_Page.onceDialog(Dialog::dismiss);
            obj_Page.click("#promptBtn");
            obj_Page.waitForTimeout(1000);

            assertThat(promptResult).containsText("Prompt was dismissed.");

            obj_Page.close();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
