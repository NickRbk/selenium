package com.corevalue.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser implements IBrowser {

    private static IBrowser instance;
    private WebDriver driver;

    private Browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static IBrowser get() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public WebElement waitElement(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public WebDriver waitFrame(int frame, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    @Override
    public void waitToClickDisabledButtonByID(String elementId) {
        while (!Browser.get().getDriver().findElement(By.id(elementId)).isEnabled()) {
            Browser.get().delay(1000);
        }
        Browser.get().getDriver().findElement(By.id(elementId)).click();
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
