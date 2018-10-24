package com.corevalue.menu.impl;

import com.corevalue.constants.menu.HelpMenuConst;
import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.TestGroup;
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

    public String showVersion() {
        browser(TestGroup.INIT).getDriver()
                .switchTo().frame(6)
                .findElement(By.id(SUBMENU_SELECTOR))
                .click();

        return HelpMenu.get().versionInfo().getText();
    }

    public void confirmExit() {
        browser(TestGroup.INIT).getDriver()
                .findElement(By.id(BUTTON_CLOSE_ID))
                .click();
    }

    private WebElement versionInfo () {
        browser(TestGroup.INIT).getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();

        return browser(TestGroup.INIT)
                .waitFrame(2)
                .findElement(By.cssSelector(VERSION_ELEMENT_SELECTOR));
    }
}
