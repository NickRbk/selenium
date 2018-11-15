package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.xsubmenu.impl.ManagementManageOfficesSubmenu;
import com.corevalue.xsubmenu.impl.obj.Office;
import com.corevalue.test.AbstractAfterMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OfficesTest extends AbstractAfterMethod implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private TestGroup testGroup;

    @Parameters("testGroup")
    public OfficesTest(String param) {
        this.testGroup = TestGroup.valueOf(param);
    }

    // ---------------------------------------------------------------------------------
    // Test for ADD OFFICE missed, because there is no functionality for further deletion
    // ---------------------------------------------------------------------------------

    @Test(groups = "management")
    void updateOffice() {
        Office expectedOffice = expectedOfficeAfterUpdate();
        Office updatedOffice = AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updateOfficeGeneralInfo(testGroup, OFFICE_REASON_ADDRESS_UPDATED, expectedOffice)
                .getUpdatedFields(testGroup);

        setExitLogic(() -> ManagementManageOfficesSubmenu.INSTANCE.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertEquals(updatedOffice, expectedOffice);
    }

    @Test(groups = "management")
    void addCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .addCoveredState(testGroup, OFFICE_COVERED_STATES_NAME);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addCoveredStates")
    void updateCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updateCoveredState(testGroup, OFFICE_COVERED_STATES_NAME_UPDATED);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertNotEquals(submenu.getNameOfCoveredStatesUpdated(), submenu.getNameOfCoveredStates());
        Assert.assertEquals(submenu.getNameOfCoveredStatesUpdated(), OFFICE_COVERED_STATES_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = {"updateCoveredStates", "removePhones"})
    void removeCoveredStates() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .removeCoveredState(testGroup);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertEquals(submenu.getCountOfCoveredStatesUpdated(), submenu.getCountOfCoveredStates()-1);
    }

    @Test(groups = "management", dependsOnMethods = "updateCoveredStates")
    void addPhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .addPhone(testGroup, OFFICE_PHONE_NAME);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertEquals(submenu.getCountOfPhonesUpdated(), submenu.getCountOfPhones()+1);
    }

    @Test(groups = "management", dependsOnMethods = "addPhones")
    void updatePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .updatePhones(testGroup, OFFICE_PHONE_NAME_UPDATED);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertNotEquals(submenu.getNameOfPhoneUpdated(), submenu.getNameOfPhone());
        Assert.assertEquals(submenu.getNameOfPhoneUpdated(), OFFICE_PHONE_NAME_UPDATED);
    }

    @Test(groups = "management", dependsOnMethods = "updatePhones")
    void removePhones() {
        ManagementManageOfficesSubmenu submenu = ManagementManageOfficesSubmenu.INSTANCE;
        AuthorizedLandingPage.INSTANCE.openManagementMenu(testGroup)
                .openManageOfficesSubmenu(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE)
                .searchOffices(testGroup)
                .selectLastOfficeToUpdate(testGroup)
                .removePhone(testGroup);

        setExitLogic(() -> submenu.close(testGroup, DIALOG_CLOSE_ID));
        Assert.assertEquals(submenu.getCountOfPhonesUpdated(), submenu.getCountOfPhones()-1);
    }

    @Override
    protected WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
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
