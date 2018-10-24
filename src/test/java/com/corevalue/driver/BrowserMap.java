package com.corevalue.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserMap {
    private static BrowserMap instance;
    private final Map<TestGroup, Browser> driverInstanceMap;

    static {
        WebDriverManager.chromedriver().setup();
    }

    private BrowserMap() {
        driverInstanceMap = generateDriverInstanceMap();
    }

    public static BrowserMap get() {
        if (instance == null) {
            instance = new BrowserMap();
        }
        return instance;
    }

    public Map<TestGroup, Browser> getDrivers() {
        return  driverInstanceMap;
    }

    private Map<TestGroup, Browser> generateDriverInstanceMap() {
        Map<TestGroup, Browser> map = new HashMap<>();
        for (TestGroup el : TestGroup.values()) {
            WebDriver driver = new ChromeDriver();
            map.put(el, new Browser(driver));
        }
        System.out.println("MAP ================> " + map.size());
        return map;
    }
}
