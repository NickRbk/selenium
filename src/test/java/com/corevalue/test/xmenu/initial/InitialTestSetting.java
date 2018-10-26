package com.corevalue.test.xmenu.initial;

import com.corevalue.constant.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class InitialTestSetting implements TestConst {

    @AfterTest
    @Parameters("testGroup")
    static void close(String param) {
        TestGroup testGroup = TestGroup.valueOf(param);
        BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver().close();
    }
}
