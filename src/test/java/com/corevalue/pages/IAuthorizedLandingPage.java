package com.corevalue.pages;

import com.corevalue.menu.impl.ManagementMenu;

public interface IAuthorizedLandingPage {
    void openFileMenu();
    void openHelpMenu();
    ManagementMenu openManagementMenu();
    void openPageWithCase();
    void logout(int submenuFrameIndex, int buttonIndex);
}
