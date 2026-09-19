package TestLoginPage;

import com.microsoft.playwright.*;

import java.util.Map;

public class TestLoginGeolocationWithPlayWright {
    static final String Login_URL="file:///C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html";
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            BrowserContext obj_desktopSafariContext = obj_browser.newContext(new Browser.NewContextOptions()
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15")
                    .setViewportSize(1280, 720)
                    .setDeviceScaleFactor(2)
                    .setIsMobile(false)
                    .setHasTouch(false)
                            .setGeolocation(48.8566,2.3522)
                    );

            Page obj_Page = obj_desktopSafariContext.newPage();
            Thread.sleep(2000);
            obj_Page.navigate(Login_URL);
            Thread.sleep(5000);

            Map<String, Object> location = (Map<String,Object>) obj_Page.evaluate(
                    "()=> new Promise((resolve,reject) => {"
                            +"navigator.geolocation.getCurrentPosition("
                    +"p=>resolve({ lat: p.coords.latitude, lng: p.coords.longitude}), "
                    +"e=> reject(e.message));})"
            );

            double actualLat= (double) location.get("lat");
            double actualLng= (double) location.get("lng");

            System.out.println("Actual lat : "+actualLat+" Actual lng : "+actualLng);

            obj_Page.close();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
