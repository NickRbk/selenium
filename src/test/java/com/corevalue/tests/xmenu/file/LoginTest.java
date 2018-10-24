package com.corevalue.tests.xmenu.file;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import com.corevalue.constants.LoginPageConst;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.corevalue.constants.AuthorizedLandingPageConst.AUTHORIZED_PAGE_TITLE;
import static com.corevalue.constants.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constants.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class LoginTest implements LoginPageConst, TestConst {
    private final static TestGroup testGroup = TestGroup.INIT;

    @BeforeClass
    static void init() {
        driver().get(BASE_URL);
    }

    @Test
    void failedLoginTest() {
        LoginPage.get(testGroup).login(testGroup, INVALID_USERNAME,INVALID_PASSWORD);
        Assert.assertNotEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(priority = 1)
    void successLoginTest() {
        LoginPage.get(testGroup).login(testGroup, VALID_USERNAME,VALID_PASSWORD);
        Assert.assertEquals(AUTHORIZED_PAGE_TITLE, driver().getTitle());
    }

    @Test(dependsOnMethods = "successLoginTest")
    void logoutTest() {
        AuthorizedLandingPage.get().logout(testGroup, SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
        Assert.assertEquals(LOGIN_PAGE_TITLE, driver().getTitle());
    }

    @AfterSuite
    static void closeDriver() {
        driver().close();
        BrowserMap.get().getDrivers().get(TestGroup.MANAGEMENT).getDriver().close();
    }

    private static WebDriver driver() {
        return BrowserMap.get().getDrivers().get(testGroup).getDriver();
    }
}
