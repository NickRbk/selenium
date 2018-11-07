package com.corevalue.test.xmenu.management;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.LoginPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.AuthorizedLandingPage;
import com.corevalue.page.impl.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ManagementTestSetting implements LoginPageConst, TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    private static TestGroup testGroup;

    @Parameters("testGroup")
    public ManagementTestSetting(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @BeforeGroups(groups = "management", alwaysRun = true)
    static void init() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD)
                .openPageWithCase(testGroup);
    }

    @AfterGroups(groups = "management", alwaysRun = true)
    static void close() {
        AuthorizedLandingPage.INSTANCE.logout(testGroup, SUBMENU_FRAME_INDEX_WITH_CASE, BUTTON_LOGOUT_SUBMIT_WITH_CASE);
        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().close();
    }

    private static WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
