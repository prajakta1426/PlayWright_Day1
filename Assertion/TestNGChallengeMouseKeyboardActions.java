package Assertion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestNGChallengeMouseKeyboardActions {
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
    }

    @Test
    public void testUploadDocumentsSection()
    {
        obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/challenge_MouseKeyboardActions.html");
        obj_Page.waitForLoadState();
        Locator documentsMenu= obj_Page.locator("#documentsMenu");
        documentsMenu.hover();

        Locator uploadDocument = obj_Page.getByText("Upload Document");
        uploadDocument.waitFor();
        uploadDocument.click();

        Path filePath = Paths.get("C:\\Users\\CCST\\Desktop\\Playwright Material\\PlaywrightMaterial\\FileForUpload.txt");
        Locator fileInput = obj_Page.locator("#fileInput");
        fileInput.setInputFiles(filePath);
        obj_Page.waitForLoadState();

        assertThat(obj_Page.locator("#fileName")).hasText("Selected file: FileForUpload.txt");

        obj_Page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Upload")).click();
        obj_Page.waitForLoadState();

        assertThat(obj_Page.locator("#result")).hasText("You greedy fellow !!");
    }
    @AfterMethod
    public void teardown()
    {
        obj_Page.close();
    }
}
