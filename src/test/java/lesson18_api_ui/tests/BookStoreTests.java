package lesson18_api_ui.tests;


import lesson18_api_ui.api.BookstoreApiSteps;
import lesson18_api_ui.helpers.WithLogin;
import lesson18_api_ui.models.Bookstore.BookModel;
import lesson18_api_ui.models.Session;
import lesson18_api_ui.pages.ProfilePage;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Tag("bookStore")
public class BookStoreTests extends TestBase {

    BookstoreApiSteps bookstoreApi = new BookstoreApiSteps();
    ProfilePage profilePage = new ProfilePage();

    @WithLogin
    @DisplayName("Удаление книги из профиля")
    @Test
    void deleteBookFromProfileTest(Session session) {
        List<BookModel> books;
        String isbn;
        List<BookModel> collectionOfIsbns = new ArrayList<>();

        bookstoreApi.deleteAllBooksFromProfile(session);
        books = bookstoreApi.getBooksFromStore();
        assertNotEquals(null, books);

        isbn = bookstoreApi.selectRandomBook(books);
        bookstoreApi.addBookToIsbnCollection(isbn, collectionOfIsbns);
        books = bookstoreApi.addBookToProfile(collectionOfIsbns, session);
        assertEquals(collectionOfIsbns, books);

        profilePage.openProfile();
        profilePage.deleteBookFromProfile(isbn);
        profilePage.bookDontExist(isbn);

        books = bookstoreApi.getBooksFromProfile(session);
        assertTrue(books.isEmpty());
    }
}
