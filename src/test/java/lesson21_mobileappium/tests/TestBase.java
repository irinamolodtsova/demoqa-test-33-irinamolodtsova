package lesson21_mobileappium.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lesson21_mobileappium.drivers.BrowserStackDriver;
import lesson21_mobileappium.drivers.EmulateMobileDriver;
import lesson21_mobileappium.drivers.RealMobileDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        Configuration.pageLoadTimeout = 0;
        Configuration.timeout = 10000;
        if (System.getProperty("deviceHost", "browserstack").equals("real")) {
            Configuration.browser = RealMobileDriver.class.getName();
        } else if (System.getProperty("deviceHost", "browserstack").equals("emulation")) {
            Configuration.browser = EmulateMobileDriver.class.getName();
        } else if (System.getProperty("deviceHost", "browserstack").equals("browserstack")) {
            Configuration.browser = BrowserStackDriver.class.getName();
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}