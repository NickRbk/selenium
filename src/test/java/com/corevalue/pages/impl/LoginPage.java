package com.corevalue.pages.impl;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.Browser;
import com.corevalue.pages.IAuthorizedLandingPage;
import com.corevalue.pages.ILoginPage;
import com.corevalue.constants.LoginPageConst;
import org.openqa.selenium.By;

public class LoginPage implements ILoginPage, TestConst, LoginPageConst {
    private static ILoginPage instance;

    private LoginPage() {}

    public static ILoginPage get() {
        if (instance == null) {
            instance = new LoginPage();
        }
        Browser.get().getDriver().get(BASE_URL);
        return instance;
    }

    @Override
    public IAuthorizedLandingPage login(String username, String password) {
        setLogin(username);
        Browser.get().delay(DELAY_INPUT);
        setPassword(password);
        submit();

        return AuthorizedLandingPage.get();
    }

    //region HELPER METHODS
    private void setLogin(String login) {
        Browser.get()
                .waitElement(By.id(LOGIN_FIELD_ID))
                .sendKeys(login);
    }

    private void setPassword(String password) {
        Browser.get()
                .waitElement(By.id(PASSWORD_FIELD_ID))
                .sendKeys(password);
    }

    private void submit() {
        Browser.get().getDriver().findElement(By.id(SUBMIT_ID)).click();
    }
    //endregion
}
