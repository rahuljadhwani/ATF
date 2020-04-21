package com.framework.atf.boilerplates;

import com.framework.atf.utils.Profile;
import org.openqa.selenium.WebDriver;

public class BasicPage {
    WebDriver driver = Profile.getInstance().getDriver();

    public BasicPage() {
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public void close(){
        driver.close();
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
