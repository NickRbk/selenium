package com.corevalue.xsubmenu.impl;

import com.corevalue.constant.Tags;
import com.corevalue.driver.TestGroup;
import com.corevalue.xsubmenu.AbstractSubmenu;
import com.corevalue.xsubmenu.Submenus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum FileLogoutSubmenu implements AbstractSubmenu {
    INSTANCE(Submenus.FILE_LOGOUT);

    public void confirmLogout(TestGroup group, int buttonIndex) {
        getContext(group);
        findElementsBy(group, By.tagName(Tags.BUTTON.toString()))
                .get(buttonIndex).click();
    }

    private Submenus submenu;

    @Override
    public void goTo(TestGroup group, int submenuFrameIndex) {
        browser(group).getDriver()
                .switchTo().frame(submenuFrameIndex);
        findElementBy(group, By.id(submenu.getSelector())).click();
    }
}
