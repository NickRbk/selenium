package com.corevalue.pages.impl;

import com.corevalue.constants.TestConst;
import com.corevalue.driver.Browser;
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
        return instance;
    }

    @Override
    public void goTo() {
        Browser.get().getDriver().get(BASE_URL);
    }

    @Override
    public void login(String username, String password) {
        setLogin(username);
        Browser.get().delay(300);
        setPassword(password);
        submit();
    }

    private void setLogin(String login) {
        Browser.get()
                .waitElement(By.id(LOGIN_FIELD_ID), 10)
                .sendKeys(login);
    }

    private void setPassword(String password) {
        Browser.get()
                .waitElement(By.id(PASSWORD_FIELD_ID), 10)
                .sendKeys(password);
    }

    private void submit() {
        Browser.get().getDriver().findElement(By.id(SUBMIT_ID)).click();
    }
}
