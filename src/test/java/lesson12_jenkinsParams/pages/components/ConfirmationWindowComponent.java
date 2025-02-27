package lesson12_jenkinsParams.pages.components;

import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Selenide.$$;

public class ConfirmationWindowComponent {

    public ConfirmationWindowComponent checkResult(int num, String value) {
        $$(".MuiTypography-root.MuiTypography-body1.css-1qye57c").get(num)
                .shouldHave(text(value));

        return this;
    }
}
