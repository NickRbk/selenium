package com.corevalue.ymenu.docmanager;

import com.corevalue.constant.Tags;
import com.corevalue.constant.docmanager.DM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public enum DocManager {
    INSTANCE;

    public void openDocument(WebDriver driver) {
        WebElement menuStage1 = driver.findElements(DM.ID_DOC_TREE.getSelector()).get(0)
                .findElements(By.tagName(Tags.LI.toString())).get(0);
        menuStage1.click();
        WebElement menuStage2 = menuStage1.findElement(By.tagName(Tags.UL.toString()))
                .findElements(By.tagName(Tags.LI.toString())).get(0);
        menuStage2.click();
        WebElement menuStage3 = menuStage2.findElement(By.tagName(Tags.UL.toString()))
                .findElements(By.tagName(Tags.LI.toString())).get(1)
                .findElement(By.tagName(Tags.A.toString()));
        menuStage3.click();
    }

    public void deselectDocument(WebDriver driver) {
        driver.findElements(DM.ID_DOC_TREE.getSelector()).get(0)
                .findElements(By.tagName(Tags.LI.toString())).get(0)
                .findElement(By.tagName(Tags.UL.toString()))
                .findElements(By.tagName(Tags.LI.toString())).get(0)
                .findElement(By.tagName(Tags.UL.toString()))
                .findElements(By.tagName(Tags.LI.toString())).get(1)
                .findElement(By.cssSelector(Tags.DIV.toString())).click();
    }
}
