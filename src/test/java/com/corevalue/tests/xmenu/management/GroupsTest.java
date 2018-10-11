package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.menu.impl.ManagementMenu;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupsTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {

    @Test(groups = "management")
    void addGroup() {
        AuthorizedLandingPage.get().openManagementMenu();
        ManagementMenu.get().openManageGroupsSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE);

        int groupsCountInitial = ManagementManageGroupsSubmenu.get().getGroupsCountInitial();
        ManagementManageGroupsSubmenu.get().addGroup(GROUP_NAME, GROUP_DESCRIPTION);
        Browser.get().getDriver().switchTo().parentFrame();
        int groupsCountNew = ManagementManageGroupsSubmenu.get().getCountOfGroups();

        ManagementManageGroupsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(groupsCountInitial+1, groupsCountNew);
    }

    @Test(groups = "management", dependsOnMethods = "addGroup")
    void updateGroup() {
        AuthorizedLandingPage.get().openManagementMenu();
        ManagementMenu.get().openManageGroupsSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE);

        int groupsCount = ManagementManageGroupsSubmenu.get().getGroupsCountInitial();
        ManagementManageGroupsSubmenu.get().updateGroup(groupsCount, GROUP_NAME_UPDATED, GROUP_DESCRIPTION_UPDATED);
        String groupNameUpdated = ManagementManageGroupsSubmenu.get().getUpdatedField(groupsCount, LIST_SELECTOR);

        ManagementManageGroupsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(GROUP_NAME_UPDATED, groupNameUpdated);
    }

    @Test(groups = "management", dependsOnMethods = "updateGroup")
    void removeGroup() {
        AuthorizedLandingPage.get().openManagementMenu();
        ManagementMenu.get().openManageGroupsSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE);

        int groupsCount = ManagementManageGroupsSubmenu.get().getGroupsCountInitial();
        ManagementManageGroupsSubmenu.get().removeGroup(groupsCount);

        int groupsCountNew = ManagementManageGroupsSubmenu.get().getCountOfGroups();

        ManagementManageGroupsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(groupsCount-1, groupsCountNew);
    }
}
