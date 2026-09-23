package iFrame;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IframeDemoWithPlayWright {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("file:///C:/Users/CCST/Desktop/SeleniumMaterial/iFrameDemo.html");
            System.out.println("Page Title: "+obj_Page.title());

            //Main Button
            Locator mainBtn = obj_Page.locator("#mainBtn");
            assertThat(mainBtn).isVisible();

            //First frame
            FrameLocator obj_frame1 = obj_Page.frameLocator("iframe").nth(0);
            Locator frame1Btn = obj_frame1.locator("#frame1Btn");
            frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            frame1Btn.click();
            obj_Page.waitForTimeout(1000);

            Locator frameResult = obj_frame1.locator("#frame1Result");
            assertThat(frameResult).containsText("Frame 1 button clicked!");

            //Second Frame
            FrameLocator obj_frame2 = obj_Page.frameLocator("iframe[name='frameByName']");
            Locator frame2Input = obj_frame2.locator("#frame2Input");
            frame2Input.fill("This is Playwright");
            obj_Page.waitForTimeout(1000);

            String enteredValue = frame2Input.inputValue();
            assertThat(frame2Input).hasValue(enteredValue);

            //Third Frame
            FrameLocator obj_frame3 = obj_Page.frameLocator("#frame3");
            Locator frameDropdown = obj_frame3.locator("#frame3Dropdown");
            frameDropdown.selectOption("One");
            obj_Page.waitForTimeout(1000);

            String selectedValue = frameDropdown.locator("option:checked").textContent();
            assertThat(frameDropdown).containsText(selectedValue);

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
