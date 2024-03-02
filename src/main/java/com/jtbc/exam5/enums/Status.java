package com.jtbc.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Status {
    OCCUPIED("Занято"),
    FREE("Свободно")
    ;
    String DESCRIPTION;

}
