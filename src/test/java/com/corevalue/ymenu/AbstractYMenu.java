package com.corevalue.ymenu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface AbstractYMenu {

    void goTo(TestGroup group);

    default WebElement findElementBy(TestGroup group, By selector) {
        return browser(group).waitElement(selector);
    }

    default List<WebElement> findElementsBy(TestGroup group, By selector) {
        return browser(group).getDriver().findElements(selector);
    }

    default IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
}
