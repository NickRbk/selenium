package com.corevalue.submenu;

import com.corevalue.constant.menu.FileMenuConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public enum Submenus implements FileMenuConst, ManagementMenuConst {
    FILE_LOGOUT(SUBMENU_LOGOUT_ID),
    MANAGEMENT_MANAGE_GROUPS(SUBMENU_MANAGE_GROUPS_ID),
    MANAGEMENT_MANAGE_USERS(SUBMENU_MANAGE_USERS_ID),
    MANAGEMENT_MANAGE_CLIENTS(SUBMENU_MANAGE_CLIENTS_ID),
    MANAGEMENT_MANAGE_OFFICES(SUBMENU_MANAGE_OFFICES_ID);

    @Getter private String selector;

    Submenus(String selector) {
        this.selector = selector;
    }

    public static AbstractSubmenu getSubmenu(TestGroup group, AbstractSubmenu submenu) {
        WebDriver driver = BrowserMap.INSTANCE.getDrivers().get(group).getDriver();
        PageFactory.initElements(driver, submenu);
        return submenu;
    }
}
