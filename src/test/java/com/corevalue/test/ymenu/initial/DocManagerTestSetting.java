package com.corevalue.test.ymenu.initial;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.LoginPage;
import com.corevalue.ymenu.YMenus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static com.corevalue.constant.LoginPageConst.VALID_PASSWORD;
import static com.corevalue.constant.LoginPageConst.VALID_USERNAME;
import static com.corevalue.constant.TestConst.BASE_URL;

public class DocManagerTestSetting {
    private static TestGroup testGroup;

    @Parameters("testGroup")
    public DocManagerTestSetting(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @BeforeTest
    void init() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, VALID_USERNAME,VALID_PASSWORD)
                .openPageWithCase(testGroup);

        driver().switchTo().frame(0).findElement(YMenus.DOC_MANAGER.getSelector()).click();
        String currentWindow = driver().getWindowHandle();
        driver().getWindowHandles().forEach(w -> {
            if(!w.equalsIgnoreCase(currentWindow)) {
                driver().switchTo().window(w);
            }
        });
    }

    @AfterTest
    static void close() {
        driver().getWindowHandles().forEach(w -> {
            driver().switchTo().window(w).close();
        });
    }

    private static WebDriver driver() {
        return browser().getDriver();
    }

    private static IBrowser browser() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup);
    }
}
