package lesson6.tests;

import lesson6.pages.RegistrationPage;
import lesson6.pages.components.ConfirmationWindowComponent;
import org.junit.jupiter.api.Test;

public class RegistrationPagePOTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    ConfirmationWindowComponent confirmationWindow = new ConfirmationWindowComponent();

    @Test
    void successRegistrationPage() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Test")
                .setLastName("Testov")
                .setEmail("test@testov.com")
                .chooseGender("Male")
                .setUserNumber("1234567899")
                .setDateOfBirth("05", "July", "2000")
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
                .removeBanners()
                .setFirstName("Test")
                .setLastName("Testov")
                .chooseGender("Male")
                .setUserNumber("1234567899")
                .setDateOfBirth("05", "July", "2000")
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
                .removeBanners()
                .setFirstName("Test")
                .setLastName("Testov")
                .chooseGender("Male")
                .setDateOfBirth("05", "July", "2000")
                .submitRegistration()
                .registrationConfirmationWindowShouldNotExist();
    }
}
