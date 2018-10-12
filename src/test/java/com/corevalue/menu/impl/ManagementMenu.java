package com.corevalue.menu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.constants.menu.MenuConst;
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

    public ManagementManageGroupsSubmenu openManageGroupsSubmenu(int submenuFrameIndex) {
        Submenus.getSubmenu(Submenus.MANAGEMENT_MANAGE_GROUPS, ManagementManageGroupsSubmenu.get())
                .goTo(submenuFrameIndex);
        return ManagementManageGroupsSubmenu.get();
    }

    public ManagementManageUsersSubmenu openManageUsersSubmenu(int submenuFrameIndex) {
        Submenus.getSubmenu(Submenus.MANAGEMENT_MANAGE_USERS, ManagementManageUsersSubmenu.get())
                .goTo(submenuFrameIndex);
        return ManagementManageUsersSubmenu.get();
    }

    public ManagementManageClientsSubmenu openManageClientsSubmenu(int submenuFrameIndex) {
        Submenus.getSubmenu(Submenus.MANAGEMENT_MANAGE_CLIENTS, ManagementManageClientsSubmenu.get())
                .goTo(submenuFrameIndex);
        return ManagementManageClientsSubmenu.get();
    }

    public ManagementManageOfficesSubmenu openManageOfficesSubmenu(int submenuFrameIndex) {
        Submenus.getSubmenu(Submenus.MANAGEMENT_MANAGE_OFFICES, ManagementManageOfficesSubmenu.get())
                .goTo(submenuFrameIndex);
        return ManagementManageOfficesSubmenu.get();
    }
}
