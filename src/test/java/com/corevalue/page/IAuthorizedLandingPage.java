package com.corevalue.page;

import com.corevalue.driver.TestGroup;
import com.corevalue.menu.impl.FileMenu;
import com.corevalue.menu.impl.HelpMenu;
import com.corevalue.menu.impl.ManagementMenu;

public interface IAuthorizedLandingPage {
    FileMenu openFileMenu(TestGroup group);
    HelpMenu openHelpMenu(TestGroup group);
    ManagementMenu openManagementMenu(TestGroup group);
    void openPageWithCase(TestGroup group);
    void logout(TestGroup group, int submenuFrameIndex, int buttonIndex);
}
