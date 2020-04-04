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
    public static Profile instance = null;
    public Logger logger = Logger.getLogger("Profile.class");


    public Profile() {
        loadPropertyFiles();
    }

    /**
     * Load Property files
     */
    private void loadPropertyFiles() {
        propertyLoaderConsumer.accept("default.properties");
    }

    /**
     * Returns the Profile instance during runtime
     * @return Profile
     */
    public static Profile getInstance() {
        if (instance == null) {
            instance = new Profile();
        }
        return instance;
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
        TestEngine engine = new TestEngine();
        return engine.getDriver();
    }

}
