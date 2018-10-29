package com.corevalue.test.xmenu.initial;

import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.HelpMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.corevalue.constant.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constant.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class HelpMenuTest extends AbstractAfterMethod implements LoginPageConst, TestConst, HelpMenuConst {
    private static TestGroup testGroup;

    @Parameters("testGroup")
    public HelpMenuTest(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @Test
    void openHelpMenuAndGetVersionTest() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD);

        String version = AuthorizedLandingPage.INSTANCE
                .openHelpMenu(testGroup)
                .showVersion(testGroup);

        setExitLogic(() -> HelpMenu.INSTANCE.confirmExit(testGroup));
        Assert.assertTrue(version.contains(VERSION));
    }

    @AfterClass
    static void close() {
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
