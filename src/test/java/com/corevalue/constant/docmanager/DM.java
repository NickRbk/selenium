package com.corevalue.constant.docmanager;

import lombok.Getter;
import org.openqa.selenium.By;

public enum DM {
    DOC_TREE_ID(By.id("browser")),
    MENU_SELECTOR(By.cssSelector("#browser > li")),
    SUBMENU_SELECTOR(By.cssSelector("#browser > li > ul > li")),
    DOC_SELECTOR(By.cssSelector("#browser > li > ul > li > ul > li")),

    DISPLAY(By.linkText("Display All Case File Documents")),
    HIDE(By.linkText("Hide All Case File Documents"));

    @Getter private By selector;

    DM(By selector) {
        this.selector = selector;
    }
}
