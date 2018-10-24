package com.corevalue.submenu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class SubmenuSwitch {

    private static SubmenuSwitch instance;

    private SubmenuSwitch() {}

    public static SubmenuSwitch get() {
        if (instance == null) {
            instance = new SubmenuSwitch();
        }
        return instance;
    }

    Map<Submenus, BiFunction<TestGroup, AbstractSubmenu, AbstractSubmenu>> getSubmenuSwitch() {
        Map<Submenus, BiFunction<TestGroup, AbstractSubmenu, AbstractSubmenu>> submenus = new HashMap<>();

        submenus.put(Submenus.FILE_LOGOUT, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_GROUPS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_USERS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_CLIENTS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_OFFICES, this::getSubmenu);

        return submenus;
    }

    private AbstractSubmenu getSubmenu(TestGroup group, AbstractSubmenu submenu) {
        WebDriver driver = BrowserMap.get().getDrivers().get(group).getDriver();
        PageFactory.initElements(driver, submenu);
        return submenu;
    }
}
