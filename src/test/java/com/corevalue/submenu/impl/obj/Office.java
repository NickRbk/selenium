package com.corevalue.submenu.impl.obj;

import lombok.*;

@Builder
@Getter
@EqualsAndHashCode
public class Office {
    private String name;
    private String code;
    private String address1;
    private String address2;
    private String city;
}
