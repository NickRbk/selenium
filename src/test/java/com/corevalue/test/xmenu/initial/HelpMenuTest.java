package com.corevalue.test.xmenu.initial;

import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.HelpMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.corevalue.constant.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constant.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class HelpMenuTest implements LoginPageConst, TestConst, HelpMenuConst {
    private static TestGroup testGroup;

    @Parameters("testGroup")
    public HelpMenuTest(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @BeforeClass
    static void init() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    void openHelpMenuAndGetVersionTest() {
        String version = AuthorizedLandingPage.INSTANCE
                .openHelpMenu(testGroup)
                .showVersion(testGroup);

        HelpMenu.INSTANCE.confirmExit(testGroup);
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
