package lesson5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HoverAndDragAndDropTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void enterprisePageTest() {
        open("https://github.com/");
        $("nav.HeaderMenu-nav").$(byText("Solutions")).hover();
        $("div.border-bottom.pb-3.pb-lg-0.border-lg-bottom-0.pb-lg-3").$("[href='https://github.com/enterprise']").click();
        $("h1#hero-section-brand-heading").shouldHave(text("""
                The AI-powered
                developer platform
                """));
    }

    @Test
    void dragAndDropTest() {
    open("https://the-internet.herokuapp.com/drag_and_drop");
    $("#column-a").shouldHave(text("A"));
    actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(150, 0).release().perform();
    $("#column-a").shouldHave(text("B"));

    //В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()
    //Именно $(element).dragAndDrop($(to-element)) не дает сделать - выдает ошибку синтаксиса, но IDEA предложила вариант ниже, и он сработал
    //$("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));
    }
}
