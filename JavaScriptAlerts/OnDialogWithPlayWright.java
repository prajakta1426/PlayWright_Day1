package JavaScriptAlerts;

import com.microsoft.playwright.*;

import java.util.concurrent.atomic.AtomicInteger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OnDialogWithPlayWright {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/javascriptAlerts.html");
            System.out.println("Page Title: "+obj_Page.title());

            AtomicInteger confirmCount =new AtomicInteger(0);
            AtomicInteger promptCount =new AtomicInteger(0);

            obj_Page.onDialog(dialog -> {
                System.out.println("Dialog type "+dialog.type()+" message : "+dialog.message());
                switch (dialog.type()) {
                    case "alert":
                        dialog.accept();
                        break;

                    case  "confirm":
                        if(confirmCount.getAndIncrement()==0){
                            dialog.accept();
                        }
                        else {
                            dialog.dismiss();
                        }
                        break;

                    case "prompt":
                        if(promptCount.getAndIncrement()==0){
                            dialog.accept("This is Playwright");
                        }
                        else {
                            dialog.dismiss();
                        }
                        break;

                    default:
                        dialog.dismiss();
                        break;

                }
            });

            //Simple Dialog - accept()
            //CLick on alert
            obj_Page.click("#alertBtn");
            Locator alertResult = obj_Page.locator("#alertResult");
            assertThat(alertResult).containsText("Alert was shown and accepted.");

            //Confirmation Dialog - accept()
            //Click on Ok to Confirm
            obj_Page.click("#confirmBtn");
            Locator confirmResult = obj_Page.locator("#confirmResult");
            assertThat(confirmResult).containsText("You clicked OK.");

            //Prompt Dialog - dismiss()
            //Clicked on cancel
            obj_Page.click("#confirmBtn");
            assertThat(confirmResult).containsText("You clicked Cancel.");

            //Prompt Dialog - accept()

            obj_Page.click("#promptBtn");
            Locator promptResult = obj_Page.locator("#promptResult");
            assertThat(promptResult).containsText("You entered: This is Playwright");

            //Prompt Dialog - dismiss()
            obj_Page.click("#promptBtn");
            assertThat(promptResult).containsText("Prompt was dismissed.");

            obj_Page.close();


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
