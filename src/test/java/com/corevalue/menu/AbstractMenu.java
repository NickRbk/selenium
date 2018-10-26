package com.corevalue.menu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;

public interface AbstractMenu {
    void goTo(TestGroup group);

    default IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
}
