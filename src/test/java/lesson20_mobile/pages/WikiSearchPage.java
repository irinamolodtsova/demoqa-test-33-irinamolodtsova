package lesson20_mobile.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static org.openqa.selenium.By.xpath;

public class WikiSearchPage {

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

    @Step("Click on found page - Starbucks")
    public void clickToTheFoundPage(){
        $(xpath("//android.widget.TextView[@text='Starbucks']")).click();
}

    @Step("Check you visited the page - Starbuks")
    public void checkTheOpenPage(){
        $(xpath("//android.widget.TextView[@text='An error occurred']")).shouldBe(visible);
    }

}
