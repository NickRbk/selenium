package com.corevalue.menu;

import com.corevalue.driver.Browser;
import org.openqa.selenium.By;

public abstract class AbstractMenu {
    private Menus menu;

    public AbstractMenu(Menus menu) {
        this.menu = menu;
    }

    public void goTo() {
        Browser.get().getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
