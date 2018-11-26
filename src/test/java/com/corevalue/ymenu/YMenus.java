package com.corevalue.ymenu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import com.corevalue.ymenu.bill.BillYMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@AllArgsConstructor
public enum  YMenus {
    BILL(By.id("s024")),
    DOC_MANAGER(By.id("s24"));

    @Getter private By selector;

    private static AbstractYMenu getYMenu(TestGroup group, AbstractYMenu menu) {
        WebDriver driver = BrowserMap.INSTANCE.getDrivers().get(group).getDriver();
        PageFactory.initElements(driver, menu);
        return menu;
    }

    public static BillYMenu openBillMenu(TestGroup group) {
        getYMenu(group, BillYMenu.INSTANCE).goTo(group);
        return BillYMenu.INSTANCE;
    }
}
