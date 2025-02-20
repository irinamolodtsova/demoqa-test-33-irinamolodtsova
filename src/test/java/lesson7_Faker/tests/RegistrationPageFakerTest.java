package lesson7_Faker.tests;

import lesson7_Faker.pages.RegistrationPage;
import lesson7_Faker.pages.components.ConfirmationWindowComponent;
import lesson7_Faker.utils.RandomUtils;
import org.junit.jupiter.api.Test;


public class RegistrationPageFakerTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    ConfirmationWindowComponent confirmationWindow = new ConfirmationWindowComponent();
    RandomUtils randomUtils = new RandomUtils();


    @Test
    void successRegistrationPageTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .setEmail(randomUtils.userEmail)
                .chooseGender(randomUtils.gender)
                .setUserNumber(randomUtils.userNumber)
                .setDateOfBirth(randomUtils.day, randomUtils.month, randomUtils.year)
                .setSubject(randomUtils.subject)
                .chooseHobbies(randomUtils.hobbies)
                .uploadFile(randomUtils.picture)
                .setAddress(randomUtils.currentAddress)
                .setState(randomUtils.state)
                .setCity(randomUtils.city)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", randomUtils.firstName + " " + randomUtils.lastName)
                .checkResult("Student Email", randomUtils.userEmail)
                .checkResult("Gender", randomUtils.gender)
                .checkResult("Mobile", randomUtils.userNumber)
                .checkResult("Date of Birth", randomUtils.day + " " + randomUtils.month + "," + randomUtils.year)
                .checkResult("Subjects", randomUtils.subject)
                .checkResult("Hobbies", randomUtils.hobbies)
                .checkResult("Picture", randomUtils.picture)
                .checkResult("Address", randomUtils.currentAddress)
                .checkResult("State and City", randomUtils.state + " " + randomUtils.city);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();

    }

    @Test
    void necessaryFieldsOnlyRegistrationPageTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .chooseGender(randomUtils.gender)
                .setUserNumber(randomUtils.userNumber)
                .setDateOfBirth(randomUtils.day, randomUtils.month, randomUtils.year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist();
        confirmationWindow.checkResult("Student Name", randomUtils.firstName + " " + randomUtils.lastName)
                .checkResult("Gender", randomUtils.gender)
                .checkResult("Mobile", randomUtils.userNumber)
                .checkResult("Date of Birth", randomUtils.day + " " + randomUtils.month + "," + randomUtils.year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    @Test
    void failedRegistrationNotAllNecessaryFieldsFilledTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .chooseGender(randomUtils.gender)
                .setDateOfBirth(randomUtils.day, randomUtils.month, randomUtils.year)
                .submitRegistration()
                .registrationConfirmationWindowShouldNotExist();
    }
}
