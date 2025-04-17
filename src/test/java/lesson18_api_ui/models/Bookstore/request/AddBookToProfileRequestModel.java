package lesson18_api_ui.models.Bookstore.request;

import lesson18_api_ui.models.Bookstore.BookModel;
import lombok.Data;


import java.util.List;

@Data
public class AddBookToProfileRequestModel {
    String userId;
    List<BookModel> collectionOfIsbns;
}
