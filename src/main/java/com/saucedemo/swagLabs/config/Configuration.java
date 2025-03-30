package com.saucedemo.swagLabs.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:general.properties",
        "classpath:grid.properties"})
public interface Configuration extends Config {

    @Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    boolean headless();

    @Key("seconds")
    int seconds();

    @Key("url.base")
    String url();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();
}
