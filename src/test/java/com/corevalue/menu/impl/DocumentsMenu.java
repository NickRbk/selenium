package com.corevalue.menu.impl;

import com.corevalue.menu.AbstractMenu;
import com.corevalue.menu.Menus;

public class DocumentsMenu extends AbstractMenu {
    private static DocumentsMenu instance;

    private DocumentsMenu() {
        super(Menus.DOCUMENTS);
    }

    public static DocumentsMenu get() {
        if (instance == null) {
            instance = new DocumentsMenu();
        }
        return instance;
    }
}
