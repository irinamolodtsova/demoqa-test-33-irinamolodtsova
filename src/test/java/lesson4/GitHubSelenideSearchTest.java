package lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class GitHubSelenideSearchTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void selenideWikiSearch() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $$(".markdown-body").shouldHave(texts("Soft assertions"));
        $(".markdown-body").$("[href='/selenide/selenide/wiki/SoftAssertions']").click();
        $$("#wiki-body").shouldHave(texts("""
                 @ExtendWith({SoftAssertsExtension.class})
                 class Tests {
                   @Test
                   void test() {
                     Configuration.assertionMode = SOFT;
                     open("page.html");
                     $("#first").should(visible).click();
                     $("#second").should(visible).click();
                   }
                 }"""));
    }
}
