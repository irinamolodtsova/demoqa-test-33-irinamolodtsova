package lesson18_api_ui.models;

import lombok.Data;

@Data
public class Session {
    private String userId,
            token,
            expires;
}
