package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageClientsSubmenu;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClientsTest extends AbstractAfterMethod implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private final TestGroup testGroup;

    @Parameters("testGroup")
    public ClientsTest(String param) {
        this.testGroup = TestGroup.valueOf(param);
    }

    @Test(groups = "management")
    void openManagementMenu() {
        int groupsCount = AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageGroupsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchGroups(testGroup)
                .getCountInitial();

        setExitLogic(() -> ManagementManageGroupsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertTrue(groupsCount > GROUPS_MIN_COUNT);
    }

    // -----------------------------------------------------------------------------------
    // Test for ADD CLIENT missed, because there is no functionality for further deletion
    // -----------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_UPDATED, CLIENT_NAME_UPDATED);
        setExitLogic(() -> ManagementManageClientsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateClient")
    void rollBackClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_INITIAL, CLIENT_NAME_INITIAL);
        setExitLogic(() -> ManagementManageClientsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID));
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_INITIAL);
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }

    private String editClientAndGetNewCode(String clientCodeInitial, String clientNameInitial) {
        return AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageClientsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchClientsByCode(testGroup, CLIENT_CODE_SEARCH)
                .selectLastClientToUpdate(testGroup)
                .updateClient(testGroup, clientCodeInitial, clientNameInitial)
                .getUpdatedField(testGroup, ManagementManageClientsSubmenu.INSTANCE.getCount(), LIST_SELECTOR);
    }
}
