package com.corevalue.menu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.By;

public abstract class AbstractMenu {
    private Menus menu;

    public AbstractMenu(Menus menu) {
        this.menu = menu;
    }

    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }

    protected IBrowser browser(TestGroup group) {
        return BrowserMap.get().getDrivers().get(group);
    }
}
