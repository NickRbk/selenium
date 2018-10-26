package com.corevalue.menu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import com.corevalue.submenu.Submenus;
import com.corevalue.submenu.impl.ManagementManageClientsSubmenu;
import com.corevalue.submenu.impl.ManagementManageGroupsSubmenu;
import com.corevalue.submenu.impl.ManagementManageOfficesSubmenu;
import com.corevalue.submenu.impl.ManagementManageUsersSubmenu;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  ManagementMenu implements AbstractMenu, MenuConst, ManagementMenuConst {
    INSTANCE(Menus.MANAGEMENT);

    private Menus menu;

    public ManagementManageGroupsSubmenu openManageGroupsSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageGroupsSubmenu.INSTANCE)
                .goTo(group, submenuFrameIndex);
        return ManagementManageGroupsSubmenu.INSTANCE;
    }

    public ManagementManageUsersSubmenu openManageUsersSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageUsersSubmenu.INSTANCE)
                .goTo(group, submenuFrameIndex);
        return ManagementManageUsersSubmenu.INSTANCE;
    }

    public ManagementManageClientsSubmenu openManageClientsSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageClientsSubmenu.INSTANCE)
                .goTo(group, submenuFrameIndex);
        return ManagementManageClientsSubmenu.INSTANCE;
    }

    public ManagementManageOfficesSubmenu openManageOfficesSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageOfficesSubmenu.INSTANCE)
                .goTo(group, submenuFrameIndex);
        return ManagementManageOfficesSubmenu.INSTANCE;
    }

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
