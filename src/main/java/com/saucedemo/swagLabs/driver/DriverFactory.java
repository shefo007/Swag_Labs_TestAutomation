package com.saucedemo.swagLabs.driver;


import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.saucedemo.swagLabs.config.ConfigurationManager.configuration;

public class DriverFactory {
    private static final Logger logger = Logger.getLogger("");

    public WebDriver createInstance(String browserType) {
        Target target = Target.valueOf(configuration().target().toUpperCase());

        return switch (target) {
            case LOCAL -> BrowserFactory.valueOf(browserType.toUpperCase()).createDriver();
            case REMOTE -> createRemoteInstance(BrowserFactory.valueOf(browserType.toUpperCase()).getOptions());
        };
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());

            remoteWebDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), capability);
        } catch (java.net.MalformedURLException e) {
            logger.log(Level.SEVERE, "Grid URL is invalid or Grid is not available");
            logger.log(Level.SEVERE, String.format("Browser: %s", capability.getBrowserName()), e);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, String.format("Browser %s is not valid or recognized", capability.getBrowserName()), e);
        }

        return remoteWebDriver;
    }

    enum Target {
        LOCAL, REMOTE
    }
}
