package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class BillingMenu extends AbstractMenu {
    private static BillingMenu instance;

    private BillingMenu() {
        super(Menus.BILLING);
    }

    public static BillingMenu get() {
        if (instance == null) {
            instance = new BillingMenu();
        }
        return instance;
    }
}
