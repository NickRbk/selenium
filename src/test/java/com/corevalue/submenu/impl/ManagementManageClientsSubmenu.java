package com.corevalue.submenu.impl;

import com.corevalue.constants.menu.ManagementMenuConst;
import com.corevalue.driver.TestGroup;
import com.corevalue.submenu.AbstractSubmenu;
import com.corevalue.submenu.Submenus;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

@Getter
public class ManagementManageClientsSubmenu extends AbstractSubmenu implements ManagementMenuConst {
    private static ManagementManageClientsSubmenu instance;
    private int count;

    private ManagementManageClientsSubmenu() {
        super(Submenus.MANAGEMENT_MANAGE_CLIENTS);
    }

    public static ManagementManageClientsSubmenu get() {
        if (instance == null) {
            instance = new ManagementManageClientsSubmenu();
        }
        return instance;
    }

    public ManagementManageClientsSubmenu updateClient(TestGroup group, String code, String name) {
        setNewProps(group, code, name);
        findElementBy(group, By.id(CLIENTS_SUBMIT)).click();
        return ManagementManageClientsSubmenu.get();
    }

    public ManagementManageClientsSubmenu searchClientsByCode(TestGroup group, String code) {
        getContext(group);
        browser(group).waitFrame(2);
        findElementBy(group, By.id(CLIENTS_CODE_FIELD_ID)).sendKeys(code);
        findElementBy(group, By.id(SEARCH_BUTTON_ID)).click();
        count = getCountOfTargetClients(group);
        return ManagementManageClientsSubmenu.get();
    }

    public ManagementManageClientsSubmenu selectLastClientToUpdate(TestGroup group) {
        findElementsBy(group, By.cssSelector(LIST_SELECTOR)).get(count-1).click();
        findElementBy(group, By.id(UPDATE_BUTTON_ID)).click();
        return ManagementManageClientsSubmenu.get();
    }

    private int getCountOfTargetClients(TestGroup group) {
        browser(group).waitFrame(0);
        return findElementsBy(group, By.cssSelector(LIST_SELECTOR)).size();
    }

    private void setNewProps(TestGroup group, String code, String name) {
        getContext(group);
        browser(group).waitFrame(3);
        findElementBy(group, By.cssSelector(CLIENT_CODE_FIELD_ID)).clear();
        findElementBy(group, By.cssSelector(CLIENT_CODE_FIELD_ID)).sendKeys(code);
        findElementBy(group, By.cssSelector(CLIENT_NAME_FIELD_ID)).clear();
        findElementBy(group, By.cssSelector(CLIENT_NAME_FIELD_ID)).sendKeys(name);
        Select lenderSystems = new Select(findElementBy(group, By.cssSelector(CLIENT_LENDER_SYSTEM_FIELD_SELECTOR)));
        lenderSystems.selectByIndex(LENDER_SYSTEM_OTHER_INDEX);
    }
}
