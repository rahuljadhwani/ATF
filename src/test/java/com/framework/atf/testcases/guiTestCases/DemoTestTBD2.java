package com.framework.atf.testcases.guiTestCases;

import com.framework.atf.boilerplates.LoginPage;
import com.framework.atf.testcases.BasicTestCase;
import com.framework.atf.utils.Profile;
import org.testng.annotations.Test;

public class DemoTestTBD2 extends BasicTestCase {

    @Test
    public void test2() {
        LoginPage objLoginPage = new LoginPage();
        objLoginPage.goTo(Profile.getProperty("url"));
        objLoginPage.doLogin("Admin", "admin123");
        System.out.println(objLoginPage.getTitle());
        objLoginPage.close();
    }
}
