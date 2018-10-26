package com.corevalue.menu.impl;

import com.corevalue.constant.menu.FileMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import com.corevalue.submenu.Submenus;
import com.corevalue.submenu.impl.FileLogoutSubmenu;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  FileMenu implements AbstractMenu, FileMenuConst {
    INSTANCE(Menus.FILE);

    private Menus menu;

    public FileLogoutSubmenu openLogoutSubmenu(TestGroup group, int submenuFrameIndex) {
        Submenus.getSubmenu(group, FileLogoutSubmenu.INSTANCE).goTo(group, submenuFrameIndex);
        return FileLogoutSubmenu.INSTANCE;
    }

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
