package com.corevalue.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IBrowser {
    WebDriver getDriver();
    WebElement waitElement(By locator);
    WebDriver waitFrame(int frame);
    void delay(int timeout);
    void waitToClickDisabledButtonByID(String elementId);
}
