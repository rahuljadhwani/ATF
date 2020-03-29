package com.framework.atf.boilerplates;

import org.openqa.selenium.remote.internal.JsonToWebElementConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;

    @FindBy(xpath = "//span[text()='Assign Leave']")
    WebElement assignLeaveButton;

    @FindBy(xpath = "//span[text()='Leave List']")
    WebElement leaveListButton;

    @FindBy(xpath = "//span[text()='Timesheets']")
    WebElement timesheetButton;

    @FindBy(xpath = "//a[text()='Welcome Admin']")
    WebElement welcomeAdminTab;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logoutLink;

    public DashboardPage(WeDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
