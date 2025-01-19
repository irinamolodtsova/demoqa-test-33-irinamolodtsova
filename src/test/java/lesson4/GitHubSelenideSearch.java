package lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.*;

public class GitHubSelenideSearch {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void SelenideWikiSearch() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $$(".markdown-body").shouldHave(texts("Soft assertions"));
        $(".markdown-body").$("[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $$(".markdown-body").shouldHave(texts("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));
    }
}
