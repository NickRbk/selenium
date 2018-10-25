package com.corevalue.submenu;

import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.corevalue.constants.menu.ManagementMenuConst.WAIT_TIMEOUT_2;

public abstract class AbstractSubmenu implements MenuConst {
    private Submenus submenu;

    public AbstractSubmenu(Submenus submenu) {
        this.submenu = submenu;
    }

    public void goTo(TestGroup group, int submenuFrameIndex) {
        browser(group).getDriver()
                .switchTo().frame(submenuFrameIndex);
        findElementBy(group, By.id(submenu.getSelector())).click();
    }

    public String getUpdatedField(TestGroup group, int totalCount, String selector) {
        browser(group).getDriver().switchTo().parentFrame();
        browser(group).waitFrame(2);
        browser(group).waitFrame(0);
        browser(group).delay(WAIT_TIMEOUT_2);
        return findElementsBy(group, By.cssSelector(selector)).get(totalCount-1).getText();
    }

    public void close(TestGroup group, String selector) {
        browser(group).getDriver().switchTo().parentFrame();
        findElementBy(group, By.id(selector)).click();
    }

    protected void getContext(TestGroup group) {
        browser(group).getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();
    }

    protected WebElement findElementBy(TestGroup group, By selector) {
        return browser(group).waitElement(selector);
    }

    protected List<WebElement> findElementsBy(TestGroup group, By selector) {
        return browser(group).getDriver().findElements(selector);
    }

    protected IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
}
