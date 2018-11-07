package com.corevalue.xsubmenu.impl;

import com.corevalue.constant.Tags;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.xsubmenu.AbstractSubmenu;
import com.corevalue.xsubmenu.Submenus;
import com.corevalue.xsubmenu.impl.obj.Office;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

@Getter
public enum ManagementManageOfficesSubmenu implements AbstractSubmenu, ManagementMenuConst {
    INSTANCE(Submenus.MANAGEMENT_MANAGE_OFFICES);

    ManagementManageOfficesSubmenu(Submenus submenu) {
        this.submenu = submenu;
    }

    private Submenus submenu;
    private int countOfCoveredStates;
    private int countOfCoveredStatesUpdated;
    private int countOfPhones;
    private int countOfPhonesUpdated;
    private String nameOfCoveredStates;
    private String nameOfCoveredStatesUpdated;
    private String nameOfPhone;
    private String nameOfPhoneUpdated;

    @Override
    public void goTo(TestGroup group, int submenuFrameIndex) {
        browser(group).getDriver()
                .switchTo().frame(submenuFrameIndex);
        findElementBy(group, By.id(submenu.getSelector())).click();
    }

    public ManagementManageOfficesSubmenu searchOffices(TestGroup group) {
        localContext(group, 2);
        findElementBy(group, By.id(SEARCH_BUTTON_ID)).click();
        return this;
    }

    public ManagementManageOfficesSubmenu selectLastOfficeToUpdate(TestGroup group) {
        int officeCount = getCountOfOffices(group);
        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(officeCount-1).click();
        findElementBy(group, By.id(UPDATE_BUTTON_ID)).click();
        return this;
    }

    public ManagementManageOfficesSubmenu updateOfficeGeneralInfo(TestGroup group, String reason, Office office) {
        localContext(group, 3);
        setOfficeProps(group, office.getName(), office.getCode());
        updateAddressAndAcceptChanges(group, reason, office.getAddress1(), office.getAddress2(), office.getCity());
        return this;
    }

    //region HELPER METHODS: general info
    private void setOfficeProps(TestGroup group, String name, String code) {
        findElementBy(group, By.cssSelector(OFFICE_NAME_FIELD_ID)).clear();
        findElementBy(group, By.cssSelector(OFFICE_NAME_FIELD_ID)).sendKeys(name);
        findElementBy(group, By.cssSelector(OFFICE_CODE_FIELD_ID)).clear();
        findElementBy(group, By.cssSelector(OFFICE_CODE_FIELD_ID)).sendKeys(code);
    }

    private int getCountOfOffices(TestGroup group) {
        browser(group).waitFrame(0);
        return findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();
    }

    private void updateAddressAndAcceptChanges(TestGroup group, String reason, String addr1, String addr2, String city) {
        findElementBy(group, By.id(UPDATE_ADDRESS_BUTTON_ID)).click();
        setReasonToUpdate(group, reason);
        setAddressProps(group, addr1, addr2, city);

        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(3);
        findElementBy(group, By.id(ACCEPT_ADDRESS_BUTTON_ID)).click();
    }

    private void setAddressProps(TestGroup group, String addr1, String addr2, String city) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(4);

        findElementBy(group, By.id(OFFICE_ADDR1_FIELD_ID)).clear();
        findElementBy(group, By.id(OFFICE_ADDR1_FIELD_ID)).sendKeys(addr1);
        findElementBy(group, By.id(OFFICE_ADDR2_FIELD_ID)).clear();
        findElementBy(group, By.id(OFFICE_ADDR2_FIELD_ID)).sendKeys(addr2);
        findElementBy(group, By.id(OFFICE_CITY_FIELD_ID)).clear();
        findElementBy(group, By.id(OFFICE_CITY_FIELD_ID)).sendKeys(city);
        findElementBy(group, By.id(OK_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu addCoveredState(TestGroup group, String state) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_COVERED_STATES_ID)).click();
        setCoveredStates(group, state);

