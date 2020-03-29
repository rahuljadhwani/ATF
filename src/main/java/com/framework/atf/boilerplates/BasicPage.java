package com.framework.atf.boilerplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicPage {

    WebDriver driver;


    public void enterText(String value, String id){
        driver.findElement(By.xpath("//input[@id='"+id+"']"));
    }
}
