package com.framework.atf.testcases.guiTestCases;

import com.framework.atf.boilerplates.LoginPage;
import com.framework.atf.testcases.BasicTestCase;
import com.framework.atf.utils.Profile;
import com.framework.atf.utils.common.ScreenshotHelper;
import com.framework.atf.utils.enums.ScreenshotType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTestTBD extends BasicTestCase {

    @Test
    public void test0() {
        LoginPage objLoginPage = new LoginPage();
        objLoginPage.goTo(Profile.getProperty("url"));
        objLoginPage.doLogin("Admin", "admin123");
        System.out.println(objLoginPage.getTitle());
        objLoginPage.close();
    }

    @Test
    public void test1() {
        LoginPage objLoginPage = new LoginPage();
        objLoginPage.goTo(Profile.getProperty("url"));
        objLoginPage.doLogin("Admin", "admin123");
        System.out.println(objLoginPage.getTitle());
        Assert.assertEquals(objLoginPage.getTitle(),"XYZ");
        objLoginPage.close();
    }

    @Test
    public void testScreenshot() throws InterruptedException {
        WebDriver driver = Profile.getInstance().getDriver();
        driver.get("https://www.airbnb.co.in/");
        ScreenshotHelper.takeScreenshot(ScreenshotType.VIEWABLEAREA,"test2");
        Thread.sleep(5000);
        ScreenshotHelper.takeScreenshot(ScreenshotType.FULLWEBPAGE,"test3");
    }
}
