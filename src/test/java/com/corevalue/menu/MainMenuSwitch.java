package com.corevalue.menu;

import com.corevalue.driver.Browser;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class MainMenuSwitch {

    private static MainMenuSwitch instance;

    private MainMenuSwitch() {}

    public static MainMenuSwitch get() {
        if (instance == null) {
            instance = new MainMenuSwitch();
        }
        return instance;
    }

    Map<Menus, Function<AbstractMenu, AbstractMenu>> getMenuSwitch() {
        Map<Menus, Function<AbstractMenu, AbstractMenu>> menus = new HashMap<>();

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

    private AbstractMenu getMenu(AbstractMenu menu) {
        PageFactory.initElements(Browser.get().getDriver(), menu);
        return menu;
    }
}
