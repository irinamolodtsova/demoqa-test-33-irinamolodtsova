package lesson18_api_ui.tests;

import lesson18_api_ui.api.BookApiStep;
import lesson18_api_ui.helpers.WithLogin;
import lesson18_api_ui.models.AddBookReq;
import lesson18_api_ui.models.LoginResp;
import lesson18_api_ui.pages.ProfilePage;
import org.junit.jupiter.api.Test;



public class DeleteBookTest extends BaseTest {

    ProfilePage profilePage = new ProfilePage();

    @WithLogin
    @Test
    public void deleteBooks() {

    }
}
