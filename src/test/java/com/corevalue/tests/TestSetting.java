package com.corevalue.tests;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import org.testng.annotations.AfterSuite;

public class TestSetting {
    @AfterSuite
    static void closeSessions() {
        BrowserMap.INSTANCE.getDrivers().get(TestGroup.INITIAL).getDriver().close();
        BrowserMap.INSTANCE.getDrivers().get(TestGroup.MANAGEMENT).getDriver().close();
    }
}
