package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.pages.impl.LoginPage;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import org.testng.Assert;
import org.testng.annotations.*;

public class ManagementTestSetting implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    @BeforeGroups(groups = "management")
    static void init() {
        Browser.get().getDriver().get(BASE_URL);
        LoginPage.get().login(VALID_USERNAME,VALID_PASSWORD)
                .openPageWithCase();
    }

    @Test(groups = "management")
    void openManagementMenu() {
        int groupsCount = AuthorizedLandingPage.get().openManagementMenu()
                .openManageGroupsSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups()
                .getCountInitial();

        ManagementManageGroupsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertTrue(groupsCount > GROUPS_MIN_COUNT);
    }

    @AfterGroups(groups = "management")
    static void close() {
        AuthorizedLandingPage.get().logout(SUBMENU_FRAME_INDEX_WITH_CASE, BUTTON_LOGOUT_SUBMIT_WITH_CASE);
    }
}
