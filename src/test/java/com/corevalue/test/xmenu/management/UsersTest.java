package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.xsubmenu.impl.ManagementManageUsersSubmenu;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UsersTest extends AbstractAfterMethod implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private final TestGroup testGroup;

    @Parameters("testGroup")
    public UsersTest(String param) {
        this.testGroup = TestGroup.valueOf(param);
    }

    // ---------------------------------------------------------------------------------
    // Test for ADD USER missed, because there is no functionality for further deletion
    // ---------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateUser() {
        String updatedUserName = editUserAndGetNewName(USER_NAME_UPDATED);
        setExitLogic(() -> ManagementManageUsersSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(updatedUserName, USER_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateUser")
    void rollBackUser() {
        String updatedUserName = editUserAndGetNewName(USER_NAME_INITIAL);
        setExitLogic(() -> ManagementManageUsersSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(updatedUserName, USER_NAME_INITIAL);
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }

    private String editUserAndGetNewName(String userName) {
        return AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageUsersSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchUsersByLogin(testGroup, USER_NAME_INITIAL)
                .selectLastUserToUpdate(testGroup)
                .updateUser(testGroup, userName)
                .getUpdatedField(testGroup, ManagementManageUsersSubmenu.INSTANCE.getCount(), LIST_SELECTOR);
    }
}
