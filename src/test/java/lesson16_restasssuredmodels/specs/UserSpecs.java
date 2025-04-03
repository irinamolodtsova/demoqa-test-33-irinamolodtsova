package lesson16_restasssuredmodels.specs;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static lesson16_restasssuredmodels.helpers.CustomAllureListener.withCustomTemplates;

public class UserSpecs {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/login");

    public static RequestSpecification userNotFoundSpec = with()
            .log().uri()
            .basePath("/users/23");

    public static RequestSpecification updateUserSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .contentType(JSON)
            .basePath("/users/2");

    public static RequestSpecification deleteRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/users/2");


    public static ResponseSpecification codeResponse(Integer code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}
