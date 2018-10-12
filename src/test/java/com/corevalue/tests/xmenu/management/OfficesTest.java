package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.pages.impl.AuthorizedLandingPage;
import com.corevalue.submenu.impl.ManagementManageOfficesSubmenu;
import com.corevalue.submenu.impl.obj.Office;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OfficesTest implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {

    // ---------------------------------------------------------------------------------
    // Test for ADD OFFICE missed, because there is no functionality for further deletion
    // ---------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateOffice() {
        Office expectedOffice = expectedOfficeAfterUpdate();
        Office updatedOffice = AuthorizedLandingPage.get().openManagementMenu()
                .openManageOfficesSubmenu(SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices()
                .selectLastOfficeToUpdate()
                .updateOfficeGeneralInfo(OFFICE_REASON_ADDRESS_UPDATED, expectedOffice)
                .getUpdatedFields();

        ManagementManageOfficesSubmenu.get().close(DIALOG_CLOSE_ID);
        Assert.assertEquals(updatedOffice, expectedOffice);
    }

    private Office expectedOfficeAfterUpdate() {
        return Office.builder()
                .name(OFFICE_NAME_UPDATED)
                .code(OFFICE_CODE_UPDATED)
                .address1(OFFICE_ADDR1_UPDATED)
                .address2(OFFICE_ADDR2_UPDATED)
                .city(OFFICE_CITY_UPDATED)
                .build();
    }
}
