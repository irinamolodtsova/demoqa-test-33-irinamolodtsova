package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Test");
        $("#lastName").setValue("Testovich");
        $("#userEmail").setValue("test@testovich.com");
        $(by("for", "gender-radio-1")).click();
        $("#gender-radio-1").should(checked);
        $("#gender-radio-2").shouldNot(checked);
        $("#gender-radio-3").shouldNot(checked);
        $("#userNumber").setValue("1234567899");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2000");
        $(by("aria-label", "Choose Wednesday, July 5th, 2000")).click();
        $("#subjectsInput").setValue("English").pressEnter();
        $(by("for", "hobbies-checkbox-1")).click();
        $("#hobbies-checkbox-1").should(checked);
        $("#hobbies-checkbox-2").shouldNot(checked);
        $("#hobbies-checkbox-3").shouldNot(checked);
        $("#submit").scrollIntoView(true);
        $("#uploadPicture").uploadFile(new File("src/test/resources/927002368dfb5a96427ae990838dd112.jpg"));
        $("#currentAddress").setValue("Address 1");
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();
        $(byText("Thanks for submitting the form")).should(exist);
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Test Testovich"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("test@testovich.com"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Male"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("1234567899"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("05 July,2000"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("English"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Sports"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("927002368dfb5a96427ae990838dd112.jpg"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("Address 1"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text("NCR Delhi"));
        $("#closeLargeModal").scrollIntoView(true);
        $("#closeLargeModal").click();
        $(byText("Thanks for submitting the form")).shouldNot(exist);
    }
}
