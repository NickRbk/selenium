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
    private static TestGroup testGroup;

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
    void openDocument() {
        DocManager.INSTANCE.openDocument(driver());

        String CREATED_BY = driver().findElement(DM.CSS_DOC_INFO.getSelector()).getText();
        boolean isFileOpened = CREATED_BY.contains("Created by");
        Assert.assertTrue(isFileOpened);
    }

    @Test(dependsOnMethods = "openDocument")
    void deselectDocumentAndTryToDownload() {
        driver().findElement(DM.LINK_DOWNLOAD.getSelector()).click();
        DocManager.INSTANCE.deselectDocument(driver());

        driver().findElement(DM.LINK_DOWNLOAD_SELECTED.getSelector()).click();
        String ALERT_MSG = driver().switchTo().alert().getText();
        driver().switchTo().alert().accept();

        boolean isDownloaded = !ALERT_MSG.contains("Please select a document for download.");

        setExitLogic(() -> driver().findElement(DM.LINK_CANCEL_DOWNLOAD.getSelector()).click());
        Assert.assertFalse(isDownloaded);
    }

    @Test(dependsOnMethods = "openDocument")
    void openSenEmailMenu() {
        driver().findElement(DM.LINK_SEND_EMAIL.getSelector()).click();
        browser().delay(5000);
        String emailFormTitle = browser().waitElement(DM.ID_EMAIL_FORM.getSelector()).getText();

        boolean isMenuOpen = emailFormTitle.contains("Send Document by Email");

        setExitLogic(() -> driver().findElement(DM.ID_QUIT_EMAIL_FORM.getSelector()).click());
        Assert.assertTrue(isMenuOpen);
    }

    protected WebDriver driver() {
        return browser().getDriver();
    }

    private IBrowser browser() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup);
    }
}
