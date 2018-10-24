package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ManagementTestSetting implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    @BeforeGroups(groups = "management")
    static void init() {
        driver().get(BASE_URL);
        LoginPage.get(TestGroup.MANAGEMENT).login(TestGroup.MANAGEMENT, VALID_USERNAME,VALID_PASSWORD)
                .openPageWithCase(TestGroup.MANAGEMENT);
    }

    @Test(groups = "management")
    void openManagementMenu() {
        int groupsCount = AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageGroupsSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(TestGroup.MANAGEMENT)
                .getCountInitial();

        ManagementManageGroupsSubmenu.get().close(TestGroup.MANAGEMENT, DIALOG_CANCEL_ID);
        Assert.assertTrue(groupsCount > GROUPS_MIN_COUNT);
    }

    @AfterGroups(groups = "management")
    static void close() {
        AuthorizedLandingPage.get().logout(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE, BUTTON_LOGOUT_SUBMIT_WITH_CASE);
    }

    private static WebDriver driver() {
        return BrowserMap.get().getDrivers().get(TestGroup.MANAGEMENT).getDriver();
    }
}
