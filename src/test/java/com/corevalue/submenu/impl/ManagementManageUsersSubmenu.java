package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import org.openqa.selenium.By;

public class ManagementManageUsersSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageUsersSubmenu instance;

    private ManagementManageUsersSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_USERS);
    }

    public static ManagementManageUsersSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageUsersSubmenu();
        }
        return instance;
    }

    public void updateUser(int userCount, String userName) {
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(userCount-1).click();
        findElementBy(By.id(UPDATE_BUTTON_ID)).click();
        setNewProps(userName);
        findElementBy(By.id(USERS_SUBMIT)).click();
    }

    private void setNewProps(String name) {
        getContext();
        Browser.get().waitFrame(3, 15);
        findElementBy(By.id(USERS_NAME_FIELD_ID)).clear();
        findElementBy(By.id(USERS_NAME_FIELD_ID)).sendKeys(name);
        findElementBy(By.id(USERS_SECTION_ROLES_ID)).click();
        findElementBy(By.id(USERS_SELECTED_ROLE_ID)).click();
        findElementBy(By.id(USERS_SECTION_GROUPS_ID)).click();
        findElementBy(By.id(USERS_SELECTED_GROUP_ID)).click();
    }

    public int getCountOfTargetUsers() {
        Browser.get().waitFrame(0, 15);
        return findElementsBy(By.cssSelector(LIST_SELECTOR)).size();
    }

    public void searchUsersByLogin(String login) {
        getContext();
        Browser.get().waitFrame(2, 15);
        findElementBy(By.id(USERS_LOGIN_FIELD_ID)).sendKeys(login);
        findElementBy(By.id(SEARCH_BUTTON_ID)).click();
    }
}
