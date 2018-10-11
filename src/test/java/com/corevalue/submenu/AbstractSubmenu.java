package com.corevalue.submenu;

import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.corevalue.constants.menu.ManagementMenuConst.WAIT_TIMEOUT_DOUBLE;

public abstract class AbstractSubmenu implements MenuConst {
    private Submenus submenu;

    public AbstractSubmenu(Submenus submenu) {
        this.submenu = submenu;
    }

    public void goTo(int submenuFrameIndex) {
        Browser.get().getDriver()
                .switchTo().frame(submenuFrameIndex)
                .findElement(By.id(submenu.getSelector()))
                .click();
    }

    public void close(String selector) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get()
                .waitElement(By.id(selector), 10)
                .click();
    }

    protected void getContext() {
        Browser.get().getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();
    }

    protected WebElement findElementBy(By selector) {
        return Browser.get().waitElement(selector, 10);
    }

    protected List<WebElement> findElementsBy(By selector) {
        return Browser.get().getDriver().findElements(selector);
    }

    public String getUpdatedField(int totalCount, String selector) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(2, 15);
        Browser.get().waitFrame(0, 15);
        Browser.get().delay(WAIT_TIMEOUT_DOUBLE);
        return findElementsBy(By.cssSelector(selector)).get(totalCount-1).getText();
    }
}
