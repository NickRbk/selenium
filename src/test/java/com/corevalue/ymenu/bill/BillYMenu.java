package com.corevalue.ymenu.bill;

import com.corevalue.driver.TestGroup;
import com.corevalue.ymenu.AbstractYMenu;
import com.corevalue.ymenu.YMenus;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.corevalue.constant.menu.YMenuBillConst.*;

public enum BillYMenu implements AbstractYMenu {
    INSTANCE(YMenus.BILL);

    private YMenus menu;

    BillYMenu(YMenus menu) {
        this.menu = menu;
    }

    @Getter private int billSize;
    @Getter private int billSizeNew;

    @Override
    public void goTo(TestGroup group) {
        browser(group).getDriver()
                .switchTo().frame(0)
                .findElement(menu.getSelector()).click();
    }

    public Bill add(TestGroup testGroup) {
        driver(testGroup)
                .switchTo().frame(5)
                .switchTo().frame(0);

        browser(testGroup).waitElement(By.id(BILL_ADD_ID)).click();

        fillFields(testGroup);
        attachDocument(testGroup);
        acceptUpdate(testGroup);

        return getLastBillInfo(testGroup, ADD_BILL_OPTION);
    }

    public void remove(TestGroup testGroup) {
        findElementBy(testGroup, By.id(BILL_REMOVE_ID)).click();
        acceptRemove(testGroup);
        selectLastBillAndGetSize(testGroup, REMOVE_BILL_OPTION);
    }

    //region HELPER METHODS: add bill
    private void fillFields(TestGroup testGroup) {
        driver(testGroup)
                .switchTo().parentFrame()
                .switchTo().parentFrame()
                .switchTo().parentFrame();
        browser(testGroup).delay(2*DELAY);
        driver(testGroup).switchTo().frame(2);

        Select feeOrCostOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_FEE_TYPE_SELECTOR)));
        feeOrCostOption.selectByVisibleText(FEE_TYPE);

        Select departmentOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_DEPT_TYPE_SELECTOR)));
        departmentOption.selectByVisibleText(BILL_DEPT_TYPE);

        browser(testGroup).delay(DELAY);

        Select payeeTypeOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_TYPE_SELECTOR)));
        payeeTypeOption.selectByVisibleText(BILL_TYPE);

        findElementBy(testGroup, By.cssSelector(BILL_FIELD_AMOUNT_SELECTOR)).clear();
        findElementBy(testGroup, By.cssSelector(BILL_FIELD_AMOUNT_SELECTOR)).sendKeys(BILL_AMOUNT);

        findElementBy(testGroup, By.cssSelector(BILL_FIELD_QUANTITY_SELECTOR)).clear();
        findElementBy(testGroup, By.cssSelector(BILL_FIELD_QUANTITY_SELECTOR)).sendKeys(BILL_QUANTITY);

        findElementsBy(testGroup, By.cssSelector(BILL_FIELD_NOTES_SELECTOR)).get(1).clear();
        findElementsBy(testGroup, By.cssSelector(BILL_FIELD_NOTES_SELECTOR)).get(1).sendKeys(BILL_NOTES);

        findElementsBy(testGroup, By.cssSelector(BILL_FIELD_COMMENTS_SELECTOR)).get(1).clear();
        findElementsBy(testGroup, By.cssSelector(BILL_FIELD_COMMENTS_SELECTOR)).get(1).sendKeys(BILL_COMMENTS);

        Select borrowerOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_BORROWER_RCVB_SELECTOR)));
        borrowerOption.selectByVisibleText(BILL_BORROWER_RCVB);

        Select investorOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_INVESTOR_RCVB_SELECTOR)));
        investorOption.selectByVisibleText(BILL_INVESTOR_RCVB);

        Select evidenceCollectedOption = new Select(findElementBy(testGroup, By.cssSelector(BILL_FIELD_EVIDENCE_SELECTOR)));
        evidenceCollectedOption.selectByVisibleText(BILL_EVIDENCE);
    }

    private void attachDocument(TestGroup testGroup) {
        findElementBy(testGroup, By.cssSelector(EVIDENCE_ADD_SELECTOR)).click();

        driver(testGroup).switchTo().parentFrame();
        browser(testGroup).waitFrame(3);

        Select evidenceOption = new Select(findElementBy(testGroup, By.id(EVIDENCE_TYPE_ID)));
        evidenceOption.selectByVisibleText(EVIDENCE_TYPE);

        findElementBy(testGroup, By.cssSelector(DM_SELECTOR)).click();

        browser(testGroup).delay(2*DELAY);

        driver(testGroup).switchTo().parentFrame();
        browser(testGroup).waitFrame(4);

        findElementBy(testGroup, By.cssSelector(DM_DOCUMENT_SELECTOR)).click();
        browser(testGroup).waitElement(By.linkText(DM_DOCUMENT_ATTACH_LINK)).click();
    }

    private void acceptUpdate(TestGroup testGroup) {
        driver(testGroup)
                .switchTo().parentFrame()
                .switchTo().parentFrame()
                .switchTo().frame(3);

        browser(testGroup).waitElement(By.id(BUTTON_SUBMIT_ID)).click();


        driver(testGroup)
                .switchTo().parentFrame()
                .switchTo().frame(2);
        browser(testGroup).waitElement(By.id(BUTTON_SUBMIT_ID)).click();

        browser(testGroup).delay(3000);
    }

    private Bill getLastBillInfo(TestGroup testGroup, String option) {
        WebElement bill = selectLastBillAndGetSize(testGroup, option);
        List<WebElement> billProps = bill.findElements(By.cssSelector(BILL_FIELD_SELECTOR));

        return Bill.builder()
                .type(billProps.get(0).getText())
                .evidence(billProps.get(1).getText())
                .billType(billProps.get(2).getText())
                .internalNotes(billProps.get(3).getText())
                .amount(billProps.get(4).getText().replace(",", ""))
                .quantity(billProps.get(5).getText())
                .build();
    }

    private WebElement selectLastBillAndGetSize(TestGroup testGroup, String option) {
        driver(testGroup)
                .switchTo().frame(0)
                .switchTo().frame(5)
                .switchTo().frame(0);

        int size = findElementsBy(testGroup, By.cssSelector(BILLS_ID)).size();
        if(option.equals(ADD_BILL_OPTION)) billSize = size;
        if(option.equals(REMOVE_BILL_OPTION)) billSizeNew = size;

        findElementsBy(testGroup, By.cssSelector(BILLS_ID)).get(size-1).click();
        return findElementsBy(testGroup, By.cssSelector(BILLS_ID)).get(size-1);
    }
    //endregion

    //region HELPER METHODS: delete bill
    private void acceptRemove(TestGroup testGroup) {
        browser(testGroup).delay(3000);

        driver(testGroup)
                .switchTo().parentFrame()
                .switchTo().parentFrame()
                .switchTo().parentFrame();

        findElementsBy(testGroup, By.cssSelector(".ui-dialog")).get(2)
                .findElements(By.cssSelector(".dlgButton")).get(0)
                .click();

        browser(testGroup).delay(3*DELAY);
    }
    //endregion
    private WebDriver driver(TestGroup testGroup) {
        return browser(testGroup).getDriver();
    }
}