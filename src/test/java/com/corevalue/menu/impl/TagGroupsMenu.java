package com.corevalue.menu.impl;

import com.corevalue.driver.TestGroup;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  TagGroupsMenu implements AbstractMenu {
    INSTANCE(Menus.TAG_GROUPS);

    private Menus menu;

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
