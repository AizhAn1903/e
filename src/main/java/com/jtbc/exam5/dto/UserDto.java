package com.jtbc.exam5.dto;

import com.jtbc.exam5.entity.Parking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String fullName;
    private String email;
    private Parking parking;

}
