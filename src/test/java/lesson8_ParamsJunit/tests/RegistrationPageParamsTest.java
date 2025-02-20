package lesson8_ParamsJunit.tests;

import lesson8_ParamsJunit.pages.RegistrationPage;
import lesson8_ParamsJunit.utils.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;


public class RegistrationPageParamsTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @BeforeEach
    void beforeEach() {
        registrationPage.openPage()
                .removeBanners();
    }

    // 1- @ValueSource - использовала только для поля Gender, тк можно использовать только для одного параметра
    @ValueSource(strings = {
            "Male",
            "Female",
            "Other"
    })
    @ParameterizedTest(name = "Проверка успешной регистрации при выборе пола {0} через аннотацию @ValueSource")
    void necessaryFieldsOnlyRegistrationPageUsingValueSourceAnnotationTest(String gender) {
        registrationPage.setFirstName(randomUtils.firstName)
                .setLastName(randomUtils.lastName)
                .chooseGender(gender)
                .setUserNumber(randomUtils.userNumber)
                .setDateOfBirth(randomUtils.day, randomUtils.month, randomUtils.year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist()
                .checkResultOfRegistration("Student Name", randomUtils.firstName + " " + randomUtils.lastName)
                .checkResultOfRegistration("Gender", gender)
                .checkResultOfRegistration("Mobile", randomUtils.userNumber)
                .checkResultOfRegistration("Date of Birth", randomUtils.day + " " + randomUtils.month + "," + randomUtils.year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    // 2- @CsvFileSource
    @CsvFileSource(resources = "/necessaryFieldsOnlyRegistrationPageUsingCsvFileSourceAnnotationTest1.csv")
    @ParameterizedTest(name = "Проверка успешной регистрации при выборе данных {0} {1} {2} {3} {4} {5} {6} через аннотацию @CsvFileSource")
    void necessaryFieldsOnlyRegistrationPageUsingCsvFileSourceAnnotationTest(String firstName,
                                                                             String lastName,
                                                                             String gender,
                                                                             String userNumber,
                                                                             String day,
                                                                             String month,
                                                                             String year) {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist()
                .checkResultOfRegistration("Student Name", firstName + " " + lastName)
                .checkResultOfRegistration("Gender", gender)
                .checkResultOfRegistration("Mobile", userNumber)
                .checkResultOfRegistration("Date of Birth", day + " " + month + "," + year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    // 3- @CsvSource
    @CsvSource(value = {
            "Test, Testov, Male, 1234567899, 05, June, 2000",
            "Ivan, Ivanov, Other, 1234567800, 07, July, 2006",
            "Testie, Testovna, Female, 1225567899, 25, August, 2021"
    })
    @ParameterizedTest(name = "Проверка успешной регистрации при выборе данных {0} {1} {2} {3} {4} {5} {6} через аннотацию @CsvSource")
    void necessaryFieldsOnlyRegistrationPageUsingCsvSourceAnnotationTest(String firstName,
                                                                         String lastName,
                                                                         String gender,
                                                                         String userNumber,
                                                                         String day,
                                                                         String month,
                                                                         String year) {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist()
                .checkResultOfRegistration("Student Name", firstName + " " + lastName)
                .checkResultOfRegistration("Gender", gender)
                .checkResultOfRegistration("Mobile", userNumber)
                .checkResultOfRegistration("Date of Birth", day + " " + month + "," + year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }

    // 4- MethodSource
    static Stream<Arguments> necessaryFieldsOnlyRegistrationPageUsingMethodSourceAnnotationTest() {
        return Stream.of(
                Arguments.of("Test", "Testov", "Male", "1234567899", "05", "June", "2000"),
                Arguments.of("Ivan", "Ivanov", "Other", "1234567800", "07", "July", "2006"),
                Arguments.of("Testie", "Testovna", "Female", "1245673564", "25", "August", "2021")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка успешной регистрации при выборе данных {0} {1} {2} {3} {4} {5} {6} через аннотацию @MethodSource")
    void necessaryFieldsOnlyRegistrationPageUsingMethodSourceAnnotationTest(String firstName,
                                                                            String lastName,
                                                                            String gender,
                                                                            String userNumber,
                                                                            String day,
                                                                            String month,
                                                                            String year) {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .submitRegistration()
                .registrationConfirmationWindowShouldExist()
                .checkResultOfRegistration("Student Name", firstName + " " + lastName)
                .checkResultOfRegistration("Gender", gender)
                .checkResultOfRegistration("Mobile", userNumber)
                .checkResultOfRegistration("Date of Birth", day + " " + month + "," + year);
        registrationPage.closeRegistrationConfirmationWindow()
                .registrationConfirmationWindowShouldNotExist();
    }
}
