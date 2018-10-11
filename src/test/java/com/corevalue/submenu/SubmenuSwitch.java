package com.corevalue.submenu;

import com.corevalue.driver.Browser;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SubmenuSwitch {

    private static SubmenuSwitch instance;

    private SubmenuSwitch() {}

    public static SubmenuSwitch get() {
        if (instance == null) {
            instance = new SubmenuSwitch();
        }
        return instance;
    }

    Map<Submenus, Function<AbstractSubmenu, AbstractSubmenu>> getSubmenuSwitch() {
        Map<Submenus, Function<AbstractSubmenu, AbstractSubmenu>> submenus = new HashMap<>();

        submenus.put(Submenus.FILE_LOGOUT, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_GROUPS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_USERS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_CLIENTS, this::getSubmenu);
        submenus.put(Submenus.MANAGEMENT_MANAGE_OFFICES, this::getSubmenu);

        return submenus;
    }

    private AbstractSubmenu getSubmenu(AbstractSubmenu submenu) {
        PageFactory.initElements(Browser.get().getDriver(), submenu);
        return submenu;
    }
}
