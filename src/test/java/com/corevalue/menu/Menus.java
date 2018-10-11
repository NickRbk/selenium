package com.corevalue.menu;

import com.corevalue.constants.menu.MainXMenuConst;
import lombok.Getter;

@Getter
public enum Menus implements MainXMenuConst {
    FILE(MENU_FILE_ID),
    WIZARDS(MENU_WIZARDS_ID),
    TAG_GROUPS(MENU_TAG_GROUPS_ID),
    STATUS(MENU_STATUS_ID),
    REPORTS(MENU_REPORTS_ID),
    DOCUMENTS(MENU_DOCUMENTS_ID),
    MANAGEMENT(MENU_MANAGEMENT_ID),
    TASC(MENU_TASC_ID),
    BILLING(MENU_BILLING_ID),
    HELP(MENU_HELP_ID);

    private String selector;

    Menus(String selector) {
        this.selector = selector;
    }

    public static AbstractMenu getMenuPage(Menus key, AbstractMenu menu) {
        return MainMenuSwitch.get().getMenuSwitch()
                .get(key)
                .apply(menu);
    }
}
