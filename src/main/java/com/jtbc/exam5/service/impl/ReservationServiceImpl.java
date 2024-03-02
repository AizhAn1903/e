package com.jtbc.exam5.service.impl;

import com.jtbc.exam5.dto.ParkingReservationDTO;
import com.jtbc.exam5.entity.Users;
import com.jtbc.exam5.service.ParkingService;
import com.jtbc.exam5.service.ReservationService;
import com.jtbc.exam5.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ParkingService parkingService;
    private final UsersService usersService;


}
