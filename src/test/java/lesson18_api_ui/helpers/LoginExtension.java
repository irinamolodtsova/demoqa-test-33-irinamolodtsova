package lesson18_api_ui.helpers;

import lesson18_api_ui.models.LoginResp;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import lesson18_api_ui.api.LoginApiSteps;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    public static LoginResp cookies;

    @Override
    public void beforeEach(ExtensionContext context) {
        cookies = LoginApiSteps.apiGetCookie();
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
    }
}
