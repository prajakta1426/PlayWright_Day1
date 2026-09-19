package SetupBrowserBinaries;

import com.microsoft.playwright.CLI;

import java.io.IOException;
import java.net.URISyntaxException;

public class SetupBrowser {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        CLI.main(new String[]{"install"});
    }
}
