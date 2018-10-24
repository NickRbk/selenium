package com.corevalue.menu;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

class MainMenuSwitch {

    private static MainMenuSwitch instance;

    private MainMenuSwitch() {}

    public static MainMenuSwitch get() {
        if (instance == null) {
            instance = new MainMenuSwitch();
        }
        return instance;
    }

    Map<Menus, BiFunction<TestGroup, AbstractMenu, AbstractMenu>> getMenuSwitch() {
        Map<Menus, BiFunction<TestGroup, AbstractMenu, AbstractMenu>> menus = new HashMap<>();

        menus.put(Menus.FILE, this::getMenu);
        menus.put(Menus.WIZARDS, this::getMenu);
        menus.put(Menus.TAG_GROUPS, this::getMenu);
        menus.put(Menus.STATUS, this::getMenu);
        menus.put(Menus.REPORTS, this::getMenu);
        menus.put(Menus.DOCUMENTS, this::getMenu);
        menus.put(Menus.MANAGEMENT, this::getMenu);
        menus.put(Menus.TASC, this::getMenu);
        menus.put(Menus.BILLING, this::getMenu);
        menus.put(Menus.HELP, this::getMenu);

        return menus;
    }

    private AbstractMenu getMenu(TestGroup group, AbstractMenu menu) {
        WebDriver driver = BrowserMap.get().getDrivers().get(group).getDriver();
        PageFactory.initElements(driver, menu);
        return menu;
    }
}
