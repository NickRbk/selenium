package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.menu.impl.ManagementMenu;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageClientsSubmenu;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClientsTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {

    // -----------------------------------------------------------------------------------
    // Test for ADD CLIENT missed, because there is no functionality for further deletion
    // -----------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_UPDATED, CLIENT_NAME_UPDATED);
        ManagementManageClientsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updateClient")
    void rollBackClient() {
        String updatedClientCode = editClientAndGetNewCode(CLIENT_CODE_INITIAL, CLIENT_NAME_INITIAL);
        ManagementManageClientsSubmenu.get().close(DIALOG_CANCEL_ID);
        Assert.assertEquals(updatedClientCode, CLIENT_CODE_INITIAL);
    }

    private String editClientAndGetNewCode(String clientCodeInitial, String clientNameInitial) {
        AuthorizedLandingPage.get().openManagementMenu();
        ManagementMenu.get().openManageClientsSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE);
        Browser.get().delay(WAIT_TIMEOUT);
        ManagementManageClientsSubmenu.get().searchClientsByCode(CLIENT_CODE_SEARCH);

        int clientsCount = ManagementManageClientsSubmenu.get().getCountOfTargetClients();
        ManagementManageClientsSubmenu.get().updateClient(clientsCount, clientCodeInitial, clientNameInitial);

        return ManagementManageClientsSubmenu.get().getUpdatedField(clientsCount, LIST_SELECTOR);
    }
}
