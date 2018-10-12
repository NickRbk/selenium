package com.corevalue.submenu;

import com.corevalue.constants.menu.MenuConst;
import com.corevalue.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.corevalue.constants.menu.ManagementMenuConst.WAIT_TIMEOUT_2;

public abstract class AbstractSubmenu implements MenuConst {
    private Submenus submenu;

    public AbstractSubmenu(Submenus submenu) {
        this.submenu = submenu;
    }

    public void goTo(int submenuFrameIndex) {
        Browser.get().getDriver()
                .switchTo().frame(submenuFrameIndex);
        findElementBy(By.id(submenu.getSelector())).click();
    }

    public String getUpdatedField(int totalCount, String selector) {
        Browser.get().getDriver().switchTo().parentFrame();
        Browser.get().waitFrame(2);
        Browser.get().waitFrame(0);
        Browser.get().delay(WAIT_TIMEOUT_2);
        return findElementsBy(By.cssSelector(selector)).get(totalCount-1).getText();
    }

    public void close(String selector) {
        Browser.get().getDriver().switchTo().parentFrame();
        findElementBy(By.id(selector)).click();
    }

    protected void getContext() {
        Browser.get().getDriver()
                .switchTo().parentFrame()
                .switchTo().parentFrame();
    }

    protected WebElement findElementBy(By selector) {
        return Browser.get().waitElement(selector);
    }

    protected List<WebElement> findElementsBy(By selector) {
        return Browser.get().getDriver().findElements(selector);
    }
}
