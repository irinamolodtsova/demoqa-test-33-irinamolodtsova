package lesson18_api_ui.models.Bookstore.response;

import lesson18_api_ui.models.Bookstore.BookModel;
import lombok.Data;


import java.util.List;

@Data
public class GetBooksFromProfileResponseModel {
    String userId, username;
    List<BookModel> books;
}
