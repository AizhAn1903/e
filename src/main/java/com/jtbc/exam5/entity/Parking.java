package com.jtbc.exam5.entity;

import com.jtbc.exam5.enums.Status;
import com.jtbc.exam5.enums.TypeOfParkingPlace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberOfParking;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Enumerated(value = EnumType.STRING)
    private TypeOfParkingPlace type;
    @ManyToOne

    private Users users;



}
