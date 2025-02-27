package lesson12_jenkinsParams.pages.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lesson12_jenkinsParams.pages.components.ConfirmationWindowComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("[data-testid = 'firstName']").$(".MuiInputBase-input.MuiOutlinedInput-input"),
            lastNameInput = $("[data-testid = 'lastName']").$(".MuiInputBase-input.MuiOutlinedInput-input"),
            genderWrapper = $("[role = 'radiogroup']"),
            userNumber = $("[data-testid = 'phone']"),
            userEmail = $("[data-testid = 'email']").$(".MuiInputBase-input.MuiOutlinedInput-input"),
            submitButton = $("[type = 'submit']"),
            registrationConfirmationWindow = $(".MuiTypography-root.MuiTypography-body1.css-1qye57c"),
            banner = $("[data-testid = 'ClearIcon'");

    ConfirmationWindowComponent confirmationWindow = new ConfirmationWindowComponent();

    @Step("Open Page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    @Step("Remove Banners")
    public RegistrationPage removeBanners() {
        banner.click();

        return this;
    }

    @Step("Set First Name")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Set Last Nane")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Set User Number")
    public RegistrationPage setUserNumber(String value) {
        userNumber.setValue(value);

        return this;
    }

    @Step("Set User Email")
    public RegistrationPage setUserEmail(String value) {
        userEmail.setValue(value);

        return this;
    }

    @Step("Set Gender")
    public RegistrationPage chooseGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Submit registration")
    public RegistrationPage submitRegistration() {

        submitButton.scrollTo();
        submitButton.click();
        firstNameInput.scrollTo();


        return this;
    }


    @Step("Check registration results")
    public RegistrationPage checkResultOfRegistration(int num, String value) {
        confirmationWindow.checkResult(num, value);

        return this;
    }
}
