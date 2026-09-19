package TestDrivingLicense;

import com.microsoft.playwright.*;

public class CheckboxAndTextFieldsValidation {

    static String fullname = "Harry Potter";
    static String address = "London";
    static String age = "37";
    static String placeofbirth = "Hogwarts";

    static String obj_fullname= "#fullname";
    static String obj_address="#address";
    static String obj_age="#age";
    static String obj_placeofbirth="#placeofbirth";
    static String obj_gender="#Male";
    static String obj_color_no="//input[@name='color_no']";
    static String obj_submit="//button[@type='submit']";

    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:\\Users\\CCST\\Desktop\\Playwright Material\\PlaywrightMaterial\\TestcasesClassAssignment-drivingLicenseUI.html");
            obj_Page.waitForTimeout(2000);

            obj_Page.locator(obj_fullname).fill(fullname);
            obj_Page.locator(obj_address).fill(address);
            obj_Page.locator(obj_age).fill(age);
            obj_Page.locator(obj_placeofbirth).fill(placeofbirth);
            //obj_Page.locator(obj_gender).click();
            obj_Page.locator(obj_gender).check();
            obj_Page.locator(obj_color_no).check();
            System.out.println("All details are submitted");

            Page obj_controlsPage= obj_context.waitForPage(() -> {
                obj_Page.locator(obj_submit).click();
            });

            String pageURL = obj_controlsPage.url();
            if(pageURL.contains("welcome.html"))
            {
                System.out.println("Test passed : URL is "+pageURL);
            }
            else
                System.out.println("Test fail : URL is "+pageURL);
            obj_Page.close();

        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
