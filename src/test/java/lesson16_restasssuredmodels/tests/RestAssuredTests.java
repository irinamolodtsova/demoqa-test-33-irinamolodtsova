package lesson16_restasssuredmodels.tests;

import io.restassured.RestAssured;
import lesson16_restasssuredmodels.models.lombok.*;
import org.junit.jupiter.api.BeforeAll;
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
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void userNotFoundTest() {
        step("Send request Get User", ()->
        given(userNotFoundSpec)
                .get()
                .then()
                .spec(userNotFoundResponseSpec));
    }

    @Test
    void successfulLoginWithSpecsTest() {
        LoginModel authData = new LoginModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponse response = step("Make request", ()->
                given(loginRequestSpec)
                        .body(authData)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponse.class));

        step("Check response", ()->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void unsuccessfulLoginTest() {

        LoginModel authData = new LoginModel();
        authData.setEmail("eve.holt@reqres.in");

        ErrorModel response = step("Make request", ()->
                given(loginRequestSpec)
                        .body(authData)

                        .when()
                        .post()

                        .then()
                        .spec(missingUserPasswordSpec)
                        .extract().as(ErrorModel.class));

        step("Check response", ()->
                assertEquals("Missing password", response.getError()));
    }


    @Test
    void updateUserDataPutMethodTest(){
        UpdateUserModel newData = new UpdateUserModel();
        newData.setName("eve.holt@reqres.in");
        newData.setJob("zion resident");

        UpdateUserResponse response = step("Make PUT request", ()->
        given(updateUserSpec)
                .body( newData)
                .when()
                .put()

                .then()
                .spec(updatedUserSpec)
                .extract().as(UpdateUserResponse.class));
        step("Response", ()->
                assertEquals("zion resident", response.getJob()));

    }

    @Test
    void updateUserDataPatchMethodTest(){
        UpdateUserModel newData = new UpdateUserModel();
        newData.setName("eve.holt@reqres.in");
        newData.setJob("zion resident");

        UpdateUserResponse response = step("Make PATCH request", ()->
                given(updateUserSpec)
                        .body( newData)
                        .when()
                        .patch()

                        .then()
                        .spec(updatedUserSpec)
                        .extract().as(UpdateUserResponse.class));
        step("Response", ()->
                assertEquals("zion resident", response.getJob()));

    }

    @Test
    void deleteUserTest(){
        step("Send request Delete User", ()->
        given(deleteRequestSpec)

                .delete()
                .then()
                .spec(deleteResponseSpec));
    }
}


