package com.corevalue.test.xmenu.initial;

import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.HelpMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.xmenu.impl.HelpMenu;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.corevalue.constant.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constant.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class HelpMenuTest extends AbstractAfterMethod implements TestConst, HelpMenuConst {
    private TestGroup testGroup;
    private String validUsername;
    private String validPassword;

    @Parameters({"testGroup", "username", "password"})
    public HelpMenuTest(String param, String username, String password) {
        testGroup = TestGroup.valueOf(param);
        validUsername = username;
        validPassword = password;
    }

    @Test
    void openHelpMenuAndGetVersionTest() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, validUsername, validPassword);

        String version = AuthorizedLandingPage.INSTANCE
                .openHelpMenu(testGroup)
                .showVersion(testGroup);

        setExitLogic(() -> HelpMenu.INSTANCE.confirmExit(testGroup));
        Assert.assertTrue(version.contains(VERSION));
    }

    @AfterClass
    @Parameters("testGroup")
    static void close(String testGroup) {
        AuthorizedLandingPage.INSTANCE.logout(TestGroup.valueOf(testGroup), SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
