package com.framework.atf.utils;

public class Profile {

    public static void main(String[] args) {

        TestEngine engine = new TestEngine();
        String url = engine.getProperty("url");

        engine.getDriver().get(url);
    }
}
