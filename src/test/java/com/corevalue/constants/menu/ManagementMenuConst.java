package com.corevalue.constants.menu;

public interface ManagementMenuConst extends MenuConst {

    //region menu: MANAGEMENT MENU
    String SUBMENU_MANAGE_GROUPS_ID = SUBMENU_BASE_PREFIX + "i6g";
    String SUBMENU_MANAGE_USERS_ID = SUBMENU_BASE_PREFIX + "i70";
    String SUBMENU_MANAGE_CLIENTS_ID = SUBMENU_BASE_PREFIX + "i61";
    String SUBMENU_MANAGE_OFFICES_ID =SUBMENU_BASE_PREFIX + "i62";
    String SUBMENU_MANAGE_BOILER_PLATES_ID = SUBMENU_BASE_PREFIX + "i63";
    String SUBMENU_MANAGE_ALL_OFFICE_CONTACTS_ID = SUBMENU_BASE_PREFIX + "i64";
    String SUBMENU_MANAGE_CONTACTS_FOR_ACTION_ID = SUBMENU_BASE_PREFIX + "i65";
    String SUBMENU_MANAGE_COURTS_ID = SUBMENU_BASE_PREFIX + "i66";
    String SUBMENU_MANAGE_WIZARDS_ID = SUBMENU_BASE_PREFIX + "i67";
    String SUBMENU_MANAGE_TICKLERS_DESCRIPTIONS_ID = SUBMENU_BASE_PREFIX + "i68";
    String SUBMENU_MANAGE_MISCEALLANEOUS_COST_DESCRIPTIONS_ID = SUBMENU_BASE_PREFIX + "i69";
    String SUBMENU_MANAGE_WORKING_DAYS_ID = SUBMENU_BASE_PREFIX + "i610";
    //endregion

    //region submenu: MANAGE GROUPS
    int GROUPS_MIN_COUNT = 5;

    String GROUPS_SELECTOR = ".cellBorderBR > nobr";

    String GROUP_ADD_BUTTON_ID = "AddGroup";
    String GROUP_UPDATE_BUTTON_ID = "EditGroup";
    String GROUP_REMOVE_BUTTON_ID = "DeleteGroup";

    String GROUP_NAME_FIELD_ID = "GroupNameId";
    String GROUP_DESCRIPTION_FIELD_ID = "GroupDescriptionId";
    String GROUP_SUBMIT = "ProcessGroupBtn";

    String GROUP_NAME = "test101";
    String GROUP_NAME_UPDATED = "test102";
    String GROUP_DESCRIPTION = "description";
    String GROUP_DESCRIPTION_UPDATED = "description new";
    //endregion

    //region submenu: USERS
    String USERS_SUBMIT = "ProcessUserBtn";

    String USERS_SECTION_ROLES_ID = "3";
    String USERS_SECTION_GROUPS_ID = "4";
    String USERS_SELECTED_ROLE_ID = "role_19";
    String USERS_SELECTED_GROUP_ID = "group_4";

    String USERS_LOGIN_FIELD_ID = "user_login";
    String USERS_NAME_FIELD_ID = "UserName";

    String USER_NAME_INITIAL = "test";
    String USER_NAME_UPDATED = "test name updated";
    //endregion

    //region submenu: CLIENTS
    String CLIENTS_CODE_FIELD_ID = "code";

    String CLIENTS_SUBMIT = "Finish";

    String CLIENT_CODE_FIELD_ID = "[id$=CLIENT_CODE]";
    String CLIENT_NAME_FIELD_ID = "[id$=CLIENT_NAME]";
    String CLIENT_LENDER_SYSTEM_FIELD_SELECTOR = "[id$=CLIENT_LENDER_SYSTEM]";
    int LENDER_SYSTEM_OTHER_INDEX = 10;

    String CLIENT_CODE_SEARCH = "test";
    String CLIENT_CODE_INITIAL = "TESTCODE123_INITIAL";
    String CLIENT_CODE_UPDATED = "TESTCODE123_UPDATED";
    String CLIENT_NAME_INITIAL = "test name initial";
    String CLIENT_NAME_UPDATED = "test name updated";
    //endregion

    //region submenu: OFFICES
    String OFFICE_CODE_FIELD_ID = "[id$=OFFICE_CODE]";
    String OFFICE_NAME_FIELD_ID = "[id$=OFFICE_NAME]";
    String OFFICE_REASON_FIELD_ID = "Reason";
    String OFFICE_ADDR1_FIELD_ID = "line1";
    String OFFICE_ADDR2_FIELD_ID = "line2";
    String OFFICE_CITY_FIELD_ID = "city";
    String ACCEPT_ADDRESS_BUTTON_ID = "Finish";

    String OFFICE_NAME_UPDATED = "name updated";
    String OFFICE_CODE_UPDATED = "code updated";
    String OFFICE_REASON_ADDRESS_UPDATED = "update address";
    String OFFICE_ADDR1_UPDATED = "address updated 1";
    String OFFICE_ADDR2_UPDATED = "address updated 2";
    String OFFICE_CITY_UPDATED = "NYC";
    String UPDATE_ADDRESS_BUTTON_ID = "EditAddress";

    String OFFICE_COVERED_STATES_ID = "3";
    String OFFICE_COVERED_STATES_ADD_STATE_ID = "AddState";
    String OFFICE_COVERED_STATES_EDIT_STATE_ID = "EditState";
    String OFFICE_COVERED_STATES_REMOVE_STATE_ID = "DeleteState";

    String OFFICE_COVERED_STATES_SELECT_ID = "cstate";
    String OFFICE_COVERED_STATES_NAME = "District of Columbia";
    String OFFICE_COVERED_STATES_NAME_UPDATED = "Colorado";

    String OFFICE_PHONES_ID = "4";
    String OFFICE_PHONE_ADD_ID = "AddPhone";
    String OFFICE_PHONE_EDIT_ID = "EditPhone";
    String OFFICE_PHONE_REMOVE_ID = "DeletePhone";
    String OFFICE_PHONE_TYPE_SELECT_ID = "type";
    String OFFICE_PHONE_FIELD_ID = "number";
    String OFFICE_PHONE_TYPE_NAME = "E-mail";
    String OFFICE_PHONE_NAME = "test2@test.com";
    String OFFICE_PHONE_NAME_UPDATED = "test3@test.com";

    String OFFICE_NAME_SELECTOR = ".cellBorderBR:nth-child(1)";
    String OFFICE_CODE_SELECTOR = ".cellBorderBR:nth-child(2)";
    String OFFICE_ADDR1_SELECTOR = ".cellBorderBR:nth-child(3)";
    String OFFICE_ADDR2_SELECTOR = ".cellBorderBR:nth-child(4)";
    String OFFICE_CITY_SELECTOR = ".cellBorderBR:nth-child(5)";

    String OFFICE_COVERED_STATE_NAME_SELECTOR = ".cellBorderB > p";
    //endregion

    int WAIT_TIMEOUT = 5000;
    int WAIT_TIMEOUT_2 = 7000;

    String OFFICE_REASON_UPDATED = "update";
    String OFFICE_REASON_REMOVE = "remove";

    String OK_BUTTON_ID = "Ok";
    String FINISH_BUTTON_ID = "Finish";
    String SEARCH_BUTTON_ID = "Search";
    String UPDATE_BUTTON_ID = "Edit";
    String DIALOG_CANCEL_ID = "Cancel";
    String DIALOG_CLOSE_ID = "Close";
    String LIST_SELECTOR = ".cellBorderBR:nth-child(1)";
}
