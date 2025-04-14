package lesson19_owner.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GitHubTest extends TestBase {


    @Test
    public void gitHubTest() {
        String title = title();
        assertEquals("GitHub · Build and ship software on a single, collaborative platform · GitHub", title);
    }

}
