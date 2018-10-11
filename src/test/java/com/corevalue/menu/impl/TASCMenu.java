package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class TASCMenu extends AbstractMenu {
    private static TASCMenu instance;

    private TASCMenu() {
        super(Menus.TASC);
    }

    public static TASCMenu get() {
        if (instance == null) {
            instance = new TASCMenu();
        }
        return instance;
    }
}
