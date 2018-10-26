package com.corevalue.page.impl;

import com.corevalue.constant.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.page.IAuthorizedLandingPage;
import com.corevalue.page.ILoginPage;
import com.corevalue.constant.LoginPageConst;
import org.openqa.selenium.By;

public enum LoginPage implements ILoginPage, TestConst, LoginPageConst {
    INSTANCE;

    @Override
    public IAuthorizedLandingPage login(TestGroup group, String username, String password) {
        setLogin(group, username);
        browser(group).delay(DELAY_INPUT);
        setPassword(group, password);
        submit(group);

        return AuthorizedLandingPage.INSTANCE;
    }

    //region HELPER METHODS
    private void setLogin(TestGroup group, String login) {
        browser(group)
                .waitElement(By.id(LOGIN_FIELD_ID))
                .sendKeys(login);
    }

    private void setPassword(TestGroup group, String password) {
        browser(group)
                .waitElement(By.id(PASSWORD_FIELD_ID))
                .sendKeys(password);
    }

    private void submit(TestGroup group) {
        browser(group).getDriver().findElement(By.id(SUBMIT_ID)).click();
    }

    private IBrowser browser(TestGroup group) {
        return BrowserMap.INSTANCE.getDrivers().get(group);
    }
    //endregion
}
