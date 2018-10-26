package com.corevalue.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.corevalue.constant.TestConst.DELAY_WAIT_BUTTON;
import static com.corevalue.constant.TestConst.WAIT_TIMEOUT_SEC;

public class Browser implements IBrowser {

    private WebDriver driver;

    Browser(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public WebElement waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SEC);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public WebDriver waitFrame(int frame) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SEC);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    @Override
    public void waitToClickDisabledButtonByID(String elementId) {
        while (!getDriver().findElement(By.id(elementId)).isEnabled()) {
            delay(DELAY_WAIT_BUTTON);
        }
        getDriver().findElement(By.id(elementId)).click();
    }

    @Override
    public void delay(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
