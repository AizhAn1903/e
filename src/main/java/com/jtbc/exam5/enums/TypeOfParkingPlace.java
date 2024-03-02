package com.jtbc.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TypeOfParkingPlace {
    STANDARD("Стандарт"),
    FOR_THE_DISABLED("Для инвалидов"),
    FOR_FAMILIES_WITH_CHILDREN("Для семей с детьми"),
    AVAILABLE_FOR_ELECTRIC_VEHICLES("Доступно для электромобилей ")
    ;
    String DESCRIPTION;
}
