package lesson10.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class GitHubSearch {

    private final SelenideElement searchField = $(".header-search-button"),
            searchInputField = $("#query-builder-test"),
            foundRepositoryEroshenkoAllureExample = $(linkText("eroshenkoam/allure-example")),
            issueTab = $("#issues-tab"),
            issueID = $(withText("95"));

    public GitHubSearch openPage(String pageName) {
        open(pageName);
        return this;
    }

    public GitHubSearch searchFieldClick() {
        searchField.click();
        return this;
    }

    public GitHubSearch searchInputFieldSendKeys(String value) {
        searchInputField.sendKeys(value);
        return this;
    }

    public GitHubSearch searchInputFieldSubmit() {
        searchInputField.submit();
        return this;
    }

    public GitHubSearch foundRepositoryEroshenkoAllureExampleClick() {
        foundRepositoryEroshenkoAllureExample.click();
        return this;
    }

    public GitHubSearch issueTabClick() {
        issueTab.click();
        return this;
    }

    public GitHubSearch issueIDShouldExist() {
        issueID.should(Condition.exist);
        return this;
    }

    public GitHubSearch selenideLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        return this;
    }

    public GitHubSearch addSnapshotPage() {
        Allure.getLifecycle().addAttachment(
                "Исходники страницы",
                "text/html",
                "html",
                WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
        );
        return this;
    }

    @Attachment(value = "Скриншот страницы", type = "image/png", fileExtension = "png")
    public byte[]  attachScreenshot() {
        sleep(2000);
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
