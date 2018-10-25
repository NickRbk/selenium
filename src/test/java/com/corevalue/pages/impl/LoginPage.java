package com.corevalue.pages.impl;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.pages.IAuthorizedLandingPage;
import com.corevalue.pages.ILoginPage;
import com.corevalue.constants.LoginPageConst;
import org.openqa.selenium.By;

public class LoginPage implements ILoginPage, TestConst, LoginPageConst {
    private static ILoginPage instance;

    private LoginPage() {}

    public static ILoginPage get(TestGroup group) {
        if (instance == null) {
            instance = new LoginPage();
        }
        browser(group).getDriver().get(BASE_URL);
        return instance;
    }

    @Override
    public IAuthorizedLandingPage login(TestGroup group, String username, String password) {
        setLogin(group, username);
        browser(group).delay(DELAY_INPUT);
        setPassword(group, password);
        submit(group);

        return AuthorizedLandingPage.get();
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

    private static IBrowser browser(TestGroup group) {
        return BrowserMap.get().getDrivers().get(group);
    }
    //endregion
}
