package com.framework.atf.utils;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class TestEngine {

    private static Properties properties = new Properties();

    public TestEngine(){
        getPropertyFile();
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

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
