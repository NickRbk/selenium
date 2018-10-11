package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class ReportsMenu extends AbstractMenu {
    private static ReportsMenu instance;

    private ReportsMenu() {
        super(Menus.REPORTS);
    }

    public static ReportsMenu get() {
        if (instance == null) {
            instance = new ReportsMenu();
        }
        return instance;
    }
}
