package com.framework.atf.utils;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class Profile {

    private static Properties properties = new Properties();
    public static final ThreadLocal<Profile>  context = new ThreadLocal<Profile>();
    private TestEngine testEngine;
    public Logger logger = Logger.getLogger("Profile.class");


    public Profile(String browser) {
        initializePropertyValues();
        initializeTestEngine(browser);
    }

    private void initializeTestEngine(String browser) {
        if (browser == null){
            browser = Profile.getProperty("browser");
        }
        testEngine = new TestEngine(browser);
    }

    /**
     * Load Property files
     */
    private void initializePropertyValues() {
        propertyLoaderConsumer.accept("default.properties");
    }

    /**
     * Returns the Profile instance during runtime
     * @return Profile
     */
    public static Profile getInstance(String browser) {
        if (context.get() == null) {
            context.set(new Profile(browser));
        }
        return context.get();
    }

    public static Profile getInstance(){
        return getInstance(null);
    }

    /**
     * Consumer to load Property file
     */
    Consumer<String> propertyLoaderConsumer = file -> {
        logger.info("Loading: " + file);
        URL resource = getClass().getClassLoader().getResource(file);

        if (resource == null) {
            throw new IllegalArgumentException(file + " not found");
        } else {
            try {
                File fileName = new File(resource.getFile());
                FileReader reader = new FileReader(fileName);
                properties.load(reader);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        logger.info(file + " loaded successfully");
    };

    /**
     * Get the property value
     * @param key
     * @return property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get the Driver from TestEngine class
     * @return WebDriver instance of TestEngine
     */
    public WebDriver getDriver() {
        return testEngine.getDriver();
    }

    public void closeDriver() {
        testEngine.closeDriver();
        context.remove();
    }
}
