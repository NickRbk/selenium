package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.Browser;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ManagementManageClientsSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageClientsSubmenu instance;

    private ManagementManageClientsSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_CLIENTS);
    }

    public static ManagementManageClientsSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageClientsSubmenu();
        }
        return instance;
    }

    public void searchClientsByCode(String code) {
        getContext();
        Browser.get().waitFrame(2, 15);
        findElementBy(By.id(CLIENTS_CODE_FIELD_ID)).sendKeys(code);
        findElementBy(By.id(SEARCH_BUTTON_ID)).click();
    }

    public int getCountOfTargetClients() {
        Browser.get().waitFrame(0, 15);
        return findElementsBy(By.cssSelector(LIST_SELECTOR)).size();
    }

    public void updateClient(int userCount,String code, String name) {
        findElementsBy(By.cssSelector(LIST_SELECTOR)).get(userCount-1).click();
        findElementBy(By.id(UPDATE_BUTTON_ID)).click();
        setNewProps(code, name);
        findElementBy(By.id(CLIENTS_SUBMIT)).click();
    }

    private void setNewProps(String code, String name) {
        getContext();
        Browser.get().waitFrame(3, 40);
        findElementBy(By.cssSelector(CLIENT_CODE_FIELD_ID)).clear();
        findElementBy(By.cssSelector(CLIENT_CODE_FIELD_ID)).sendKeys(code);
        findElementBy(By.cssSelector(CLIENT_NAME_FIELD_ID)).clear();
        findElementBy(By.cssSelector(CLIENT_NAME_FIELD_ID)).sendKeys(name);
        Select lenderSystems = new Select(findElementBy(By.cssSelector(CLIENT_LENDER_SYSTEM_FIELD_SELECTOR)));
        lenderSystems.selectByIndex(LENDER_SYSTEM_OTHER_INDEX);
    }
}
