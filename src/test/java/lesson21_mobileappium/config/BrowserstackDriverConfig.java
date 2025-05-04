package lesson21_mobileappium.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "file:src/test/resources/AndroidStudio/browserstack.properties"
})

public interface BrowserstackDriverConfig extends Config {

    @Key("app")
    String getApp();

    @Key("browserstackDeviceName")
    String getDeviceName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("browserstackURL")
    String getBrowserstackURL();
}
