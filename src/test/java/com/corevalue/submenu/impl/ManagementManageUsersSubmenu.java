package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ManagementManageUsersSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageUsersSubmenu instance;
    private int count;

    private ManagementManageUsersSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_USERS);
    }

    public static ManagementManageUsersSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageUsersSubmenu();
        }
        return instance;
    }

    public ManagementManageUsersSubmenu updateUser(TestGroup group, String userName) {
        setNewProps(group, userName);
        findElementBy(group, By.id(USERS_SUBMIT)).click();
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageUsersSubmenu searchUsersByLogin(TestGroup group, String login) {
        getContext(group);
        browser(group).waitFrame(2);
        findElementBy(group, By.id(USERS_LOGIN_FIELD_ID)).sendKeys(login);
        findElementBy(group, By.id(SEARCH_BUTTON_ID)).click();
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageUsersSubmenu selectLastUserToUpdate(TestGroup group) {
        count = getCountOfTargetUsers(group);
        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(count-1).click();
        findElementBy(group, By.id(UPDATE_BUTTON_ID)).click();
        return ManagementManageUsersSubmenu.get();
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
