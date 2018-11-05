package com.corevalue.page.impl;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.Tags;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.xmenu.Menus;
import com.corevalue.xmenu.impl.FileMenu;
import com.corevalue.xmenu.impl.HelpMenu;
import com.corevalue.xmenu.impl.ManagementMenu;
import com.corevalue.page.IAuthorizedLandingPage;
import org.openqa.selenium.By;

public enum AuthorizedLandingPage implements IAuthorizedLandingPage, AuthorizedLandingPageConst {
    INSTANCE;

    @Override
    public FileMenu openFileMenu(TestGroup group) {
        Menus.getMenuPage(group, FileMenu.INSTANCE).goTo(group);
        return FileMenu.INSTANCE;
    }

    @Override
    public HelpMenu openHelpMenu(TestGroup group) {
        Menus.getMenuPage(group, HelpMenu.INSTANCE).goTo(group);
        return HelpMenu.INSTANCE;
    }

    @Override
    public ManagementMenu openManagementMenu(TestGroup group) {
        Menus.getMenuPage(group, ManagementMenu.INSTANCE).goTo(group);
        return ManagementMenu.INSTANCE;
    }

    @Override
    public void logout(TestGroup group, int submenuFrameIndex, int buttonIndex) {
        openFileMenu(group)
                .openLogoutSubmenu(group, submenuFrameIndex)
                .confirmLogout(group, buttonIndex);
    }

    @Override
    public void openPageWithCase(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(By.id(BUTTON_SEARCH_ID)).click();
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(2);
        browser(group).waitToClickDisabledButtonByID(SUBMIT_ID);
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitElement(By.tagName(Tags.BUTTON.toString())).click();
    }

    private IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
}
