package com.corevalue.submenu;

import com.corevalue.constant.menu.MenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.corevalue.constant.menu.ManagementMenuConst.WAIT_TIMEOUT_2;

public interface AbstractSubmenu extends MenuConst {

    void goTo(TestGroup group, int submenuFrameIndex);

    default String getUpdatedField(TestGroup group, int totalCount, String selector) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(2);
        browser(group).waitFrame(0);
        browser(group).delay(WAIT_TIMEOUT_2);
        return findElementsBy(group, By.cssSelector(selector)).get(totalCount-1).getText();
    }

    default void close(TestGroup group, String selector) {
        browser(group).getDriver().switchTo().parentFrame();
        findElementBy(group, By.id(selector)).click();
    }

    default void getContext(TestGroup group) {
        browser(group).getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();
    }

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
