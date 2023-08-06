import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class BrowserTest {
    @Before
    public void configureDriver() {
        String browser = System.getProperty("browser");
        if (browser != null && browser.equals("yandex")) { // Если значение параметра равно "yandex", то настроим тесты для запуска в браузере Yandex
            Configuration.browserSize = "1920x1080";
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\alex_\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
            DesiredCapabilities cup = new DesiredCapabilities();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\alex_\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            cup.setCapability(CAPABILITY, options);
            Configuration.browserCapabilities = cup;
            // Если значение параметра не равно "yandex", то настроим тесты для запуска в браузере Google Chrome
        } else {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";// Указываем, что тесты будут выполняться в Google Chrome
        }
    }
}