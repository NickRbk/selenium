package com.corevalue.pages.impl;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.Menus;
import com.corevalue.menu.impl.FileMenu;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.menu.impl.ManagementMenu;
import com.corevalue.pages.IAuthorizedLandingPage;
import org.openqa.selenium.By;

import static com.corevalue.constants.TagsConst.BUTTON;

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
        browser(group).waitElement(By.tagName(BUTTON)).click();
    }

    private IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
}
