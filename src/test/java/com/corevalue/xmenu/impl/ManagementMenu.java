package com.corevalue.xmenu.impl;

import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.constant.menu.MenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.xmenu.AbstractMenu;
import com.corevalue.xmenu.Menus;
import com.corevalue.xsubmenu.Submenus;
import com.corevalue.xsubmenu.impl.ManagementManageClientsSubmenu;
import com.corevalue.xsubmenu.impl.ManagementManageGroupsSubmenu;
import com.corevalue.xsubmenu.impl.ManagementManageOfficesSubmenu;
import com.corevalue.xsubmenu.impl.ManagementManageUsersSubmenu;
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
