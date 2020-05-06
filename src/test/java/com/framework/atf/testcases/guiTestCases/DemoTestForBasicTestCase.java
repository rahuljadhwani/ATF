package com.framework.atf.testcases.guiTestCases;

import com.framework.atf.boilerplates.LoginPage;
import com.framework.atf.testcases.BasicTestCase;
import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class DemoTestForBasicTestCase extends BasicTestCase {


    //@Test
    public void testBasicTestCaseUsingByClass(){
        LoginPage loginPage = new LoginPage();
        loginPage.goTo(Profile.getProperty("url"));
        loginPage.doLogin("Admin", "admin123");
        clickOnElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]"));
        enter(By.xpath("//*[@id=\"searchSystemUser_userName\"]"),"admin");
        clickOnElement(By.xpath("//*[@id=\"searchBtn\"]"));
        loginPage.close();
    }

    //@Test
    public void testBasicTestCaseUsingEnumLocator(){
        LoginPage loginPage = new LoginPage();
        loginPage.goTo(Profile.getProperty("url"));
        loginPage.doLogin("Admin", "admin123");
        clickOnElement(Locator.XPATH,"//*[@id=\"menu_admin_viewAdminModule\"]");
        enter(Locator.XPATH,"//*[@id=\"searchSystemUser_userName\"]","admin");
        clickOnElement(Locator.XPATH,"//*[@id=\"searchBtn\"]");
        loginPage.close();
    }

    //@Test
    public void testAlert() throws InterruptedException {
        WebDriver driver = Profile.getInstance().getDriver();
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        clickOnElement(Locator.XPATH, "//*[@name='submit']");
        System.out.println(getAlertText());
        Thread.sleep(5000);
        acceptAlert();
        Thread.sleep(5000);
        System.out.println(getAlertText());


    }

    //@Test
    public void testFrame(){
        //TODO: Need to test this
    }

    @Test
    public void testActions() throws InterruptedException {
        WebDriver driver = Profile.getInstance().getDriver();
        driver.get("https://www.goindigo.in/");
        driver.manage().window().maximize();//Have to use this otherwise it will throw ElementNotInteractableException due to overlay of elements
        moveMouse(Locator.XPATH,"//ul/li[@class='topNavItem']/a[contains(text(),'Book')]");
        clickOnElement(Locator.XPATH,"//*[@id=\"navbarSupportedContent\"]//a//i[@class='icon-book-flight']");
        Thread.sleep(5000);
    }
}
