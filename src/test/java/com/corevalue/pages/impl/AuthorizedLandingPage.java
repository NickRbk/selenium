package com.corevalue.pages.impl;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.driver.Browser;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.menu.Menus;
import com.corevalue.menu.impl.FileMenu;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.menu.impl.ManagementMenu;
import com.corevalue.pages.IAuthorizedLandingPage;
import org.openqa.selenium.By;

import static com.corevalue.constants.TagsConst.BUTTON;

public class AuthorizedLandingPage implements IAuthorizedLandingPage, AuthorizedLandingPageConst {
    private static IAuthorizedLandingPage instance;

    private AuthorizedLandingPage() {}

    public static IAuthorizedLandingPage get() {
        if (instance == null) {
            instance = new AuthorizedLandingPage();
        }
        return instance;
    }

    @Override
    public FileMenu openFileMenu(TestGroup group) {
        Menus.getMenuPage(Menus.FILE, group, FileMenu.get()).goTo(group);
        return FileMenu.get();
    }

    @Override
    public HelpMenu openHelpMenu(TestGroup group) {
        Menus.getMenuPage(Menus.HELP, group, HelpMenu.get()).goTo(group);
        return HelpMenu.get();
    }

    @Override
    public ManagementMenu openManagementMenu(TestGroup group) {
        Menus.getMenuPage(Menus.MANAGEMENT, group, ManagementMenu.get()).goTo(group);
        return ManagementMenu.get();
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

    private static Browser browser(TestGroup group) {
        return BrowserMap.get().getDrivers().get(group);
    }
}
