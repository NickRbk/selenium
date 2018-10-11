package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import org.openqa.selenium.By;

import static com.corevalue.constants.TagsConst.BUTTON;

public class ManagementManageGroupsSubmenu extends AbstractSubmenu implements ManagementMenuConst {
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

    public int getGroupsCountInitial() {
        getContext();
        Browser.get().waitFrame(2, 10).switchTo().frame(0);
        return findElementsBy(By.cssSelector(GROUPS_SELECTOR)).size();
    }

    public int getCountOfGroups() {
        Browser.get().getDriver().switchTo().frame(2).switchTo().frame(0);
        return findElementsBy(By.cssSelector(GROUPS_SELECTOR)).size();
    }

    public void addGroup(String name, String description) {
        getContext();
        Browser.get().waitFrame(2, 10).switchTo().frame(0);
        findElementBy(By.id(GROUP_ADD_BUTTON_ID)).click();
        getContext();
        Browser.get().waitFrame(3, 10);
        setNewProps(name, description);
        confirmGroupAdd();
    }

    public void updateGroup(int groupsCount, String name, String description) {
        findElementsBy(By.cssSelector(GROUPS_SELECTOR)).get(groupsCount - 1).click();
        getContext();
        Browser.get().waitFrame(2, 10).switchTo().frame(0);
        findElementBy(By.id(GROUP_UPDATE_BUTTON_ID)).click();
        getContext();
        Browser.get().waitFrame(3, 10);
        setNewProps(name, description);
        confirmGroupAdd();
    }

    public void removeGroup(int groupIndex) {
        findElementsBy(By.cssSelector(GROUPS_SELECTOR)).get(groupIndex-1).click();
        findElementBy(By.id(GROUP_REMOVE_BUTTON_ID)).click();
        confirmGroupRemove();
    }

    private void setNewProps(String name, String description) {
        findElementBy(By.id(GROUP_NAME_FIELD_ID)).clear();
        findElementBy(By.id(GROUP_NAME_FIELD_ID)).sendKeys(name);
        Browser.get().delay(300);
        findElementBy(By.id(GROUP_DESCRIPTION_FIELD_ID)).clear();
        findElementBy(By.id(GROUP_DESCRIPTION_FIELD_ID)).sendKeys(description);
    }

    private void confirmGroupAdd() {
        findElementBy(By.id(GROUP_SUBMIT)).click();
        Browser.get().delay(WAIT_TIMEOUT);
    }

    private void confirmGroupRemove() {
        getContext();
        findElementsBy(By.tagName(BUTTON)).get(1).click();
        Browser.get().delay(WAIT_TIMEOUT);
    }
}
