package lesson12_jenkinsParams.helpers;

public class JenkinsProperties {

    public static String getServer(){
        String login = System.getProperty("login");
        String pw = System.getProperty("pw");
        String server = System.getProperty("server");

        return "https://" + login + ":" + pw + "@" + server; //https://user1:1234@selenoid.autotests.cloud/wd/hub
    }

    public static String getBrowser(){
        return System.getProperty("browser","chrome");
    }

    public static String getBrowserVersion(){
        return System.getProperty("version", null);
    }

    public static String getBrowserSize(){
        return System.getProperty("size","1920x1080");
    }
}
