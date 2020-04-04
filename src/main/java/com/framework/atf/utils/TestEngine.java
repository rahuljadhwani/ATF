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

        Predicate<String> p = s -> s.equalsIgnoreCase(Profile.getProperty("browser"));

        if (p.test("chrome")) {
            driver = getChromeDriver();
        } else if (p.test("firefox")) {
            driver = getFirefoxDriver();
        } else if (p.test("opera")) {
            driver = getOperaDriver();
        } else if (p.test("headless")) {
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
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }
}
