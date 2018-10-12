package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
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

    public ManagementManageUsersSubmenu updateUser(String userName) {
        setNewProps(userName);
        findElementBy(By.id(USERS_SUBMIT)).click();
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageUsersSubmenu searchUsersByLogin(String login) {
        getContext();
        Browser.get().waitFrame(2);
        findElementBy(By.id(USERS_LOGIN_FIELD_ID)).sendKeys(login);
        findElementBy(By.id(SEARCH_BUTTON_ID)).click();
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageUsersSubmenu selectLastUserToUpdate() {
        count = getCountOfTargetUsers();
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(count-1).click();
        findElementBy(By.id(UPDATE_BUTTON_ID)).click();
        return ManagementManageUsersSubmenu.get();
    }

    private int getCountOfTargetUsers() {
        Browser.get().waitFrame(0);
        return findElementsBy(By.cssSelector(LIST_SELECTOR)).size();
    }

    private void setNewProps(String name) {
        getContext();
        Browser.get().waitFrame(3);
        findElementBy(By.id(USERS_NAME_FIELD_ID)).clear();
        findElementBy(By.id(USERS_NAME_FIELD_ID)).sendKeys(name);
        findElementBy(By.id(USERS_SECTION_ROLES_ID)).click();
        findElementBy(By.id(USERS_SELECTED_ROLE_ID)).click();
        findElementBy(By.id(USERS_SECTION_GROUPS_ID)).click();
        findElementBy(By.id(USERS_SELECTED_GROUP_ID)).click();
    }
}
