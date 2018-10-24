package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageClientsSubmenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClientsTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private final static TestGroup testGroup = TestGroup.MANAGEMENT;

    // -----------------------------------------------------------------------------------
    // Test for ADD CLIENT missed, because there is no functionality for further deletion
    // -----------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_UPDATED, CLIENT_NAME_UPDATED);
        ManagementManageClientsSubmenu.get().close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateClient")
    void rollBackClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_INITIAL, CLIENT_NAME_INITIAL);
        ManagementManageClientsSubmenu.get().close(testGroup, DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_INITIAL);
    }

    private String editClientAndGetNewCode(String clientCodeInitial, String clientNameInitial) {
        return AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageClientsSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchClientsByCode(testGroup, CLIENT_CODE_SEARCH)
                .selectLastClientToUpdate(testGroup)
                .updateClient(testGroup, clientCodeInitial, clientNameInitial)
                .getUpdatedField(testGroup, ManagementManageClientsSubmenu.get().getCount(), LIST_SELECTOR);
    }
}
