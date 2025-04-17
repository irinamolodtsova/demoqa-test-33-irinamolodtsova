package lesson18_api_ui.api;


import io.qameta.allure.Step;
import lesson18_api_ui.data.TestData;
import lesson18_api_ui.helpers.JenkinsProperties;
import lesson18_api_ui.models.Bookstore.request.LoginRequestModel;
import lesson18_api_ui.models.Bookstore.request.TokenRequestModel;
import lesson18_api_ui.models.Bookstore.response.LoginResponseModel;
import lesson18_api_ui.models.Bookstore.response.TokenResponseModel;
import lesson18_api_ui.models.Session;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static lesson18_api_ui.specs.BookStoreSpec.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookstoreAuth {
    static TestData user = new TestData();

    @Step("Получить токен")
    public static void getToken() {
        TokenRequestModel bodyData = new TokenRequestModel();
        bodyData.setUserName(JenkinsProperties.getUserLogin());
        bodyData.setPassword(JenkinsProperties.getUserPassword());


        TokenResponseModel response = step("Отправить запрос", () ->
                given(bookStoreRequestSpec)
                        .body(bodyData)
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .spec(bookStoreResponseSpec(200))
                        .extract().as(TokenResponseModel.class));

        step("Проверить ответ", () -> {
            assertEquals("Success", response.getStatus());
            assertEquals("User authorized successfully.", response.getResult());
        });
    }

    @Step("Авторизация")
    public static LoginResponseModel getAuthorization() {

        getToken();

        LoginRequestModel bodyData = new LoginRequestModel();
        bodyData.setUserName(JenkinsProperties.getUserLogin());
        bodyData.setPassword(JenkinsProperties.getUserPassword());

        return given(bookStoreRequestSpec)
                .body(bodyData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(bookStoreResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }

    @Step("Сгенерировать куки для браузера")
    public static void buildAuthorizationCookie(Session session) {
        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userID", session.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", session.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", session.getExpires()));
    }
}
