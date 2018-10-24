package com.corevalue.tests.xmenu.management;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.constants.LoginPageConst;
import com.corevalue.constants.TestConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
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
        Office updatedOffice = AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .updateOfficeGeneralInfo(TestGroup.MANAGEMENT, OFFICE_REASON_ADDRESS_UPDATED, expectedOffice)
                .getUpdatedFields(TestGroup.MANAGEMENT);

        ManagementManageOfficesSubmenu.get().close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);
        Assert.assertEquals(updatedOffice, expectedOffice);
    }

    @Test(groups = "management")
    void addCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .addCoveredState(TestGroup.MANAGEMENT, OFFICE_COVERED_STATES_NAME)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addCoveredStates")
    void updateCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .updateCoveredState(TestGroup.MANAGEMENT, OFFICE_COVERED_STATES_NAME_UPDATED)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertNotEquals(submenu.getNameOfCoveredStatesUpdated(), submenu.getNameOfCoveredStates());
        Assert.assertEquals(submenu.getNameOfCoveredStatesUpdated(), OFFICE_COVERED_STATES_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = {"updateCoveredStates", "removePhones"})
    void removeCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .removeCoveredState(TestGroup.MANAGEMENT)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()-1);
    }

    @Test(groups = "management", dependsOnMethods = "updateCoveredStates")
    void addPhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .addPhone(TestGroup.MANAGEMENT, OFFICE_PHONE_NAME)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfPhonesUpdated(), submenu.getCountOfPhones()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addPhones")
    void updatePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .updatePhones(TestGroup.MANAGEMENT, OFFICE_PHONE_NAME_UPDATED)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertNotEquals(submenu.getNameOfPhoneUpdated(), submenu.getNameOfPhone());
        Assert.assertEquals(submenu.getNameOfPhoneUpdated(), OFFICE_PHONE_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updatePhones")
    void removePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(TestGroup.MANAGEMENT)
                .openManageOfficesSubmenu(TestGroup.MANAGEMENT, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(TestGroup.MANAGEMENT)
                .selectLastOfficeToUpdate(TestGroup.MANAGEMENT)
                .removePhone(TestGroup.MANAGEMENT)
                .close(TestGroup.MANAGEMENT, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfPhonesUpdated(), submenu.getCountOfPhones()-1);
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
