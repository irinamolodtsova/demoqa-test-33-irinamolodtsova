package lesson18_api_ui.data;

import lombok.Data;

@Data
public class TestData {
    // String login = "testira1";
     String userLogin = System.getProperty("USERLOGIN");
     String userPassword = System.getProperty("USERPASSWORD");
    // String password = "TESTira2!";
}
