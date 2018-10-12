package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.corevalue.constants.TagsConst.BUTTON;
import static com.corevalue.constants.TestConst.DELAY_INPUT;

@Getter
public class ManagementManageGroupsSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private int countInitial;
    private int countUpdated;

    private static ManagementManageGroupsSubmenu instance;

    private ManagementManageGroupsSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_GROUPS);
    }

    public static ManagementManageGroupsSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageGroupsSubmenu();
        }
        return instance;
    }

    public ManagementManageGroupsSubmenu searchGroups() {
        countInitial = getCountOfGroupsInitial();
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu addGroup(String name, String description) {
        getContext();
        Browser.get().waitFrame(2).switchTo().frame(0);
        findElementBy(By.id(GROUP_ADD_BUTTON_ID)).click();
        getContext();
        Browser.get().waitFrame(3);
        setNewProps(name, description);
        confirmGroupAdd();
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu updateGroup(String name, String description) {
        findElementsBy(By.cssSelector(GROUPS_SELECTOR)).get(countInitial-1).click();
        getContext();
        Browser.get().waitFrame(2).switchTo().frame(0);
        findElementBy(By.id(GROUP_UPDATE_BUTTON_ID)).click();
        getContext();
        Browser.get().waitFrame(3);
        setNewProps(name, description);
        confirmGroupUpdate();

        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu removeGroup() {
        findElementsBy(By.cssSelector(GROUPS_SELECTOR)).get(countInitial-1).click();
        findElementBy(By.id(GROUP_REMOVE_BUTTON_ID)).click();
        confirmGroupRemove();
        return ManagementManageGroupsSubmenu.get();
    }

    //region HELPER METHODS
    private void setNewProps(String name, String description) {
        findElementBy(By.id(GROUP_NAME_FIELD_ID)).clear();
        findElementBy(By.id(GROUP_NAME_FIELD_ID)).sendKeys(name);
        Browser.get().delay(DELAY_INPUT);
        findElementBy(By.id(GROUP_DESCRIPTION_FIELD_ID)).clear();
        findElementBy(By.id(GROUP_DESCRIPTION_FIELD_ID)).sendKeys(description);
    }

    private void confirmGroupAdd() {
        findElementBy(By.id(GROUP_SUBMIT)).click();
        Browser.get().delay(WAIT_TIMEOUT);
        Browser.get().getDriver().switchTo().parentFrame();
        countUpdated = getCountOfGroupsUpdated();
    }

    private void confirmGroupUpdate() {
        findElementBy(By.id(GROUP_SUBMIT)).click();
        Browser.get().delay(WAIT_TIMEOUT);
    }

    private void confirmGroupRemove() {
        getContext();
        findElementsBy(By.tagName(BUTTON)).get(1).click();
        Browser.get().delay(WAIT_TIMEOUT);
        countUpdated = getCountOfGroupsUpdated();
    }

    private int getCountOfGroupsInitial() {
        getContext();
        Browser.get().waitFrame(2).switchTo().frame(0);
        return findElementsBy(By.cssSelector(GROUPS_SELECTOR)).size();
    }

    private int getCountOfGroupsUpdated() {
        Browser.get().getDriver().switchTo().frame(2).switchTo().frame(0);
        return findElementsBy(By.cssSelector(GROUPS_SELECTOR)).size();
    }
    //endregion
}
