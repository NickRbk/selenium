package com.corevalue.menu.impl;

import com.corevalue.driver.TestGroup;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  TASCMenu implements AbstractMenu {
    INSTANCE(Menus.TASC);

    private Menus menu;

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
