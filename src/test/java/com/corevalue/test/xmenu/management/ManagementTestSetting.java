package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import org.testng.annotations.*;

public class ManagementTestSetting implements TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    @BeforeGroups(groups = "management", alwaysRun = true)
    @Parameters({"testGroup", "username", "password"})
    static void init(String param, String validUsername, String validPassword) {
        TestGroup testGroup = TestGroup.valueOf(param);

        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, validUsername,validPassword)
                .openPageWithCase(testGroup);
    }

    @AfterGroups(groups = "management", alwaysRun = true)
    @Parameters("testGroup")
    static void close(String param) {
        TestGroup testGroup = TestGroup.valueOf(param);
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE, BUTTON_LOGOUT_SUBMIT_WITH_CASE);
        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().close();
    }
}
