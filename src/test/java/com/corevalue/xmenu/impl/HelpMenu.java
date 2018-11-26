package com.corevalue.xmenu.impl;

import com.corevalue.constant.menu.HelpMenuConst;
import com.corevalue.constant.menu.MenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.xmenu.AbstractMenu;
import com.corevalue.xmenu.Menus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public enum HelpMenu implements AbstractMenu, MenuConst, HelpMenuConst {
    INSTANCE(Menus.HELP);

    private Menus menu;

    public String showVersion(TestGroup testGroup) {
        browser(testGroup).getDriver()
                .switchTo().frame(6)
                .findElement(By.id(SUBMENU_SELECTOR))
                .click();

        return versionInfo(testGroup).getText();
    }

    public void confirmExit(TestGroup testGroup) {
        browser(testGroup).getDriver()
                .findElement(By.id(BUTTON_CLOSE_ID))
                .click();
    }

    private WebElement versionInfo (TestGroup testGroup) {
        browser(testGroup).getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();

        return browser(testGroup)
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
