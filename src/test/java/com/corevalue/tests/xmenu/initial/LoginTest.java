package com.corevalue.tests.xmenu.initial;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import com.corevalue.constants.LoginPageConst;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.corevalue.constants.AuthorizedLandingPageConst.AUTHORIZED_PAGE_TITLE;
import static com.corevalue.constants.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constants.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class LoginTest implements LoginPageConst, TestConst {
    private final static TestGroup testGroup = TestGroup.INITIAL;

    @BeforeClass
    static void init() {
        driver().get(BASE_URL);
    }

    @Test(groups = "initial")
    void failedLoginTest() {
        LoginPage.INSTANCE.login(testGroup, INVALID_USERNAME,INVALID_PASSWORD);
        Assert.assertNotEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(groups = "initial", dependsOnMethods = "failedLoginTest")
    void successLoginTest() {
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD);
        Assert.assertEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(groups = "initial", dependsOnMethods = "successLoginTest")
    void logoutTest() {
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
        Assert.assertEquals(LOGIN_PAGE_TITLE, driver().getTitle());
    }

    private static WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
