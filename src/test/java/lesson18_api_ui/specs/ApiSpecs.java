package lesson18_api_ui.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static lesson18_api_ui.helpers.CustomAllureListener.withCustomTemplates;


public class ApiSpecs {

    public static RequestSpecification loginSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static ResponseSpecification codeResponse(Integer code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}

