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
    private final static TestGroup testGroup = TestGroup.MANAGEMENT;

    @Test(groups = "management")
    void addGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .addGroup(testGroup, GROUP_NAME, GROUP_DESCRIPTION)
                .close(testGroup, DIALOG_CANCEL_ID);

        Assert.assertEquals(submenu.getCountInitial()+1, submenu.getCountUpdated());
    }

    @Test(groups = "management", dependsOnMethods = "addGroup")
    void updateGroup() {
        String groupNameUpdated = AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .updateGroup(testGroup, GROUP_NAME_UPDATED, GROUP_DESCRIPTION_UPDATED)
                .getUpdatedField(testGroup, ManagementManageGroupsSubmenu.INSTANCE.getCountInitial(), LIST_SELECTOR);

        ManagementManageGroupsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertEquals(GROUP_NAME_UPDATED, groupNameUpdated);
    }

    @Test(groups = "management", dependsOnMethods = "updateGroup")
    void removeGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .removeGroup(testGroup)
                .close(testGroup, DIALOG_CANCEL_ID);

        Assert.assertEquals(submenu.getCountInitial()-1, submenu.getCountUpdated());
    }
}
