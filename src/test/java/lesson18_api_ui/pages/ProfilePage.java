package lesson18_api_ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement userNameLabel = $("#userName-value");

    @Step("Check user name auth")
    public void usernameLabel(String userName){
        userNameLabel.shouldHave(text(userName));

    }
}
