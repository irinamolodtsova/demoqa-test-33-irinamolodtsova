package lesson10.steps;

import io.qameta.allure.Step;
import lesson10.pages.GitHubSearch;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Steps {

    GitHubSearch git = new GitHubSearch();

    @Step("Открываем главную страницу")
    public Steps openMainPage(String mainPageLink) {
        git.openPage(mainPageLink)
                .addSnapshotPage()
                .attachScreenshot();
        return this;
    }

    @Step("Ищем репозиторий {value}")
    public Steps searchRepository(String value) {
        git.searchFieldClick()
                .searchInputFieldSendKeys(value)
                .searchInputFieldSubmit()
                .addSnapshotPage()
                .attachScreenshot();
        return this;
    }

    @Step("Переходим в найденный репозиторий")
    public Steps foundRepositoryClick() {
        git.foundRepositoryEroshenkoAllureExampleClick()
                .addSnapshotPage()
                .attachScreenshot();
        return this;
    }

    @Step("Переходим на вкладку ISSUES")
    public Steps issueTabRepositoryClick() {
        git.issueTabClick()
                .addSnapshotPage()
                .attachScreenshot();
        return this;
    }

    @Step("Ищем необходимый номер ISSUE")
    public Steps issueIDCheck() {
        git.issueIDShouldExist()
                .addSnapshotPage()
                .attachScreenshot();
        return this;
    }
}
