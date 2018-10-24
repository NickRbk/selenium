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
    private final static TestGroup testGroup = TestGroup.MANAGEMENT;

    // ---------------------------------------------------------------------------------
    // Test for ADD OFFICE missed, because there is no functionality for further deletion
    // ---------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateOffice() {
        Office expectedOffice = expectedOfficeAfterUpdate();
        Office updatedOffice = AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updateOfficeGeneralInfo(testGroup, OFFICE_REASON_ADDRESS_UPDATED, expectedOffice)
                .getUpdatedFields(testGroup);

        ManagementManageOfficesSubmenu.get().close(testGroup, DIALOG_CLOSE_ID);
        Assert.assertEquals(updatedOffice, expectedOffice);
    }

    @Test(groups = "management")
    void addCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .addCoveredState(testGroup, OFFICE_COVERED_STATES_NAME)
                .close(testGroup, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addCoveredStates")
    void updateCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updateCoveredState(testGroup, OFFICE_COVERED_STATES_NAME_UPDATED)
                .close(testGroup, DIALOG_CLOSE_ID);

        Assert.assertNotEquals(submenu.getNameOfCoveredStatesUpdated(), submenu.getNameOfCoveredStates());
        Assert.assertEquals(submenu.getNameOfCoveredStatesUpdated(), OFFICE_COVERED_STATES_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = {"updateCoveredStates", "removePhones"})
    void removeCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .removeCoveredState(testGroup)
                .close(testGroup, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()-1);
    }

    @Test(groups = "management", dependsOnMethods = "updateCoveredStates")
    void addPhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .addPhone(testGroup, OFFICE_PHONE_NAME)
                .close(testGroup, DIALOG_CLOSE_ID);

        Assert.assertEquals(submenu.getCountOfPhonesUpdated(), submenu.getCountOfPhones()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addPhones")
    void updatePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updatePhones(testGroup, OFFICE_PHONE_NAME_UPDATED)
                .close(testGroup, DIALOG_CLOSE_ID);

        Assert.assertNotEquals(submenu.getNameOfPhoneUpdated(), submenu.getNameOfPhone());
        Assert.assertEquals(submenu.getNameOfPhoneUpdated(), OFFICE_PHONE_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updatePhones")
    void removePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.get();
        AuthorizedLandingPage.get().openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .removePhone(testGroup)
                .close(testGroup, DIALOG_CLOSE_ID);

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
