package com.framework.atf.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.function.Predicate;

public class TestEngine {

    private WebDriver driver = null;

    public enum BrowserType {
        CHROME,
        FIREFOX,
        OPERA,
        HEADLESS
    }

    public TestEngine() {
        getWebDriver();
    }

    private WebDriver getWebDriver() {
        if (driver != null) {
            return driver;
        }

        Predicate<BrowserType> browserTypePredicate = bt -> {
            if (bt.name().equalsIgnoreCase(Profile.getProperty("browser"))) {
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
        }

        return driver;
    }

    private WebDriver getOperaDriver() {
        return driver;
    }

    private WebDriver getHeadlessDriver() {
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", Profile.getProperty("driverBinaryPath") + "\\geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", Profile.getProperty("driverBinaryPath") + "\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    /**
     * Return the WebDriver driver
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }
}
