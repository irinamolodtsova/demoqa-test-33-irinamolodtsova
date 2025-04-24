package lesson20_mobile.tests;

import lesson20_mobile.pages.WikiPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("mobile")
public class SearchTest extends TestBase {

    WikiPage wikipage = new WikiPage();

    @Test
    void successfulSearchTest() {
        wikipage.clickSearch();
        wikipage.searchData("Appium");
        wikipage.checkFoundResult();
    }
}
