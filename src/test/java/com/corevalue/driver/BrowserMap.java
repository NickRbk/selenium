package com.corevalue.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum BrowserMap {
    INSTANCE;

    BrowserMap() {
        WebDriverManager.chromedriver().setup();
        drivers = generateDriverInstanceMap();
    }

    private final Map<TestGroup, IBrowser> drivers;

    private Map<TestGroup, IBrowser> generateDriverInstanceMap() {
        Map<TestGroup, IBrowser> map = new HashMap<>();
        for (TestGroup el : TestGroup.values()) {
            WebDriver driver = new ChromeDriver();
            map.put(el, new Browser(driver));
        }
        return map;
    }
}
