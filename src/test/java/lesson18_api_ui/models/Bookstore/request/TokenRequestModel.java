package lesson18_api_ui.models.Bookstore.request;

import lombok.Data;

@Data
public class TokenRequestModel {
    String userName, password;
}
