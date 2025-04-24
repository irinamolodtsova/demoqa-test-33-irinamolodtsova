package lesson20_mobile.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class WikiPage {

    @Step("Click Search Wikipedia")
    public void clickSearch(){
        $(accessibilityId("Search Wikipedia")).click();
    }

    @Step("Search Data")
    public void searchData(String text){
        $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(text);
    }

    @Step("Verify content found")
    public void checkFoundResult(){
        $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                .shouldHave(sizeGreaterThan(0));
    }
}
