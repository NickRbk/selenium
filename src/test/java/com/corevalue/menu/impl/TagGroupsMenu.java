package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class TagGroupsMenu extends AbstractMenu {
    private static TagGroupsMenu instance;

    private TagGroupsMenu() {
        super(Menus.TAG_GROUPS);
    }

    public static TagGroupsMenu get() {
        if (instance == null) {
            instance = new TagGroupsMenu();
        }
        return instance;
    }
}
