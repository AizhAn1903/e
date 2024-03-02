package com.jtbc.exam5.dto;

import com.jtbc.exam5.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingDTO {
    private Long id;
    private String numberOfParking;
    private Status status;

}
