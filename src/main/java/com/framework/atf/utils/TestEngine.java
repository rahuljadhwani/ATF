package com.framework.atf.utils;

import com.framework.atf.utils.system.OSValidator;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class TestEngine {

    private WebDriver driver = null;
    private String browser = null;
    public Logger logger = Logger.getLogger("TestEngine.class");

    public enum BrowserType {
        CHROME,
        FIREFOX,
        OPERA,
        HEADLESS,
        REMOTE
    }

    public TestEngine(String browser) {
        this.browser = browser;
    }

    private WebDriver getWebDriver() {
        if (driver != null) {
            return driver;
        }

        Predicate<BrowserType> browserTypePredicate = bt -> {
            if (bt.name().equalsIgnoreCase(browser)) {
                return true;
            }
            return false;
        };

        if (browserTypePredicate.test(BrowserType.CHROME)) {
            driver = getChromeDriver();
        } else if (browserTypePredicate.test(BrowserType.FIREFOX)) {
            driver = getFirefoxDriver();
        } else if (browserTypePredicate.test(BrowserType.OPERA)) {
            driver = getOperaDriver();
        } else if (browserTypePredicate.test(BrowserType.HEADLESS)) {
            driver = getHeadlessDriver();
        }else if (browserTypePredicate.test(BrowserType.REMOTE)){
            driver = getRemoteDriver();
        }

        return driver;
    }

    private WebDriver getRemoteDriver() {
        logger.info("Loading Remote driver");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        try {
            URL url = new URL(Profile.getProperty("hub"));
            driver = new RemoteWebDriver(url, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private WebDriver getOperaDriver() {
        logger.warning("TODO: Implement Opera driver ");
        return driver;
    }

    private WebDriver getHeadlessDriver() {
        logger.warning("TODO: Implement Headless driver ");
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        logger.info("Loading firefox driver");
        System.setProperty("webdriver.gecko.driver", Profile.getProperty("driverBinaryPath") + "\\geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

    private WebDriver getChromeDriver() {
        logger.info("Loading chrome driver");
        System.setProperty("webdriver.chrome.driver", Profile.getProperty("driverBinaryPath") + (OSValidator.isWindows() ? "\\" : "/") + "chromedriver" + (OSValidator.isWindows() ? ".exe" : ""));
        driver = new ChromeDriver();
        return driver;
    }

    /**
     * Return the WebDriver driver
     *
     * @return driver
     */
    public WebDriver getDriver() {
        logger.info("Getting driver for " + Profile.getProperty("browser"));
        return getWebDriver();
    }
}
