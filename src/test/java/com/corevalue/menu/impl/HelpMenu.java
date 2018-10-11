package com.corevalue.menu.impl;

import com.corevalue.constants.menu.HelpMenuConst;
import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelpMenu extends AbstractMenu implements MenuConst, HelpMenuConst {
    private static HelpMenu instance;

    private HelpMenu() {
        super(Menus.HELP);
    }

    public static HelpMenu get() {
        if (instance == null) {
            instance = new HelpMenu();
        }
        return instance;
    }

    public WebElement showVersion() {
        Browser.get().getDriver()
                .switchTo().frame(6)
                .findElement(By.id(SUBMENU_SELECTOR))
                .click();

        return HelpMenu.get().versionInfo();
    }

    private WebElement versionInfo () {
        Browser.get().getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();

        return Browser.get()
                .waitFrame(2, 10000)
                .findElement(By.cssSelector(VERSION_ELEMENT_SELECTOR));
    }

    public void confirmExit() {
        Browser.get().getDriver()
                .findElement(By.id(BUTTON_CLOSE_ID))
                .click();
    }
}
