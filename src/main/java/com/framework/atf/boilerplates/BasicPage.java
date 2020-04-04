package com.framework.atf.boilerplates;

import com.framework.atf.utils.Profile;
import com.framework.atf.utils.TestEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicPage {

    WebDriver driver;

    public BasicPage() {
        driver = Profile.getInstance().getDriver();
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public void close(){
        driver.close();
    }
}
