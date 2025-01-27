package lesson6.tests;

import lesson6.pages.TextBoxPage;
import org.junit.jupiter.api.Test;

public class TextBoxPOTest extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .setFullName("Test Testov")
                .setUserEmail("test@testov.com")
                .setCurrentAddress("Address 1")
                .setPermanent("Address 2")
                .submitButton()
                .checkResult("Name:", "Test Testov")
                .checkResult("Email:", "test@testov.com")
                .checkResult("Current Address :", "Address 1")
                .checkResult("Permananet Address :", "Address 2");
    }
}

