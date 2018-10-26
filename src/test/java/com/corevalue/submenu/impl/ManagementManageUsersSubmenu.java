package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import lombok.Getter;
import org.openqa.selenium.By;

public enum ManagementManageUsersSubmenu implements AbstractSubmenu, ManagementMenuConst {
    INSTANCE(Submenus.MANAGEMENT_MANAGE_USERS);

    ManagementManageUsersSubmenu(Submenus submenu) {
        this.submenu = submenu;
    }

    private Submenus submenu;
    @Getter private int count;

    @Override
    public void goTo(TestGroup group, int submenuFrameIndex) {
        browser(group).getDriver()
                .switchTo().frame(submenuFrameIndex);
        findElementBy(group, By.id(submenu.getSelector())).click();
    }

    public ManagementManageUsersSubmenu updateUser(TestGroup group, String userName) {
        setNewProps(group, userName);
        findElementBy(group, By.id(USERS_SUBMIT)).click();
        return this;
    }

    public ManagementManageUsersSubmenu searchUsersByLogin(TestGroup group, String login) {
        getContext(group);
        browser(group).waitFrame(2);
        findElementBy(group, By.id(USERS_LOGIN_FIELD_ID)).sendKeys(login);
        findElementBy(group, By.id(SEARCH_BUTTON_ID)).click();
        return this;
    }

    public ManagementManageUsersSubmenu selectLastUserToUpdate(TestGroup group) {
        count = getCountOfTargetUsers(group);
        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(count-1).click();
        findElementBy(group, By.id(UPDATE_BUTTON_ID)).click();
        return this;
    }

    private int getCountOfTargetUsers(TestGroup group) {
        browser(group).waitFrame(0);
        return findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();
    }

    private void setNewProps(TestGroup group, String name) {
        getContext(group);
        browser(group).waitFrame(3);
        findElementBy(group, By.id(USERS_NAME_FIELD_ID)).clear();
        findElementBy(group, By.id(USERS_NAME_FIELD_ID)).sendKeys(name);
        findElementBy(group, By.id(USERS_SECTION_ROLES_ID)).click();
        findElementBy(group, By.id(USERS_SELECTED_ROLE_ID)).click();
        findElementBy(group, By.id(USERS_SECTION_GROUPS_ID)).click();
        findElementBy(group, By.id(USERS_SELECTED_GROUP_ID)).click();
    }
}
