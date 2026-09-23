package Assertion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGiFrames {
    Playwright obj_playwright;
    Browser obj_browser;
    BrowserContext obj_context;
    Page obj_Page;

    @BeforeMethod
    public void setUp(){
        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        obj_context = obj_browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        obj_Page = obj_context.newPage();
        obj_Page.navigate("file:///C:/Users/CCST/Desktop/SeleniumMaterial/iFrameDemo.html");
        obj_Page.waitForLoadState();
    }

    @Test
    public void testMainButton()
    {
        Locator mainBtn = obj_Page.locator("#mainBtn");
        assertThat(mainBtn).isVisible();
    }

    @Test
    public void frame1AccessByIndex()
    {
        FrameLocator obj_frame1 = obj_Page.frameLocator("iframe").nth(0);
        Locator frame1Btn = obj_frame1.locator("#frame1Btn");
        frame1Btn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        frame1Btn.click();
        obj_Page.waitForTimeout(1000);

        Locator frameResult = obj_frame1.locator("#frame1Result");
        assertThat(frameResult).containsText("Frame 1 button clicked!");
    }

    @Test
    public void frame2AccessByName()
    {
        FrameLocator obj_frame2 = obj_Page.frameLocator("iframe[name='frameByName']");
        Locator frame2Input = obj_frame2.locator("#frame2Input");
        frame2Input.fill("This is Playwright");
        obj_Page.waitForTimeout(1000);

        String enteredValue = frame2Input.inputValue();
        assertThat(frame2Input).hasValue(enteredValue);
    }

    @Test
    public void frame3AccessByElementID()
    {
        FrameLocator obj_frame3 = obj_Page.frameLocator("#frame3");
        Locator frameDropdown = obj_frame3.locator("#frame3Dropdown");
        frameDropdown.selectOption("One");
        obj_Page.waitForTimeout(1000);

        String selectedValue = frameDropdown.locator("option:checked").textContent();
        assertThat(frameDropdown).containsText(selectedValue);
    }
    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
