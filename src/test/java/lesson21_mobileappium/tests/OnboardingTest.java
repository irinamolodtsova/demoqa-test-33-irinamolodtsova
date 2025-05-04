package lesson21_mobileappium.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("AndroidStudio")
public class OnboardingTest extends TestBase {

    @Test
    void gettingStartedTest() {
        step("Первый экран и клик", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(visible);
        });
        step("Второй экран и клик", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(visible);
        });
        step("Третий экран и клик", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"))
                    .click();
            $(id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(visible);
        });
        step("Четвертый экран и клик", () -> {
            $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"))
                    .click();
            $(id("org.wikipedia.alpha:id/search_container"))
                    .shouldHave(visible);
        });

    }
}