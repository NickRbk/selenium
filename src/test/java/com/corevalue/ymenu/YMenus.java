package com.corevalue.ymenu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

@AllArgsConstructor
public enum  YMenus {
    DOC_MANAGER(By.id("s24"));

    @Getter
    private By selector;
}
