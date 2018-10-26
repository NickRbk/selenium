package com.corevalue.submenu.impl;

import com.corevalue.driver.TestGroup;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static com.corevalue.constant.TagsConst.BUTTON;

@AllArgsConstructor
public enum FileLogoutSubmenu implements AbstractSubmenu {
    INSTANCE(Submenus.FILE_LOGOUT);

    public void confirmLogout(TestGroup group, int buttonIndex) {
        getContext(group);
        findElementsBy(group, By.tagName(BUTTON))
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
