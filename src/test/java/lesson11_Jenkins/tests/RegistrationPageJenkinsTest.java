package lesson11_Jenkins.tests;

import lesson11_Jenkins.helpers.Attach;
import lesson11_Jenkins.pages.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Selenide.sleep;

@Tag("jenkins_test")
@DisplayName("Registration Page Jenkins Test")
public class RegistrationPageJenkinsTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void beforeEach() {
        registrationPage.openPage();
                sleep(2000);
        registrationPage.removeBanners();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @CsvFileSource(resources = "/necessaryFieldsOnlyRegistrationPageUsingCsvFileSourceAnnotationTest2.csv")
    @ParameterizedTest(name = "Проверка успешной регистрации при выборе данных {0} {1} {2} {3} {4}")
    void necessaryFieldsOnlyRegistrationPageTest(String firstName,
                                                                             String lastName,
                                                                             String gender,
                                                                             String userNumber,
                                                                             String email) {
        registrationPage.setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber)
                .setUserEmail(email)
                .submitRegistration()
                .checkResultOfRegistration(0, firstName)
                .checkResultOfRegistration(1, lastName)
                .checkResultOfRegistration(2, email)
                .checkResultOfRegistration(3, gender)
                .checkResultOfRegistration(4, "+1" + " " + userNumber);
    }
}
