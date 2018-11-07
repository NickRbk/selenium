package com.corevalue.xmenu.impl;

import com.corevalue.driver.TestGroup;
import com.corevalue.xmenu.AbstractMenu;
import com.corevalue.xmenu.Menus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  ReportsMenu implements AbstractMenu {
    INSTANCE(Menus.REPORTS);

    private Menus menu;

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
