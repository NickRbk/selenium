package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageClientsSubmenu;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClientsTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private final TestGroup testGroup;

    @Parameters("testGroup")
    public ClientsTest(String param) {
        this.testGroup = TestGroup.valueOf(param);
    }

    // -----------------------------------------------------------------------------------
    // Test for ADD CLIENT missed, because there is no functionality for further deletion
    // -----------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_UPDATED, CLIENT_NAME_UPDATED);
        ManagementManageClientsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateClient")
    void rollBackClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_INITIAL, CLIENT_NAME_INITIAL);
        ManagementManageClientsSubmenu.INSTANCE.close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_INITIAL);
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
