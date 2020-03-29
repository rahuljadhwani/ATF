package com.framework.atf.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class TestEngine {

    private static Properties properties = new Properties();
    WebDriver driver;

    public TestEngine() {
        getPropertyFile();
        loadWebDriver();
    }

    private void getPropertyFile() {

        URL resource = getClass().getClassLoader().getResource("default.properties");

        if (resource == null) {
            throw new IllegalArgumentException("default property file not found");
        } else {
            try {
                File file = new File(resource.getFile());
                FileReader reader = new FileReader(file);
                properties.load(reader);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }

    private void loadWebDriver() {
        System.setProperty("webdriver.chrome.driver", getProperty("driverBinaryPath"));
        driver = new ChromeDriver();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
