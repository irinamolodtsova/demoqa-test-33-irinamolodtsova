package lesson18_api_ui.api;

import io.qameta.allure.Step;
import lesson18_api_ui.models.LoginReq;
import lesson18_api_ui.models.LoginResp;
import lesson18_api_ui.tests.BaseTest;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static lesson18_api_ui.specs.ApiSpecs.codeResponse;
import static lesson18_api_ui.specs.ApiSpecs.loginSpec;

public class LoginApiSteps extends BaseTest {

    @Step("Get Cookie")
    public static LoginResp apiGetCookie() {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginReq registerRequest;
        File jsonFile = new File(LoginApiSteps.class.getResource("/data.json").getFile());
        try {
            registerRequest = objectMapper.readValue(jsonFile, LoginReq.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return given(loginSpec)
                .body(registerRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(codeResponse(200))
                .extract().as(LoginResp.class);
    }
}
