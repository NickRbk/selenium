package com.corevalue.menu.impl;

import com.corevalue.constants.menu.HelpMenuConst;
import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public enum HelpMenu implements AbstractMenu, MenuConst, HelpMenuConst {
    INSTANCE(Menus.HELP);

    private Menus menu;

    public String showVersion() {
        browser(TestGroup.INITIAL).getDriver()
                .switchTo().frame(6)
                .findElement(By.id(SUBMENU_SELECTOR))
                .click();
        return this.versionInfo().getText();
    }

    public void confirmExit() {
        browser(TestGroup.INITIAL).getDriver()
                .findElement(By.id(BUTTON_CLOSE_ID))
                .click();
    }

    private WebElement versionInfo () {
        browser(TestGroup.INITIAL).getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();

        return browser(TestGroup.INITIAL)
                .waitFrame(2)
                .findElement(By.cssSelector(VERSION_ELEMENT_SELECTOR));
    }

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(menu.getSelector())).click();
    }
}
