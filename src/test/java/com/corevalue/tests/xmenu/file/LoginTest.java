package com.corevalue.tests.xmenu.file;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.Browser;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import com.corevalue.constants.LoginPageConst;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.corevalue.constants.AuthorizedLandingPageConst.AUTHORIZED_PAGE_TITLE;
import static com.corevalue.constants.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constants.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class LoginTest implements LoginPageConst, TestConst {
    @BeforeClass
    static void init() {
        Browser.get().getDriver().get(BASE_URL);
    }

    @Test
    void failedLoginTest() {
        LoginPage.get().login(INVALID_USERNAME,INVALID_PASSWORD);
        Assert.assertNotEquals(AUTHORIZED_PAGE_TITLE, Browser.get().getDriver().getTitle());
    }

    @Test(priority = 1)
    void successLoginTest() {
        LoginPage.get().login(VALID_USERNAME,VALID_PASSWORD);
        Assert.assertEquals(AUTHORIZED_PAGE_TITLE, Browser.get().getDriver().getTitle());
    }

    @Test(dependsOnMethods = "successLoginTest")
    void logoutTest() {
        AuthorizedLandingPage.get().logout(SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
        Assert.assertEquals(LOGIN_PAGE_TITLE, Browser.get().getDriver().getTitle());
    }

    @AfterSuite
    static void closeDriver() {
        Browser.get().getDriver().close();
    }
}
