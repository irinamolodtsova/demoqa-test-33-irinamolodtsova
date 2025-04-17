package lesson18_api_ui.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static lesson18_api_ui.helpers.CustomAllureListener.withCustomTemplates;


public class BookStoreSpec {

    public static RequestSpecification bookStoreRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification bookStoreResponseSpec(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .log(ALL)
                .build();
    }

}