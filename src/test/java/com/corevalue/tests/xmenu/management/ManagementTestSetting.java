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
    private final static TestGroup testGroup = TestGroup.MANAGEMENT;

    @BeforeGroups(groups = "management")
    static void init() {
        driver().get(BASE_URL);
        LoginPage.get(testGroup).login(testGroup, VALID_USERNAME,VALID_PASSWORD)
                .openPageWithCase(testGroup);
    }

    @Test(groups = "management")
    void openManagementMenu() {
        int groupsCount = AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .getCountInitial();

        ManagementManageGroupsSubmenu.get().close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertTrue(groupsCount > GROUPS_MIN_COUNT);
    }

    @AfterGroups(groups = "management")
    static void close() {
        AuthorizedLandingPage.get().logout(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE, BUTTON_LOGOUT_SUBMIT_WITH_CASE);
    }

    private static WebDriver driver() {
        return BrowserMap.get().getDrivers().get(testGroup).getDriver();
    }
}
