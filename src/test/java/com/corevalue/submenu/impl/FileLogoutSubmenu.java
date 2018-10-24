package com.corevalue.submenu.impl;

import com.corevalue.driver.TestGroup;
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

    public void confirmLogout(TestGroup group, int buttonIndex) {
        getContext(group);
        findElementsBy(group, By.tagName(BUTTON))
                .get(buttonIndex).click();
    }
}
