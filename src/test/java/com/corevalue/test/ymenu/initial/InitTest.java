package com.corevalue.test.ymenu.initial;

import com.corevalue.constant.docmanager.DM;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.test.AbstractAfterMethod;
import com.corevalue.ymenu.docmanager.DocManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InitTest extends AbstractAfterMethod {
    private TestGroup testGroup;

    @Parameters("testGroup")
    public InitTest(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @Test
    void openDocManager() {
        driver().findElement(DM.DISPLAY.getSelector()).click();
        boolean isDocsExpanded = browser().waitElement(DM.HIDE.getSelector()).isDisplayed();
        Assert.assertTrue(isDocsExpanded);
    }

    @Test(dependsOnMethods = "openDocManager")
    void openEachDocuments() {
        new DocManager(testGroup).openEachDocuments();
    }

    protected WebDriver driver() {
        return browser().getDriver();
    }

    private IBrowser browser() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup);
    }
}
