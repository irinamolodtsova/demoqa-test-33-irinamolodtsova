package lesson18_api_ui.data;

import lombok.Data;

@Data
public class TestData {
    // String login = "testira1";
     String userLogin = System.getenv("USERLOGIN");
     String userPassword = System.getenv("USERPASSWORD");
    // String password = "TESTira2!";
}
