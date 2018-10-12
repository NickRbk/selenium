package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import com.corevalue.submenu.impl.obj.Office;
import org.openqa.selenium.By;

public class ManagementManageOfficesSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageOfficesSubmenu instance;

    private ManagementManageOfficesSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_OFFICES);
    }

    public static ManagementManageOfficesSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageOfficesSubmenu();
        }
        return instance;
    }

    public ManagementManageOfficesSubmenu searchOffices() {
        getContext();
        Browser.get().waitFrame(2);
        findElementBy(By.id(SEARCH_BUTTON_ID)).click();
        return ManagementManageOfficesSubmenu.get();
    }

    public ManagementManageOfficesSubmenu updateOfficeGeneralInfo(String reason, Office office) {
        getContext();
        Browser.get().waitFrame(3);
        setOfficeProps(office.getName(), office.getCode());
        updateAddressAndAcceptChanges(reason, office.getAddress1(), office.getAddress2(), office.getCity());
        return ManagementManageOfficesSubmenu.get();
    }

    private void updateAddressAndAcceptChanges(String reason, String addr1, String addr2, String city) {
        findElementBy(By.id(UPDATE_ADDRESS_BUTTON_ID)).click();
        setReasonToUpdateAddress(reason);
        setAddressProps(addr1, addr2, city);

        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(3);
        findElementBy(By.id(ACCEPT_ADDRESS_BUTTON_ID)).click();
    }

    public ManagementManageOfficesSubmenu selectLastOfficeToUpdate() {
        int officeCount = getCountOfOffices();
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(officeCount-1).click();
        findElementBy(By.id(UPDATE_BUTTON_ID)).click();
        return ManagementManageOfficesSubmenu.get();
    }

    public Office getUpdatedFields() {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(2);
        int lastIndex = getCountOfOffices()-1;
        return Office.builder()
                .name(findElementsBy(By.cssSelector(OFFICE_NAME_SELECTOR)).get(lastIndex).getText())
                .code(findElementsBy(By.cssSelector(OFFICE_CODE_SELECTOR)).get(lastIndex).getText())
                .address1(findElementsBy(By.cssSelector(OFFICE_ADDR1_SELECTOR)).get(lastIndex).getText())
                .address2(findElementsBy(By.cssSelector(OFFICE_ADDR2_SELECTOR)).get(lastIndex).getText())
                .city(findElementsBy(By.cssSelector(OFFICE_CITY_SELECTOR)).get(lastIndex).getText())
                .build();
    }

    private void setOfficeProps(String name, String code) {
        findElementBy(By.cssSelector(OFFICE_NAME_FIELD_ID)).clear();
        findElementBy(By.cssSelector(OFFICE_NAME_FIELD_ID)).sendKeys(name);
        findElementBy(By.cssSelector(OFFICE_CODE_FIELD_ID)).clear();
        findElementBy(By.cssSelector(OFFICE_CODE_FIELD_ID)).sendKeys(code);
    }

    private int getCountOfOffices() {
        Browser.get().waitFrame(0);
        return findElementsBy(By.cssSelector(LIST_SELECTOR)).size();
    }

    private void setReasonToUpdateAddress(String reason) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(4);

        findElementBy(By.id(OFFICE_REASON_ADDRESS_FIELD_ID)).sendKeys(reason);
        findElementBy(By.id(OK_BUTTON_ID)).click();
    }

    private void setAddressProps(String addr1, String addr2, String city) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(4);

        findElementBy(By.id(OFFICE_ADDR1_FIELD_ID)).clear();
        findElementBy(By.id(OFFICE_ADDR1_FIELD_ID)).sendKeys(addr1);
        findElementBy(By.id(OFFICE_ADDR2_FIELD_ID)).clear();
        findElementBy(By.id(OFFICE_ADDR2_FIELD_ID)).sendKeys(addr2);
        findElementBy(By.id(OFFICE_CITY_FIELD_ID)).clear();
        findElementBy(By.id(OFFICE_CITY_FIELD_ID)).sendKeys(city);
        findElementBy(By.id(OK_BUTTON_ID)).click();
    }
}
