package com.framework.atf.boilerplates;

import com.framework.atf.utils.TestEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicPage {
    TestEngine engine = new TestEngine();
    WebDriver driver = engine.getDriver();

    public void goTo(String url){
        driver.get(url);
    }

    public TestEngine getEngine(){
        return engine;
    }

}
