package com.corevalue.menu.impl;

import com.corevalue.constants.menu.FileMenuConst;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import com.corevalue.submenu.Submenus;
import com.corevalue.submenu.impl.FileLogoutSubmenu;

public class FileMenu extends AbstractMenu implements FileMenuConst {
    private static FileMenu instance;

    private FileMenu() {
        super(Menus.FILE);
    }

    public static FileMenu get() {
        if (instance == null) {
            instance = new FileMenu();
        }
        return instance;
    }

    public FileLogoutSubmenu openLogoutSubmenu(int submenuFrameIndex) {
        Submenus.getSubmenu(Submenus.FILE_LOGOUT, FileLogoutSubmenu.get()).goTo(submenuFrameIndex);
        return FileLogoutSubmenu.get();
    }
}
