package lesson18_api_ui.api;

import io.qameta.allure.Step;
import lesson18_api_ui.models.AddBookReq;
import lesson18_api_ui.models.LoginResp;

import static io.restassured.RestAssured.given;
import static lesson18_api_ui.specs.ApiSpecs.codeResponse;
import static lesson18_api_ui.specs.ApiSpecs.reqSpec;

public class BookApiStep {

    @Step("Удаление всех книг из профиля пользователя")
    public static void deleteAllBooks(LoginResp loginResponse) {
        given(reqSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(codeResponse(204));
    }
    @Step("Добавление книги в профиль пользователя")
    public static void addBook(LoginResp loginResponse, AddBookReq booksList) {
        given(reqSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(codeResponse(201));
    }

}
