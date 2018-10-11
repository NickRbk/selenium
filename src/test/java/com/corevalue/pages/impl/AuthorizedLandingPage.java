package com.corevalue.pages.impl;

import com.corevalue.constants.AuthorizedLandingPageConst;
import com.corevalue.driver.Browser;
import com.corevalue.menu.Menus;
import com.corevalue.menu.impl.FileMenu;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.menu.impl.ManagementMenu;
import com.corevalue.pages.IAuthorizedLandingPage;
import com.corevalue.submenu.impl.FileLogoutSubmenu;
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
    public void openFileMenu() {
        Menus.getMenuPage(Menus.FILE, FileMenu.get()).goTo();
    }

    @Override
    public void openPageWithCase() {
        Browser.get().getDriver()
                .switchTo().frame(0)
                .findElement(By.id(BUTTON_SEARCH_ID)).click();

        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(2, 10);

        Browser.get().waitToClickDisabledButtonByID(SUBMIT_ID);

        Browser.get().getDriver().switchTo().parentFrame();

        Browser.get().waitElement(By.tagName(BUTTON), 30).click();
    }

    @Override
    public void openHelpMenu() {
        Menus.getMenuPage(Menus.HELP, HelpMenu.get()).goTo();
    }

    @Override
    public ManagementMenu openManagementMenu() {
        Menus.getMenuPage(Menus.MANAGEMENT, ManagementMenu.get()).goTo();
        return ManagementMenu.get();
    }

    @Override
    public void logout(int submenuFrameIndex, int buttonIndex) {
        openFileMenu();
        FileMenu.get().openLogoutSubmenu(submenuFrameIndex);
        FileLogoutSubmenu.get().confirmLogout(buttonIndex);
    }
}
