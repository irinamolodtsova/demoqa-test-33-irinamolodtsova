package lesson20_mobile.config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:mobile/${env}.properties",
        "classpath:mobile/local.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browserstack.user")
    String getBrowserstackUser();

    @Key("browserstack.key")
    String getBrowserstackKey();

    @Key("app")
    @DefaultValue("bs://sample.app")
    String getApp();

    @Key("remoteUrl")
    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String getRemoteUrl();

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOsVersion();

    @Key("build")
    String getBuild();

    @Key("project")
    String getPtoject();

    @Key("name")
    String getName();
}
