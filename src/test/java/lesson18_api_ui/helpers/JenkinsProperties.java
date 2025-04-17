package lesson18_api_ui.helpers;

public class JenkinsProperties {

    public static String getServer() {
        String login = System.getProperty("login");
        String pw = System.getProperty("pw");
        String server = System.getProperty("server");

        return "https://" + login + ":" + pw + "@" + server + "/wd/hub";
    }
    public static String getBrowser(){
        return System.getProperty("browser","chrome");
    }

    public static String getBrowserSize(){
        return System.getProperty("size","1920x1080");
    }

    public static String getUserLogin(){
        String userLogin = System.getProperty("userLogin");
        return System.getProperty("userLogin",userLogin);
    }

    public static String getUserPassword(){
        String userPassword = System.getProperty("userPassword");
        return System.getProperty("userPassword",userPassword);
    }
}
