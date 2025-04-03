package lesson16_restasssuredmodels.tests;

import io.restassured.RestAssured;
import lesson16_restasssuredmodels.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static lesson16_restasssuredmodels.specs.UserSpecs.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("rest_assured")
public class RestAssuredTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
        //если делаю, то почему то /api не подставляется, почему так?
        //RestAssured.baseURI = "https://reqres.in";
        //  RestAssured.basePath = "/api";
    }

    @Test
    @DisplayName("Check User Not Found")
    void userNotFoundTest() {
        step("Send request Get User", () ->
                given(userNotFoundSpec)
                        .get()
                        .then()
                        .spec(codeResponse(404)));
    }

    @Test
    @DisplayName("Check Successful Login")
    void successfulLoginWithSpecsTest() {
        LoginModel authData = new LoginModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponse response = step("Make request", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .when()
                        .post()
                        .then()
                        .spec(codeResponse(200))
                        .extract().as(LoginResponse.class));

        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    @DisplayName("Check Unsuccessful Login")
    void unsuccessfulLoginTest() {

        LoginModel authData = new LoginModel();
        authData.setEmail("eve.holt@reqres.in");

        ErrorModel response = step("Make request", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .when()
                        .post()
                        .then()
                        .spec(codeResponse(400))
                        .extract().as(ErrorModel.class));

        step("Check response", () ->
                assertEquals("Missing password", response.getError()));
    }


    @Test
    @DisplayName("Check Successful User Update via Put Method")
    void updateUserDataPutMethodTest() {
        UpdateUserModel newData = new UpdateUserModel();
        newData.setName("eve.holt@reqres.in");
        newData.setJob("zion resident");

        UpdateUserResponse response = step("Make PUT request", () ->
                given(updateUserSpec)
                        .body(newData)
                        .when()
                        .put()
                        .then()
                        .spec(codeResponse(200))
                        .extract().as(UpdateUserResponse.class));
        step("Response", () ->
                assertEquals("zion resident", response.getJob()));

    }

    @Test
    @DisplayName("Check Successful User Update via Patch Method")
    void updateUserDataPatchMethodTest() {
        UpdateUserModel newData = new UpdateUserModel();
        newData.setName("eve.holt@reqres.in");
        newData.setJob("zion resident");

        UpdateUserResponse response = step("Make PATCH request", () ->
                given(updateUserSpec)
                        .body(newData)
                        .when()
                        .patch()
                        .then()
                        .spec(codeResponse(200))
                        .extract().as(UpdateUserResponse.class));
        step("Response", () ->
                assertEquals("zion resident", response.getJob()));

    }

    @Test
    @DisplayName("Check Successful User Delete")
    void deleteUserTest() {
        step("Send request Delete User", () ->
                given(deleteRequestSpec)
                        .delete()
                        .then()
                        .spec(codeResponse(204)));
    }
}


