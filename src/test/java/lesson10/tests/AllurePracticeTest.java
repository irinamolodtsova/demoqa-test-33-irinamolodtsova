package lesson10.tests;

import lesson10.pages.GitHubSearch;
import lesson10.steps.Steps;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class AllurePracticeTest {
    Steps step = new Steps();
    GitHubSearch git = new GitHubSearch();

    String mainPage = "https://github.com";
    String repository = "eroshenkoam/allure-example";


    @Test
    void cleanWithListenerTest() {
        git.selenideLogger()
                .openPage(mainPage)
                .searchFieldClick()
                .searchInputFieldSendKeys(repository)
                .searchInputFieldSubmit()
                .foundRepositoryEroshenkoAllureExampleClick()
                .issueTabClick()
                .issueIDShouldExist();

    }

    @Test
    void lambdaTest() {
        git.selenideLogger();

        step("Открываем главную страницу", () -> {
            git.openPage(mainPage)
                    .addSnapshotPage()
                    .attachScreenshot();
        });
        step("Ищем репозиторий " + repository, () -> {
            git.searchFieldClick()
                    .searchInputFieldSendKeys(repository)
                    .searchInputFieldSubmit()
                    .addSnapshotPage()
                    .attachScreenshot();
        });
        step("Переходим в найденный репозиторий", () -> {
            git.foundRepositoryEroshenkoAllureExampleClick()
                    .addSnapshotPage()
                    .attachScreenshot();
        });
        step("Переходим на вкладку ISSUES", () -> {
            git.issueTabClick()
                    .addSnapshotPage()
                    .attachScreenshot();
        });
        step("Ищем необходимый номер ISSUE", () -> {
            git.issueIDShouldExist()
                    .addSnapshotPage()
                    .attachScreenshot();
        });
    }

    @Test
    void stepsTest() {
        git.selenideLogger();
        step.openMainPage(mainPage)
                .searchRepository(repository)
                .foundRepositoryClick()
                .issueTabRepositoryClick()
                .issueIDCheck();
    }
}
