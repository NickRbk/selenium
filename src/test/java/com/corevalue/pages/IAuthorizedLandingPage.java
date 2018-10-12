package com.corevalue.pages;

import com.corevalue.menu.impl.FileMenu;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.menu.impl.ManagementMenu;

public interface IAuthorizedLandingPage {
    FileMenu openFileMenu();
    HelpMenu openHelpMenu();
    ManagementMenu openManagementMenu();
    void openPageWithCase();
    void logout(int submenuFrameIndex, int buttonIndex);
}
