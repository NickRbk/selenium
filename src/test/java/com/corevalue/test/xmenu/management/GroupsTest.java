package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GroupsTest extends AbstractAfterMethod implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private final TestGroup testGroup;

    @Parameters("testGroup")
    public GroupsTest(String param) {
        this.testGroup = TestGroup.valueOf(param);
    }

    @Test(groups = "management")
    void addGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .addGroup(testGroup, GROUP_NAME, GROUP_DESCRIPTION);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(submenu.getCountInitial()+1, submenu.getCountUpdated());
    }

    @Test(groups = "management", dependsOnMethods = "addGroup")
    void updateGroup() {
        String groupNameUpdated = AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .updateGroup(testGroup, GROUP_NAME_UPDATED, GROUP_DESCRIPTION_UPDATED)
                .getUpdatedField(testGroup, ManagementManageGroupsSubmenu.INSTANCE.getCountInitial(), LIST_SELECTOR);

        setExitLogic(() -> ManagementManageGroupsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(GROUP_NAME_UPDATED, groupNameUpdated);
    }

    @Test(groups = "management", dependsOnMethods = "updateGroup")
    void removeGroup() {
        ManagementManageGroupsSubmenu submenu = ManagementManageGroupsSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .removeGroup(testGroup);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(submenu.getCountInitial()-1, submenu.getCountUpdated());
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
