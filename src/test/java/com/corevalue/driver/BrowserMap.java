package com.corevalue.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserMap {
    private static BrowserMap instance;
    private static final Map<TestGroup, IBrowser> driverInstanceMap;

    static {
        WebDriverManager.chromedriver().setup();
        driverInstanceMap = generateDriverInstanceMap();
    }

    private BrowserMap() {}

    public static BrowserMap get() {
        if (instance == null) {
            instance = new BrowserMap();
        }
        return instance;
    }

    public Map<TestGroup, IBrowser> getDrivers() {
        return  driverInstanceMap;
    }

    private static Map<TestGroup, IBrowser> generateDriverInstanceMap() {
        Map<TestGroup, IBrowser> map = new HashMap<>();
        for (TestGroup el : TestGroup.values()) {
            WebDriver driver = new ChromeDriver();
            map.put(el, new Browser(driver));
        }
        return map;
    }
}
