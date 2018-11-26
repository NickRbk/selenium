package com.corevalue.ymenu.bill;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Bill {
    private String type;
    private String evidence;
    private String billType;
    private String internalNotes;
    private String amount;
    private String quantity;
}