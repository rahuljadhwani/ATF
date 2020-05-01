package com.framework.atf.utils;

import com.framework.atf.utils.system.OSValidator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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

    Consumer<String> systemPropertyConsumer = browser -> {
        Map<String, List<String>> driverDetails = new HashMap<>();
        driverDetails.put("chrome", Arrays.asList("webdriver.chrome.driver", "chromedriver"));
        driverDetails.put("opera", Arrays.asList("webdriver.opera.driver", "operadriver"));
        driverDetails.put("firefox", Arrays.asList("webdriver.gecko.driver", "geckodriver"));
        String driverName = driverDetails.get(browser).get(0);
        String driverBinary = driverDetails.get(browser).get(1);
        System.setProperty(driverName, Profile.getProperty("driverBinaryPath") + (OSValidator.isWindows() ? "\\" : "/") + driverBinary + (OSValidator.isWindows() ? ".exe" : ""));
    };

    Predicate<BrowserType> supportedBrowser = browserType -> {
        if (browserType.name().equalsIgnoreCase(browser)) {
            return true;
        }
        return false;
    };

    private WebDriver getWebDriver() {
        if (driver != null) {
            return driver;
        }

        if (supportedBrowser.test(BrowserType.CHROME)) {
            driver = getChromeDriver();
        } else if (supportedBrowser.test(BrowserType.FIREFOX)) {
            driver = getFirefoxDriver();
        } else if (supportedBrowser.test(BrowserType.OPERA)) {
            driver = getOperaDriver();
        } else if (supportedBrowser.test(BrowserType.HEADLESS)) {
            driver = getHeadlessDriver();
        }else if (supportedBrowser.test(BrowserType.REMOTE)){
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
        logger.warning("Implement Opera driver ");
        systemPropertyConsumer.accept(browser);
        driver = new OperaDriver();
        return driver;
    }

    private WebDriver getHeadlessDriver() {
        logger.warning("TODO: Implement Headless driver ");
        return driver;
    }

    private WebDriver getFirefoxDriver() {
        logger.info("Loading firefox driver");
        systemPropertyConsumer.accept(browser);
        driver = new FirefoxDriver();
        return driver;
    }

    private WebDriver getChromeDriver() {
        logger.info("Loading chrome driver");
        systemPropertyConsumer.accept(browser);
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

    /**
     * closing the WebDriver driver
     */
    public void closeDriver() {
        logger.info("closing the driver");
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
            //TODO: Have to add this due to the issue with firefox quit funciton is not working as expected
        }catch (Exception e){
        };
        driver = null;
    }
}
