package com.corevalue.test.ymenu.initial;

import com.corevalue.constant.AuthorizedLandingPageConst;
import com.corevalue.constant.TestConst;
import com.corevalue.constant.menu.ManagementMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.LoginPage;
import org.testng.annotations.*;

public class YMenuTestSetting implements TestConst, AuthorizedLandingPageConst, ManagementMenuConst {
    @BeforeTest
    @Parameters({"testGroup", "username", "password"})
    static void init(String param, String validUsername, String validPassword) {
        TestGroup testGroup = TestGroup.valueOf(param);

        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, validUsername,validPassword)
                .openPageWithCase(testGroup);
    }

    @AfterTest
    @Parameters("testGroup")
    static void close(String param) {
        TestGroup testGroup = TestGroup.valueOf(param);
        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().close();
    }
}
