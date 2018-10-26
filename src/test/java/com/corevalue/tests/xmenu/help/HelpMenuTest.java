package com.corevalue.tests.xmenu.help;

import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.HelpMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.corevalue.constants.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constants.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class HelpMenuTest implements LoginPageConst, TestConst, HelpMenuConst {
    private final static TestGroup testGroup = TestGroup.INIT;

    @BeforeClass
    static void init() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    void openHelpMenuAndGetVersionTest() {
        String version = AuthorizedLandingPage.INSTANCE
                .openHelpMenu(testGroup)
                .showVersion();

        HelpMenu.INSTANCE.confirmExit();
        Assert.assertTrue(version.contains(VERSION));
    }

    @AfterClass
    static void close() {
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
    }

    private static WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
