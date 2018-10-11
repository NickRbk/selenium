package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class WizardsMenu extends AbstractMenu {
    private static WizardsMenu instance;

    private WizardsMenu() {
        super(Menus.WIZARDS);
    }

    public static WizardsMenu get() {
        if (instance == null) {
            instance = new WizardsMenu();
        }
        return instance;
    }
}
