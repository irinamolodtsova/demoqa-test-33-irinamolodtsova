package lesson19_owner.tests;

import com.codeborne.selenide.Configuration;
import lesson19_owner.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.pollingInterval = 400;
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        if (config.isRemote()) {
            Configuration.remote = config.getRemoteUrl();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        open(Configuration.baseUrl);
        Configuration.timeout = 7000;
    }

}
