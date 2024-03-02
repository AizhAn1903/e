package com.jtbc.exam5.service;

import com.jtbc.exam5.dto.CreateParkingDTO;
import com.jtbc.exam5.dto.ParkingDTO;
import com.jtbc.exam5.dto.ParkingDTOWithId;
import com.jtbc.exam5.dto.ParkingReservationDTO;
import com.jtbc.exam5.entity.Users;

import java.util.List;
import java.util.Optional;

public interface ParkingService {
    CreateParkingDTO createParking(CreateParkingDTO createParkingDTO);

    List<ParkingDTO> getAllParking();
    ParkingDTOWithId getById(Long Id);
    CreateParkingDTO updateParking(Long id, CreateParkingDTO createParkingDTO);
    void deleteParking(Long id);


    boolean reserveParking(String parkingNumber, Long userId);

    boolean releaseParking(String parkingNumber);
}
