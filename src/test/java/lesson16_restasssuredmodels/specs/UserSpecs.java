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
                .basePath("/api/login");

        public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(STATUS)
                .log(BODY)
                .build();

        public static RequestSpecification userNotFoundSpec = with()
                .log().uri()
                .basePath("/api/users/23");

        public static ResponseSpecification userNotFoundResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .log(STATUS)
                .log(BODY)
                .build();

        public static ResponseSpecification missingUserPasswordSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .log(STATUS)
                .log(BODY)
                .build();

        public static RequestSpecification updateUserSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .contentType(JSON)
                .basePath("api/users/2");

        public static ResponseSpecification updatedUserSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(STATUS)
                .log(BODY)
                .build();

        public static RequestSpecification deleteRequestSpec = with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .basePath("/api/users/2");

        public static ResponseSpecification deleteResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(204)
                .log(STATUS)
                .log(BODY)
                .build();
}
