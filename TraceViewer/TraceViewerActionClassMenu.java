package TraceViewer;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TraceViewerActionClassMenu {

    public static void main(String[] args) {
        Browser obj_browser = null;
        BrowserContext obj_context = null;
        String pathFile = "C:\\Users\\CCST\\Desktop\\Playwright Material\\PlaywrightMaterial\\trace.zip";
        try(Playwright obj_playwright = Playwright.create())
        {
            obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            obj_context = obj_browser.newContext();
            obj_context.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setLive(true)
                    .setSources(true));
            Page obj_Page = obj_context.newPage();
            obj_Page.navigate("C:/Users/CCST/Desktop/SeleniumMaterial/actionClass_Menu.html");
            System.out.println("Page Title: "+obj_Page.title());
            Locator productsMenuLocator=obj_Page.locator("#productsMenu");
            productsMenuLocator.hover();

            //for laptop link
            Locator laptopsSubMenu = obj_Page.locator("#laptopsLink");
            laptopsSubMenu.waitFor();
            laptopsSubMenu.click();

            Locator result = obj_Page.locator("#result");
            result.waitFor();
            assertThat(result).hasText("You clicked: Laptops");

            //for phones link
            productsMenuLocator.hover();
            Locator phonesSubMenu = obj_Page.locator("#phonesLink");
            phonesSubMenu.waitFor();
            phonesSubMenu.click();

            assertThat(result).hasText("You clicked: Phones");

            //for tablet link
            productsMenuLocator.hover();
            Locator tabletSubMenu = obj_Page.locator("#tabletsLink");
            tabletSubMenu.waitFor();
            tabletSubMenu.click();

            assertThat(result).hasText("You clicked: Tablets");

            //Serives menu
            //for support link
            Locator serviceMenu = obj_Page.locator("#servicesMenu");
            serviceMenu.hover();

            Locator supportSubMenu = obj_Page.locator("#supportLink");
            supportSubMenu.waitFor();
            supportSubMenu.click();

            assertThat(result).hasText("You clicked: Support");

            //for training link
            Locator trainingSubMenu = obj_Page.locator("#trainingLink");
            trainingSubMenu.waitFor();
            trainingSubMenu.click();

            assertThat(result).hasText("You clicked: Training");
            obj_context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(pathFile)));

            obj_Page.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
