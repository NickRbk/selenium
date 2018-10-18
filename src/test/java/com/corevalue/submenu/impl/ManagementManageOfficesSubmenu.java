package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import com.corevalue.submenu.impl.obj.Office;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.corevalue.constants.TagsConst.BUTTON;

@Getter
public class ManagementManageOfficesSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageOfficesSubmenu instance;
    private int countOfCoveredStates;
    private int countOfCoveredStatesUpdated;
    private int countOfPhones;
    private int countOfPhonesUpdated;
    private String nameOfCoveredStates;
    private String nameOfCoveredStatesUpdated;
    private String nameOfPhone;
    private String nameOfPhoneUpdated;

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
        localContext(2);
        findElementBy(By.id(SEARCH_BUTTON_ID)).click();
        return ManagementManageOfficesSubmenu.get();
    }

    public ManagementManageOfficesSubmenu selectLastOfficeToUpdate() {
        int officeCount = getCountOfOffices();
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(officeCount-1).click();
        findElementBy(By.id(UPDATE_BUTTON_ID)).click();
        return ManagementManageOfficesSubmenu.get();
    }

    public ManagementManageOfficesSubmenu updateOfficeGeneralInfo(String reason, Office office) {
        localContext(3);
        setOfficeProps(office.getName(), office.getCode());
        updateAddressAndAcceptChanges(reason, office.getAddress1(), office.getAddress2(), office.getCity());
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: general info
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

    private void updateAddressAndAcceptChanges(String reason, String addr1, String addr2, String city) {
        findElementBy(By.id(UPDATE_ADDRESS_BUTTON_ID)).click();
        setReasonToUpdate(reason);
        setAddressProps(addr1, addr2, city);

        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(3);
        findElementBy(By.id(ACCEPT_ADDRESS_BUTTON_ID)).click();
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
    //endregion

    public ManagementManageOfficesSubmenu addCoveredState(String state) {
        localContext(3);
        findElementBy(By.id(OFFICE_COVERED_STATES_ID)).click();
        setCoveredStates(state);

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: add covered states
    private void setCoveredStates(String state) {
        countOfCoveredStates = findElementsBy(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(OFFICE_COVERED_STATES_ADD_STATE_ID)).click();
        setReasonToUpdate(OFFICE_REASON_UPDATED);

        setCoveredStatesProps(state);

        Browser.get().getDriver().switchTo().parentFrame();
        countOfCoveredStatesUpdated = Browser.get().waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu removeCoveredState() {
        localContext(3);
        findElementBy(By.id(OFFICE_COVERED_STATES_ID)).click();

        removeCoveredStateService();

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: remove covered states
    private void removeCoveredStateService() {
        countOfCoveredStates = findElementsBy(By.cssSelector(LIST_SELECTOR)).size();

        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(countOfCoveredStates - 2).click();
        findElementBy(By.id(OFFICE_COVERED_STATES_REMOVE_STATE_ID)).click();
        setReasonToUpdate(OFFICE_REASON_REMOVE);

        countOfCoveredStatesUpdated = Browser.get().waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu updateCoveredState(String state) {
        localContext(3);
        findElementBy(By.id(OFFICE_COVERED_STATES_ID)).click();

        updateCoveredStateService(state);

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: update covered states
    private void updateCoveredStateService(String state) {
        nameOfCoveredStates = findElementsBy(By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(1).getText();

        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(countOfCoveredStatesUpdated - 2).click();
        findElementBy(By.id(OFFICE_COVERED_STATES_EDIT_STATE_ID)).click();
        setReasonToUpdate(OFFICE_REASON_UPDATED);

        setCoveredStatesProps(state);

        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(3);
        nameOfCoveredStatesUpdated = findElementsBy(By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(1).getText();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu addPhone(String email) {
        localContext(3);
        findElementBy(By.id(OFFICE_PHONES_ID)).click();
        setPhone(email);

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: add phone
    private void setPhone(String email) {
        countOfPhones = findElementsBy(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(OFFICE_PHONE_ADD_ID)).click();
        setReasonToUpdate(OFFICE_REASON_UPDATED);

        setPhoneProps(email);

        Browser.get().getDriver().switchTo().parentFrame();
        countOfPhonesUpdated = Browser.get().waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }

    private void setPhoneProps(String email) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(4);

        Select phoneType = new Select(findElementBy(By.id(OFFICE_PHONE_TYPE_SELECT_ID)));
        phoneType.selectByVisibleText(OFFICE_PHONE_TYPE_NAME);

        findElementBy(By.id(OFFICE_PHONE_FIELD_ID)).clear();
        findElementBy(By.id(OFFICE_PHONE_FIELD_ID)).sendKeys(email);

        findElementBy(By.id(OK_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu updatePhones(String email) {
        localContext(3);
        findElementBy(By.id(OFFICE_PHONES_ID)).click();

        updatePhoneService(email);

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: update phone
    private void updatePhoneService(String email) {
        nameOfPhone = findElementsBy(By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(countOfPhones).getText();
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(countOfPhones).click();
        findElementBy(By.id(OFFICE_PHONE_EDIT_ID)).click();
        setReasonToUpdate(OFFICE_REASON_UPDATED);

        setPhoneProps(email);

        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(3);
        nameOfPhoneUpdated = findElementsBy(By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(countOfPhones).getText();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu removePhone() {
        localContext(3);
        findElementBy(By.id(OFFICE_PHONES_ID)).click();
        removePhoneService();

        back();
        return ManagementManageOfficesSubmenu.get();
    }

    //region HELPER METHODS: remove covered states
    private void removePhoneService() {
        countOfPhones = findElementsBy(By.cssSelector(LIST_SELECTOR)).size();

        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(countOfPhones-1).click();
        findElementBy(By.id(OFFICE_PHONE_REMOVE_ID)).click();

        Browser.get().getDriver().switchTo().parentFrame();
        findElementsBy(By.tagName(BUTTON)).get(3).click();
//        findElementsBy(By.tagName(BUTTON)).get(1).click();
        setReasonToUpdate(OFFICE_REASON_REMOVE);

        countOfPhonesUpdated = Browser.get().waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    //region COMMON METHODS
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

    private void setReasonToUpdate(String reason) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(4);

        findElementBy(By.id(OFFICE_REASON_FIELD_ID)).sendKeys(reason);
        findElementBy(By.id(OK_BUTTON_ID)).click();
    }

    private void setCoveredStatesProps(String state) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(4);

        Select lenderSystems = new Select(findElementBy(By.id(OFFICE_COVERED_STATES_SELECT_ID)));
        lenderSystems.selectByVisibleText(state);
        findElementBy(By.id(OK_BUTTON_ID)).click();
    }

    private void localContext(int frame) {
        getContext();
        Browser.get().waitFrame(frame);
    }

    private void back() {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(2);
        Browser.get().waitFrame(0);
    }
    //endregion
}
