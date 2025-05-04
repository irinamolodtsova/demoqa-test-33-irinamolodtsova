package lesson21_mobileappium.tests;

import lesson21_mobileappium.pages.WikiSearchPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("AndroidStudio")
public class SearchTest extends TestBase {

    WikiSearchPage wikiSearchPage = new WikiSearchPage();

    @Test
    void successfulAppiumSearchTest() {
        wikiSearchPage.clickSearch();
        wikiSearchPage.searchData("Appium");
        wikiSearchPage.checkFoundResult();
    }

    @Test
    void unsuccessfulPageVisit() {
        wikiSearchPage.clickSearch();
        wikiSearchPage.searchData("Starbucks");
        wikiSearchPage.checkFoundResult();
        wikiSearchPage.clickToTheFoundPage();
        wikiSearchPage.checkAnErrorMessage();
    }
}
