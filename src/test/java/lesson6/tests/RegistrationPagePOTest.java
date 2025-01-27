package lesson6.tests;

import com.codeborne.selenide.Configuration;
import lesson6.pages.RegistrationPage;
import lesson6.pages.components.ConfirmationWindowComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPagePOTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    ConfirmationWindowComponent confirmationWindow = new ConfirmationWindowComponent();

    @Test
    void successRegistrationPage() {
        registrationPage.openPage()
                .setFirstName("Test")
                .setLastName("Testov")
                .setEmail("test@testov.com")
                .chooseGender("Male")
                .setUserNumber("1234567899")
                .setDateOfBirth("Choose Wednesday, July 5th, 2000", "July", "2000")
                .setSubject("English")
                .chooseHobbies("Sports")
                .uploadFile("picfortest.jpg")
                .setAddress("Adress 1")
                .setState("NCR")
                .setCity("Delhi")
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", "Test Testov")
                .checkResult("Student Email", "test@testov.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567899")
                .checkResult("Date of Birth", "5 July,2000")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "picfortest.jpg")
                .checkResult("Address", "Adress 1")
                .checkResult("State and City", "NCR Delhi");
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    @Test
    void necessaryFieldsOnlyRegistrationPage() {
        registrationPage.openPage()
                .setFirstName("Test")
                .setLastName("Testov")
                .chooseGender("Male")
                .setUserNumber("1234567899")
                .setDateOfBirth("Choose Wednesday, July 5th, 2000", "July", "2000")
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", "Test Testov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "1234567899")
                .checkResult("Date of Birth", "5 July,2000");
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    @Test
    void failedRegistrationNotAllNecessaryFieldsFilled() {
        registrationPage.openPage()
                .setFirstName("Test")
                .setLastName("Testov")
                .chooseGender("Male")
                .setDateOfBirth("Choose Wednesday, July 5th, 2000", "July", "2000")
                .submitRegistration()
                .registrationConfirmationWindowShouldNotExist();

    }
}
