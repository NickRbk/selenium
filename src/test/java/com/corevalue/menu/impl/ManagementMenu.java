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

public class ManagementMenu extends AbstractMenu implements MenuConst, ManagementMenuConst {
    private static ManagementMenu instance;

    private ManagementMenu() {
        super(Menus.MANAGEMENT);
    }

    public static ManagementMenu get() {
        if (instance == null) {
            instance = new ManagementMenu();
        }
        return instance;
    }

    public ManagementManageGroupsSubmenu openManageGroupsSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageGroupsSubmenu.get())
                .goTo(group, submenuFrameIndex);
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageUsersSubmenu openManageUsersSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageUsersSubmenu.get())
                .goTo(group, submenuFrameIndex);
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageClientsSubmenu openManageClientsSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageClientsSubmenu.get())
                .goTo(group, submenuFrameIndex);
        return ManagementManageClientsSubmenu.get();
    }

    public ManagementManageOfficesSubmenu openManageOfficesSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, ManagementManageOfficesSubmenu.get())
                .goTo(group, submenuFrameIndex);
        return ManagementManageOfficesSubmenu.get();
    }
}
