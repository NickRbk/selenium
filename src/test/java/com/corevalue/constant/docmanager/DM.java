package com.corevalue.constant.docmanager;

import lombok.Getter;
import org.openqa.selenium.By;

public enum DM {
    ID_DOC_TREE(By.id("browser")),
    CSS_DOC_INFO(By.cssSelector("#mainContainer > div > div > span")),
    DISPLAY(By.linkText("Display All Case File Documents")),
    HIDE(By.linkText("Hide All Case File Documents")),
    ID_EMAIL_FORM(By.id("emailFormTitle")),
    ID_QUIT_EMAIL_FORM(By.cssSelector("#emailDocumentFormDlg button")),
    LINK_SEND_EMAIL(By.linkText("Send Email")),
    LINK_DOWNLOAD(By.linkText("Download")),
    LINK_DOWNLOAD_SELECTED(By.linkText("Download Selected")),
    LINK_CANCEL_DOWNLOAD(By.linkText("Cancel Download"));

    @Getter private By selector;

    DM(By selector) {
        this.selector = selector;
    }
}
