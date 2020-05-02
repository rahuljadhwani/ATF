package com.framework.atf.testcases.guiTestCases;

import com.framework.atf.boilerplates.LoginPage;
import com.framework.atf.testcases.BasicTestCase;
import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.Locator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DemoTestForBasicTestCase extends BasicTestCase {


    @Test
    public void testBasicTestCaseUsingByClass(){
        LoginPage loginPage = new LoginPage();
        loginPage.goTo(Profile.getProperty("url"));
        loginPage.doLogin("Admin", "admin123");
        click(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]"));
        enter(By.xpath("//*[@id=\"searchSystemUser_userName\"]"),"admin");
        click(By.xpath("//*[@id=\"searchBtn\"]"));
        loginPage.close();
    }

    @Test
    public void testBasicTestCaseUsingEnumLocator(){
        LoginPage loginPage = new LoginPage();
        loginPage.goTo(Profile.getProperty("url"));
        loginPage.doLogin("Admin", "admin123");
        click(Locator.XPATH,"//*[@id=\"menu_admin_viewAdminModule\"]");
        enter(Locator.XPATH,"//*[@id=\"searchSystemUser_userName\"]","admin");
        click(Locator.XPATH,"//*[@id=\"searchBtn\"]");
        loginPage.close();
    }
}
