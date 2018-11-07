package com.corevalue.test.xmenu.initial;

import com.corevalue.constant.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.corevalue.constant.AuthorizedLandingPageConst.AUTHORIZED_PAGE_TITLE;
import static com.corevalue.constant.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constant.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class LoginTest extends AbstractAfterMethod implements LoginPageConst, TestConst {
    private static TestGroup testGroup;

    @Parameters("testGroup")
    public LoginTest(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @BeforeClass
    static void init() {
        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().get(BASE_URL);
    }

    @Test
    void failedLoginTest() {
        LoginPage.INSTANCE.login(testGroup, INVALID_USERNAME,INVALID_PASSWORD);
        Assert.assertNotEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(dependsOnMethods = "failedLoginTest")
    void successLoginTest() {
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD);
        Assert.assertEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(dependsOnMethods = "successLoginTest")
    void logoutTest() {
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
        Assert.assertEquals(LOGIN_PAGE_TITLE, driver().getTitle());
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
