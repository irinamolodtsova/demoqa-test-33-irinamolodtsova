package lesson7.tests;

import com.github.javafaker.Faker;
import lesson7.pages.RegistrationPage;
import lesson7.pages.components.ConfirmationWindowComponent;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static lesson7.utils.RandomUtils.*;

public class RegistrationPageFakerTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    ConfirmationWindowComponent confirmationWindow = new ConfirmationWindowComponent();

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String currentAddress = faker.address().streetAddress();
    String userNumber = getRandomPhone();
    String day = getRandomDay();
    String year = getRandomYear();
    String gender = getRandomGender();
    String month = getRandomMonth();
    String hobbies = getRandomHobbies();



    @Test
    void successRegistrationPage() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .setSubject("English")
                .chooseHobbies(hobbies)
                .uploadFile("picfortest.jpg")
                .setAddress(currentAddress)
                .setState("NCR")
                .setCity("Delhi")
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", "picfortest.jpg")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", "NCR Delhi");
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    @Test
    void necessaryFieldsOnlyRegistrationPage() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender",  gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", day + " " + month + "," + year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    @Test
    void failedRegistrationNotAllNecessaryFieldsFilled() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .registrationConfirmationWindowShouldNotExist();
    }
}
