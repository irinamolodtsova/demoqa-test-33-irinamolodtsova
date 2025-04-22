package lesson18_api_ui.api;

import io.qameta.allure.Step;
import lesson18_api_ui.models.Bookstore.BookModel;
import lesson18_api_ui.models.Bookstore.response.GetBooksFromProfileResponseModel;
import lesson18_api_ui.models.Session;

import java.util.List;

import static io.restassured.RestAssured.given;
import static lesson18_api_ui.specs.BookStoreSpec.bookStoreRequestSpec;
import static lesson18_api_ui.specs.BookStoreSpec.bookStoreResponseSpec;

public class AccountApiSteps {

    @Step("Проверить что в профиле нет книг")
    public List<BookModel> getBooksFromProfile(Session session) {
        GetBooksFromProfileResponseModel response =
                given(bookStoreRequestSpec)
                        .when()
                        .header("Authorization", "Bearer " + session.getToken())
                        .get("/Account/v1/User/" + session.getUserId())
                        .then()
                        .spec(bookStoreResponseSpec(200))
                        .extract().as(GetBooksFromProfileResponseModel.class);

        return response.getBooks();
    }
}
