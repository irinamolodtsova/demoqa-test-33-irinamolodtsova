package lesson20_mobile.helpers;

import lesson20_mobile.config.WebDriverConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        return given()
                .auth().basic(config.getUserName(), config.getUserKey())
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
