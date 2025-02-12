package lesson9.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson9.module.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonReadTest {
    private final ClassLoader cl = JsonReadTest.class.getClassLoader();

    @Test
    void jsonReadTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("books.json")) {
            try (InputStreamReader reader = new InputStreamReader(is)) {
                ObjectMapper objectMapper = new ObjectMapper();
                Library book = objectMapper.readValue(reader, Library.class);
                Assertions.assertEquals("Центральная городская библиотека", book.getName());
                Assertions.assertEquals("ул. Ленина, 15, г. Москва, Россия", book.getAddress());
                Assertions.assertEquals("10:00 - 20:00", book.getOpening_hours());
                Assertions.assertEquals("1984", book.getBooks().getFirst().getTitle());
                Assertions.assertEquals("Джордж Оруэлл", book.getBooks().getFirst().getAuthor());
                Assertions.assertEquals(1949, book.getBooks().getFirst().getYear());
                Assertions.assertEquals("Антиутопия", book.getBooks().getFirst().getGenre());
                Assertions.assertTrue(book.getBooks().getFirst().isIs_available());
                Assertions.assertEquals("Мастер и Маргарита", book.getBooks().get(1).getTitle());
                Assertions.assertEquals("Михаил Булгаков", book.getBooks().get(1).getAuthor());
                Assertions.assertEquals(1967, book.getBooks().get(1).getYear());
                Assertions.assertEquals("Роман", book.getBooks().get(1).getGenre());
                Assertions.assertFalse(book.getBooks().get(1).isIs_available());
            }
        }
    }
}



