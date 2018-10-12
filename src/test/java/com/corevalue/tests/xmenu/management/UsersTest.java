package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageUsersSubmenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {

    // ---------------------------------------------------------------------------------
    // Test for ADD USER missed, because there is no functionality for further deletion
    // ---------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateUser() {
        String updatedUserName = editUserAndGetNewName(USER_NAME_UPDATED);
        ManagementManageUsersSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedUserName, USER_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateUser")
    void rollBackUser() {
        String updatedUserName = editUserAndGetNewName(USER_NAME_INITIAL);
        ManagementManageUsersSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedUserName, USER_NAME_INITIAL);
    }

    private String editUserAndGetNewName(String userName) {
        return AuthorizedLandingPage.get().openManagementMenu()
                .openManageUsersSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchUsersByLogin(USER_NAME_INITIAL)
                .selectLastUserToUpdate()
                .updateUser(userName)
                .getUpdatedField(ManagementManageUsersSubmenu.get().getCount(), LIST_SELECTOR);
    }
}