        back(group);
        return this;
    }

    //region HELPER METHODS: add covered states
    private void setCoveredStates(TestGroup group, String state) {
        countOfCoveredStates = findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(OFFICE_COVERED_STATES_ADD_STATE_ID)).click();
        setReasonToUpdate(group, OFFICE_REASON_UPDATED);

        setCoveredStatesProps(group, state);

        browser(group).getDriver().switchTo().parentFrame();
        countOfCoveredStatesUpdated = browser(group).waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu removeCoveredState(TestGroup group) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_COVERED_STATES_ID)).click();

        removeCoveredStateService(group);

        back(group);
        return this;
    }

    //region HELPER METHODS: remove covered states
    private void removeCoveredStateService(TestGroup group) {
        countOfCoveredStates = findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();

        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(countOfCoveredStates - 2).click();
        findElementBy(group, By.id(OFFICE_COVERED_STATES_REMOVE_STATE_ID)).click();
        setReasonToUpdate(group, OFFICE_REASON_REMOVE);

        countOfCoveredStatesUpdated = browser(group).waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu updateCoveredState(TestGroup group, String state) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_COVERED_STATES_ID)).click();

        updateCoveredStateService(group, state);

        back(group);
        return this;
    }

    //region HELPER METHODS: update covered states
    private void updateCoveredStateService(TestGroup group, String state) {
        nameOfCoveredStates = findElementsBy(group, By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(1).getText();

        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(countOfCoveredStatesUpdated - 2).click();
        findElementBy(group, By.id(OFFICE_COVERED_STATES_EDIT_STATE_ID)).click();
        setReasonToUpdate(group, OFFICE_REASON_UPDATED);

        setCoveredStatesProps(group, state);

        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(3);
        nameOfCoveredStatesUpdated = findElementsBy(group, By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(1).getText();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu addPhone(TestGroup group, String email) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_PHONES_ID)).click();
        setPhone(group, email);

        back(group);
        return this;
    }

    //region HELPER METHODS: add phone
    private void setPhone(TestGroup group, String email) {
        countOfPhones = findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(OFFICE_PHONE_ADD_ID)).click();
        setReasonToUpdate(group, OFFICE_REASON_UPDATED);

        setPhoneProps(group, email);

        browser(group).getDriver().switchTo().parentFrame();
        countOfPhonesUpdated = browser(group).waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }

    private void setPhoneProps(TestGroup group, String email) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(4);

        Select phoneType = new Select(findElementBy(group, By.id(OFFICE_PHONE_TYPE_SELECT_ID)));
        phoneType.selectByVisibleText(OFFICE_PHONE_TYPE_NAME);

        findElementBy(group, By.id(OFFICE_PHONE_FIELD_ID)).clear();
        findElementBy(group, By.id(OFFICE_PHONE_FIELD_ID)).sendKeys(email);

        findElementBy(group, By.id(OK_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu updatePhones(TestGroup group, String email) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_PHONES_ID)).click();

        updatePhoneService(group, email);

        back(group);
        return this;
    }

    //region HELPER METHODS: update phone
    private void updatePhoneService(TestGroup group, String email) {
        nameOfPhone = findElementsBy(group, By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(countOfPhones).getText();
        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(countOfPhones).click();
        findElementBy(group, By.id(OFFICE_PHONE_EDIT_ID)).click();
        setReasonToUpdate(group, OFFICE_REASON_UPDATED);

        setPhoneProps(group, email);

        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(3);
        nameOfPhoneUpdated = findElementsBy(group, By.cssSelector(OFFICE_COVERED_STATE_NAME_SELECTOR)).get(countOfPhones).getText();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    public ManagementManageOfficesSubmenu removePhone(TestGroup group) {
        localContext(group, 3);
        findElementBy(group, By.id(OFFICE_PHONES_ID)).click();
        removePhoneService(group);

        back(group);
        return this;
    }

    //region HELPER METHODS: remove covered states
    private void removePhoneService(TestGroup group) {
        countOfPhones = findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();

        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(countOfPhones-1).click();
        findElementBy(group, By.id(OFFICE_PHONE_REMOVE_ID)).click();

        browser(group).getDriver().switchTo().parentFrame();
        findElementsBy(group, By.tagName(Tags.BUTTON.toString())).get(3).click();
//        findElementsBy(By.tagName(BUTTON)).get(1).click();
        setReasonToUpdate(group, OFFICE_REASON_REMOVE);

        countOfPhonesUpdated = browser(group).waitFrame(3).findElements(By.cssSelector(LIST_SELECTOR)).size();

        findElementBy(group, By.id(FINISH_BUTTON_ID)).click();
    }
    //endregion

    //region COMMON METHODS
    public Office getUpdatedFields(TestGroup group) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(2);
        int lastIndex = getCountOfOffices(group)-1;
        return Office.builder()
                .name(findElementsBy(group, By.cssSelector(OFFICE_NAME_SELECTOR)).get(lastIndex).getText())
                .code(findElementsBy(group, By.cssSelector(OFFICE_CODE_SELECTOR)).get(lastIndex).getText())
                .address1(findElementsBy(group, By.cssSelector(OFFICE_ADDR1_SELECTOR)).get(lastIndex).getText())
                .address2(findElementsBy(group, By.cssSelector(OFFICE_ADDR2_SELECTOR)).get(lastIndex).getText())
                .city(findElementsBy(group, By.cssSelector(OFFICE_CITY_SELECTOR)).get(lastIndex).getText())
                .build();
    }

    private void setReasonToUpdate(TestGroup group, String reason) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(4);

        findElementBy(group, By.id(OFFICE_REASON_FIELD_ID)).sendKeys(reason);
        findElementBy(group, By.id(OK_BUTTON_ID)).click();
    }

    private void setCoveredStatesProps(TestGroup group, String state) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(4);

        Select lenderSystems = new Select(findElementBy(group, By.id(OFFICE_COVERED_STATES_SELECT_ID)));
        lenderSystems.selectByVisibleText(state);
        findElementBy(group, By.id(OK_BUTTON_ID)).click();
    }

    private void localContext(TestGroup group, int frame) {
        getContext(group);
        browser(group).waitFrame(frame);
    }

    private void back(TestGroup group) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(2);
        browser(group).waitFrame(0);
    }
    //endregion
}
