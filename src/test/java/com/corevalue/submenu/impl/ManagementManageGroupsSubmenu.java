package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
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

    public ManagementManageGroupsSubmenu searchGroups(TestGroup group) {
        countInitial = getCountOfGroupsInitial(group);
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu addGroup(TestGroup group, String name, String description) {
        getContext(group);
        browser(group).waitFrame(2).switchTo().frame(0);
        findElementBy(group, By.id(GROUP_ADD_BUTTON_ID)).click();
        getContext(group);
        browser(group).waitFrame(3);
        setNewProps(group, name, description);
        confirmGroupAdd(group);
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu updateGroup(TestGroup group, String name, String description) {
        findElementsBy(group, By.cssSelector(GROUPS_SELECTOR)).get(countInitial-1).click();
        getContext(group);
        browser(group).waitFrame(2).switchTo().frame(0);
        findElementBy(group, By.id(GROUP_UPDATE_BUTTON_ID)).click();
        getContext(group);
        browser(group).waitFrame(3);
        setNewProps(group, name, description);
        confirmGroupUpdate(group);

        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageGroupsSubmenu removeGroup(TestGroup group) {
        findElementsBy(group, By.cssSelector(GROUPS_SELECTOR)).get(countInitial-1).click();
        findElementBy(group, By.id(GROUP_REMOVE_BUTTON_ID)).click();
        confirmGroupRemove(group);
        return ManagementManageGroupsSubmenu.get();
    }

    //region HELPER METHODS
    private void setNewProps(TestGroup group, String name, String description) {
        findElementBy(group, By.id(GROUP_NAME_FIELD_ID)).clear();
        findElementBy(group, By.id(GROUP_NAME_FIELD_ID)).sendKeys(name);
        browser(group).delay(DELAY_INPUT);
        findElementBy(group, By.id(GROUP_DESCRIPTION_FIELD_ID)).clear();
        findElementBy(group, By.id(GROUP_DESCRIPTION_FIELD_ID)).sendKeys(description);
    }

    private void confirmGroupAdd(TestGroup group) {
        findElementBy(group, By.id(GROUP_SUBMIT)).click();
        browser(group).delay(WAIT_TIMEOUT);
        browser(group).getDriver().switchTo().parentFrame();
        countUpdated = getCountOfGroupsUpdated(group);
    }

    private void confirmGroupUpdate(TestGroup group) {
        findElementBy(group, By.id(GROUP_SUBMIT)).click();
        browser(group).delay(WAIT_TIMEOUT);
    }

    private void confirmGroupRemove(TestGroup group) {
        getContext(group);
        findElementsBy(group, By.tagName(BUTTON)).get(1).click();
        browser(group).delay(WAIT_TIMEOUT);
        countUpdated = getCountOfGroupsUpdated(group);
    }

    private int getCountOfGroupsInitial(TestGroup group) {
        getContext(group);
        browser(group).waitFrame(2).switchTo().frame(0);
        return findElementsBy(group, By.cssSelector(GROUPS_SELECTOR)).size();
    }

    private int getCountOfGroupsUpdated(TestGroup group) {
        browser(group).getDriver().switchTo().frame(2).switchTo().frame(0);
        return findElementsBy(group, By.cssSelector(GROUPS_SELECTOR)).size();
    }
    //endregion
}
