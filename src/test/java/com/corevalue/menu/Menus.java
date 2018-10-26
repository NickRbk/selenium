package com.corevalue.menu;

import com.corevalue.constant.menu.MainXMenuConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@AllArgsConstructor
public enum Menus implements MainXMenuConst {
    FILE(MENU_FILE_ID),
    WIZARDS(MENU_WIZARDS_ID),
    TAG_GROUPS(MENU_TAG_GROUPS_ID),
    STATUS(MENU_STATUS_ID),
    REPORTS(MENU_REPORTS_ID),
    DOCUMENTS(MENU_DOCUMENTS_ID),
    MANAGEMENT(MENU_MANAGEMENT_ID),
    TASC(MENU_TASC_ID),
    BILLING(MENU_BILLING_ID),
    HELP(MENU_HELP_ID);

    @Getter private String selector;

    public static AbstractMenu getMenuPage(TestGroup group, AbstractMenu menu) {
        WebDriver driver = BrowserMap.INSTANCE.getDrivers().get(group).getDriver();
        PageFactory.initElements(driver, menu);
        return menu;
    }
}
