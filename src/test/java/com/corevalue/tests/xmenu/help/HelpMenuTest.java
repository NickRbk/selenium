package com.corevalue.tests.xmenu.help;

import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.HelpMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.corevalue.constants.AuthorizedLandingPageConst.BUTTON_LOGOUT_SUBMIT;
import static com.corevalue.constants.AuthorizedLandingPageConst.SUBMENU_FRAME_INDEX;

public class HelpMenuTest implements LoginPageConst, TestConst, HelpMenuConst {

    @BeforeClass
    static void init() {
        Browser.get().getDriver().get(BASE_URL);
        LoginPage.get().login(VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    void openHelpMenuAndGetVersionTest() {
        AuthorizedLandingPage.get().openHelpMenu();
        WebElement helpInfo = HelpMenu.get().showVersion();
        Assert.assertTrue(helpInfo.getText().contains(VERSION));
        HelpMenu.get().confirmExit();
    }

    @AfterClass
    static void close() {
        AuthorizedLandingPage.get().logout(SUBMENU_FRAME_INDEX, BUTTON_LOGOUT_SUBMIT);
    }
}
