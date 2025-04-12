package lesson18_api_ui.models;

import lombok.Data;

@Data
public class LoginResp {
    String created_date, expires, isActive, password, token, userId, username;
}

