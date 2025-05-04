package lesson20_mobile.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "file:src/test/resources/mobile/mobile.properties",
        "file:src/test/resources/mobile/local.properties"
})

public interface WebDriverConfig extends Config {
    @Key("userName")
    String getUserName();

    @Key("userKey")
    String getUserKey();

    @Key("app")
    String getApp();

    @Key("deviceName")
    String getDeviceName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("browserstackURL")
    String getBrowserstackURL();
}
