package com.corevalue.submenu.impl;

import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import org.openqa.selenium.By;

import static com.corevalue.constants.TagsConst.BUTTON;

public class FileLogoutSubmenu extends AbstractSubmenu {

    private static FileLogoutSubmenu instance;

    private FileLogoutSubmenu() {
        super(Submenus.FILE_LOGOUT);
    }

    public static FileLogoutSubmenu get() {
        if (instance == null) {
            instance = new FileLogoutSubmenu();
        }
        return instance;
    }

    public void confirmLogout(int buttonIndex) {
        getContext();
        findElementsBy(By.tagName(BUTTON))
                .get(buttonIndex).click();
    }
}
