package lesson18_api_ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private final SelenideElement deleteAllButton = $(".text-right.button.di #submit");
    private final SelenideElement confirmDeleteAllButton = $("#closeSmallModal-ok");

    @Step("Открываем страницу корзины")
    public ProfilePage openLoginPage() {
        open("/profile");
        return this;
    }

    @Step("Нажатие на удаление всех книг из корзины")
    public ProfilePage clickDeleteAllBooksButton() {
        deleteAllButton.click();
        return this;
    }

    @Step("Подтверждение удаления книг в окне")
    public ProfilePage confirmDeleteAllBooks() {
        confirmDeleteAllButton.click();
        return this;
    }

    @Step("Проверка, что книг нет в корзине")
    public ProfilePage isBookRemovedSuccessful() {
        $("#see-book-Speaking JavaScript").shouldNotBe(visible);
        return this;
    }

}

