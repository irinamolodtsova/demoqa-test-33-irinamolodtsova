package lesson12_jenkinsParams.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static lesson12_jenkinsParams.helpers.JenkinsProperties.*;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://app.qa.guru/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        Configuration.browser = getBrowser();
        Configuration.browserVersion = getBrowserVersion();
        Configuration.browserSize = getBrowserSize();
        Configuration.remote = getServer();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
