package com.saucedemo.swagLabs.driver;

import com.saucedemo.swagLabs.exceptions.HeadlessNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static com.saucedemo.swagLabs.config.ConfigurationManager.configuration;
import static java.lang.Boolean.TRUE;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notification");

            if (configuration().headless()) chromeOptions.addArguments(CHROME_HEADLESS);

            return chromeOptions;
        }
    },

    FIREFOX {
        @Override
        public WebDriver createDriver() {
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) firefoxOptions.addArguments(GENERIC_HEADLESS);

            return firefoxOptions;
        }
    },

    EDGE {
        @Override
        public WebDriver createDriver() {
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);

            if (configuration().headless()) edgeOptions.addArguments(GENERIC_HEADLESS);

            return edgeOptions;
        }
    },

    SAFARI {
        @Override
        public WebDriver createDriver() {
            return new SafariDriver(getOptions());
        }

        @Override
        public SafariOptions getOptions() {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setAutomaticInspection(false);

            if (TRUE.equals(configuration().headless()))
                throw new HeadlessNotSupportedException();

            return safariOptions;
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";
    public static final String CHROME_HEADLESS = "--headless=new";
    public static final String GENERIC_HEADLESS = "-headless";

    public abstract WebDriver createDriver();

    public abstract AbstractDriverOptions<?> getOptions();

}
