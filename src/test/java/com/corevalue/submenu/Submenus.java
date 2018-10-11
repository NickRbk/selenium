package com.corevalue.submenu;

import com.corevalue.constants.menu.FileMenuConst;
import com.corevalue.constants.menu.ManagementMenuConst;
import lombok.Getter;

@Getter
public enum Submenus implements FileMenuConst, ManagementMenuConst {
    FILE_LOGOUT(SUBMENU_LOGOUT_ID),
    MANAGEMENT_MANAGE_GROUPS(SUBMENU_MANAGE_GROUPS_ID),
    MANAGEMENT_MANAGE_USERS(SUBMENU_MANAGE_USERS_ID),
    MANAGEMENT_MANAGE_CLIENTS(SUBMENU_MANAGE_CLIENTS_ID),
    MANAGEMENT_MANAGE_OFFICES(SUBMENU_MANAGE_OFFICES_ID);

    private String selector;

    Submenus(String selector) {
        this.selector = selector;
    }

    public static AbstractSubmenu getSubmenu(Submenus key, AbstractSubmenu submenu) {
        return SubmenuSwitch.get().getSubmenuSwitch()
                .get(key)
                .apply(submenu);
    }
}
