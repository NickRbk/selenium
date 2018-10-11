package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class StatusMenu extends AbstractMenu {
    private static StatusMenu instance;

    private StatusMenu() {
        super(Menus.STATUS);
    }

    public static StatusMenu get() {
        if (instance == null) {
            instance = new StatusMenu();
        }
        return instance;
    }
}
