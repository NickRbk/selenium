package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupsTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {

    @Test(groups = "management")
    void addGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageGroupsSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(TestGroup.MANAGEMENT)
                .addGroup(TestGroup.MANAGEMENT, GROUP_NAME, GROUP_DESCRIPTION)
                .close(TestGroup.MANAGEMENT, DIALOG_CANCEL_ID);

        Assert.assertEquals(submenu.getCountInitial()+1, submenu.getCountUpdated());
    }

    @Test(groups = "management", dependsOnMethods = "addGroup")
    void updateGroup() {
        String groupNameUpdated = AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageGroupsSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(TestGroup.MANAGEMENT)
                .updateGroup(TestGroup.MANAGEMENT, GROUP_NAME_UPDATED, GROUP_DESCRIPTION_UPDATED)
                .getUpdatedField(TestGroup.MANAGEMENT, ManagementManageGroupsSubmenu.get().getCountInitial(), LIST_SELECTOR);

        ManagementManageGroupsSubmenu.get().close(TestGroup.MANAGEMENT, DIALOG_CANCEL_ID);
        Assert.assertEquals(GROUP_NAME_UPDATED, groupNameUpdated);
    }

    @Test(groups = "management", dependsOnMethods = "updateGroup")
    void removeGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageGroupsSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(TestGroup.MANAGEMENT)
                .removeGroup(TestGroup.MANAGEMENT)
                .close(TestGroup.MANAGEMENT, DIALOG_CANCEL_ID);

        Assert.assertEquals(submenu.getCountInitial()-1, submenu.getCountUpdated());
    }
}
