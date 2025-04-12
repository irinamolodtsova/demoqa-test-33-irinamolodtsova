package lesson18_api_ui.models;

import lombok.Data;

@Data
public class LoginReq {
    private String userName, password;
}
