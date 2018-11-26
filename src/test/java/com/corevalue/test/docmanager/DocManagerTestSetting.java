package com.corevalue.test.docmanager;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.impl.LoginPage;
import com.corevalue.ymenu.YMenus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static com.corevalue.constant.TestConst.BASE_URL;

public class DocManagerTestSetting {
    private TestGroup testGroup;
    private String validUsername;
    private String validPassword;

    @Parameters({"testGroup", "username", "password"})
    public DocManagerTestSetting(String param, String username, String password) {
        testGroup = TestGroup.valueOf(param);
        validUsername = username;
        validPassword = password;
    }

    @BeforeTest
    void init() {
        driver().get(BASE_URL);
        LoginPage.INSTANCE.login(testGroup, validUsername, validPassword)
                .openPageWithCase(testGroup);

        browser().waitFrame(0).findElement(YMenus.DOC_MANAGER.getSelector()).click();
        String currentWindow = driver().getWindowHandle();
        driver().getWindowHandles().forEach(w -> {
            if(!w.equalsIgnoreCase(currentWindow)) {
                driver().switchTo().window(w);
            }
        });
    }

    @AfterTest
    void close() {
        driver().getWindowHandles().forEach(w -> driver().switchTo().window(w).close());
    }

    private WebDriver driver() {
        return browser().getDriver();
    }

    private IBrowser browser() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup);
    }
}
