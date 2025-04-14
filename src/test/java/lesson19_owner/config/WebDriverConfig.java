package lesson19_owner.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configs/${env}.properties"
})
public interface WebDriverConfig extends Config {
    @Key("getBrowser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("getBrowserVersion")
    @DefaultValue("114.0.5735.90")
    String getBrowserVersion();

    @Key("getBrowserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("getBaseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("getRemoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}