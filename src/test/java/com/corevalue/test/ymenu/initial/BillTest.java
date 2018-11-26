package com.corevalue.test.ymenu.initial;

import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.IBrowser;
import com.corevalue.driver.TestGroup;
import com.corevalue.test.AbstractAfterMethod;
import com.corevalue.ymenu.YMenus;
import com.corevalue.ymenu.bill.Bill;
import com.corevalue.ymenu.bill.BillYMenu;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.corevalue.constant.menu.YMenuBillConst.*;

public class BillTest extends AbstractAfterMethod {
    private TestGroup testGroup;

    @Parameters("testGroup")
    public BillTest(String param) {
        testGroup = TestGroup.valueOf(param);
    }

    @Test
    void addBill() {
        Bill expectedBill = expectedBillAfterUpdate();
        Bill newBill = YMenus.openBillMenu(testGroup)
                .add(testGroup);

        Assert.assertEquals(newBill, expectedBill);
    }

    @Test(dependsOnMethods = "addBill")
    void removeBill() {
        BillYMenu bill = BillYMenu.INSTANCE;
        BillYMenu.INSTANCE.remove(testGroup);

        Assert.assertEquals(bill.getBillSizeNew(), bill.getBillSize()-1);
    }

    private Bill expectedBillAfterUpdate() {
        return Bill.builder()
                .type(FEE_TYPE)
                .evidence(BILL_EVIDENCE)
                .billType(BILL_TYPE)
                .internalNotes(BILL_NOTES)
                .amount(BILL_AMOUNT)
                .quantity(BILL_QUANTITY)
                .build();
    }

    protected WebDriver driver() {
        return browser().getDriver();
    }

    private IBrowser browser() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup);
    }
}
