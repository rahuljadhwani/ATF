package com.framework.atf.boilerplates;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasicPage {

    @FindBy(xpath = "//input[@id='txtUsername']")
    WebElement usernameTextbox;

    @FindBy(xpath = "//input[@id='txtPassword']")
    WebElement passwordTextbox;

    @FindBy(xpath = "//input[@id='btnLogin']")
    WebElement loginButton;


    public LoginPage(){
        PageFactory.initElements(driver, this);

    }


    public void doLogin(String username, String password){
        usernameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        loginButton.click();

    }








}
