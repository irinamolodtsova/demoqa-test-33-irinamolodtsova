package lesson18_api_ui.api;


import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lesson18_api_ui.models.Bookstore.*;
import lesson18_api_ui.models.Bookstore.request.AddBookToProfileRequestModel;
import lesson18_api_ui.models.Bookstore.response.AddBookToProfileResponseModel;
import lesson18_api_ui.models.Bookstore.response.GetBooksFromProfileResponseModel;
import lesson18_api_ui.models.Bookstore.response.GetBooksFromStoreResponseModel;
import lesson18_api_ui.models.Session;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static lesson18_api_ui.specs.BookStoreSpec.*;

public class BookstoreApiSteps {
    private final Faker faker = new Faker();

    @Step("Удалить все книги из профиля")
    public void deleteAllBooksFromProfile(Session session) {
        given(bookStoreRequestSpec)
                .header("Authorization", "Bearer " + session.getToken())
                .queryParam("UserId", session.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(bookStoreResponseSpec(204));
    }

    @Step("Получить список книг, доступных в магазине")
    public List<BookModel> getBooksFromStore() {
        GetBooksFromStoreResponseModel response =
                given(bookStoreRequestSpec)
                        .when()
                        .get("/BookStore/v1/books")
                        .then()
                        .spec(bookStoreResponseSpec(200))
                        .extract().as(GetBooksFromStoreResponseModel.class);

        return response.getBooks();
    }

    @Step("Выбрать любую книгу")
  //  public String selectRandomBook(List<BookModel> books) {
//        Random random = new Random();
//        int randomIndex = random.nextInt(books.size());
//        BookModel randomBook = books.get(randomIndex);
//
//        return randomBook.getIsbn();
    public String selectRandomBook(List<BookModel> books) {
        return books.get(faker.number().numberBetween(0, books.size()-1)).getIsbn();
    }

    @Step("Добавить книгу в список книг")
    public List<BookModel> addBookToIsbnCollection(String isbn, List<BookModel> collectionOfIsbns) {
        BookModel selectedBook = new BookModel();
        selectedBook.setIsbn(isbn);
        collectionOfIsbns.add(selectedBook);

        return collectionOfIsbns;
    }

    @Step("Добавить выбранную книгу в профиль")
    public List<BookModel> addBookToProfile(List<BookModel> collectionOfIsbns, Session session) {

        AddBookToProfileRequestModel bodyData = new AddBookToProfileRequestModel();
        bodyData.setCollectionOfIsbns(collectionOfIsbns);
        bodyData.setUserId(session.getUserId());
        AddBookToProfileResponseModel response =
                given(bookStoreRequestSpec)
                        .header("Authorization", "Bearer " + session.getToken())
                        .body(bodyData)
                        .when()
                        .post("/BookStore/v1/Books")
                        .then()
                        .spec(bookStoreResponseSpec(201))
                        .extract().as(AddBookToProfileResponseModel.class);

        return response.getBooks();
    }

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
