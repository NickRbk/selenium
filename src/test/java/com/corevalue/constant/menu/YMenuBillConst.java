package com.corevalue.constant.menu;

public interface YMenuBillConst {

    String FEE_TYPE = "Cost";
    String BILL_DEPT_TYPE = "Eviction";
    String BILL_EVIDENCE = "Pending";
    String BILL_TYPE = "General Sessions";
    String BILL_NOTES = "draft notes";
    String BILL_COMMENTS = "asap";
    String BILL_BORROWER_RCVB = "Recoverable";
    String BILL_INVESTOR_RCVB = "Reimbursable";
    String BILL_AMOUNT = "2000.00";
    String BILL_QUANTITY = "150";

    String BILL_FIELD_FEE_TYPE_SELECTOR = "[id$=LIT_TYPE_ID]";
    String BILL_FIELD_DEPT_TYPE_SELECTOR = "[id$=DEPT_ID]";
    String BILL_FIELD_TYPE_SELECTOR = "[id$=PAYEE_TYPE_ID]";
    String BILL_FIELD_AMOUNT_SELECTOR = "[id$=LI_AMOUNT]";
    String BILL_FIELD_QUANTITY_SELECTOR = "[id$=LI_QUANTITY]";
    String BILL_FIELD_NOTES_SELECTOR = "[id$=LI_NOTE]";
    String BILL_FIELD_COMMENTS_SELECTOR = "[id$=LI_INV_COMMENTS]";
    String BILL_FIELD_BORROWER_RCVB_SELECTOR = "[id$=LI_RECOVERABLE]";
    String BILL_FIELD_INVESTOR_RCVB_SELECTOR = "[id$=LI_INVESTOR_RECOVERABLE]";
    String BILL_FIELD_EVIDENCE_SELECTOR = "[id$=ALL_EVIDENCE_COLLECTED]";

    String BILL_FIELD_SELECTOR = ".cellBorderBR";
    String BILLS_ID = "#MainTable > tbody > tr";

    String EVIDENCE_TYPE_ID = "evidenceType";
    String EVIDENCE_TYPE = "Receipt";
    String DM_SELECTOR = "#select > img";
    String DM_DOCUMENT_SELECTOR = "#browser > li:nth-child(4) > ul > li > ul > li:nth-child(4)";
    String DM_DOCUMENT_ATTACH_LINK = "Attach to Bill Line Item";


    String EVIDENCE_ADD_SELECTOR = ".tbFrameToolBar#Bar tr td:nth-child(2)";

    String BILL_ADD_ID = "AddP";
    String BILL_REMOVE_ID = "DeleteP";

    String BUTTON_SUBMIT_ID = "Finish";

    String ADD_BILL_OPTION = "add";
    String REMOVE_BILL_OPTION = "delete";

    int DELAY = 3000;
}
