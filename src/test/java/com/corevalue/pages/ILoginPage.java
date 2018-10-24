package com.corevalue.pages;

import com.corevalue.driver.TestGroup;

public interface ILoginPage {
    IAuthorizedLandingPage login(TestGroup group, String username, String password);
}
