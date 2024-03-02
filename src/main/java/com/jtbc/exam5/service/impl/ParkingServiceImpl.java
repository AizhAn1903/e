package com.jtbc.exam5.service.impl;

import com.jtbc.exam5.dto.*;
import com.jtbc.exam5.entity.Parking;
import com.jtbc.exam5.entity.Users;
import com.jtbc.exam5.enums.Status;
import com.jtbc.exam5.repo.ParkingRepo;
import com.jtbc.exam5.repo.UserRepo;
import com.jtbc.exam5.service.ParkingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepo parkingRepo;
    private final UserRepo userRepo;

    @Override
    public CreateParkingDTO createParking(CreateParkingDTO createParkingDTO) {
        Parking parking = Parking.builder()
                .numberOfParking(createParkingDTO.getNumberOfParking())
                .type(createParkingDTO.getType())
                .status(createParkingDTO.getStatus())
                .build();
        try {
            parkingRepo.save(parking);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return createParkingDTO;
    }



    @Override
    public List<ParkingDTO> getAllParking() {
        List<Parking> parkings = parkingRepo.findAll();
        List<ParkingDTO> parkingDTOS = new ArrayList<>();
        for (Parking parking : parkings) {
            ParkingDTO parkingDTO = ParkingDTO.builder()
                    .id(parking.getId())
                    .numberOfParking(parking.getNumberOfParking())
                    .status(parking.getStatus())
                    .build();
            parkingDTOS.add(parkingDTO);
        }
        return parkingDTOS;
    }

    @Override
    public ParkingDTOWithId getById(Long id) {
        Parking parking = parkingRepo.findById(id).get();
        ParkingDTOWithId parkingDTO = ParkingDTOWithId.builder()
                .id(parking.getId())
                .numberOfParking(parking.getNumberOfParking())
                .status(parking.getStatus())
                .type(parking.getType())
                .build();

        return parkingDTO;
    }

    @Override
    public CreateParkingDTO updateParking(Long id, CreateParkingDTO createParkingDTO) {
        Parking parking = parkingRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Parking not found with id: " + id));
        parking.setNumberOfParking(createParkingDTO.getNumberOfParking());
        parking.setStatus(createParkingDTO.getStatus());
        parking.setType(createParkingDTO.getType());

        parking = parkingRepo.save(parking);

        CreateParkingDTO updatedParkingDTO = CreateParkingDTO.builder()
                .numberOfParking(parking.getNumberOfParking())
                .status(parking.getStatus())
                .type(parking.getType())
                .build();

        return updatedParkingDTO;
    }

    @Override
    public void deleteParking(Long id) {
        Optional<Parking> parkingOptional = parkingRepo.findById(id);
        if (parkingOptional.isPresent()) {
            parkingRepo.delete(parkingOptional.get());
        } else {
            throw new EntityNotFoundException("Parking not found with id: " + id);
        }
    }
@Override
public boolean reserveParking(String parkingNumber, Long userId) {
    Parking parkingSpot = parkingRepo.findByNumberOfParking(parkingNumber);

    if (parkingSpot == null || parkingSpot.getStatus() == Status.OCCUPIED) {
        return false;
    } else {
        Optional<Users> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            parkingSpot.setStatus(Status.OCCUPIED);
            parkingSpot.setUsers(user);
            parkingRepo.save(parkingSpot);
            return true;
        } else {
            return false;
        }
    }
    }
    @Override
    public boolean releaseParking(String parkingNumber) {
        Parking parkingSpot = parkingRepo.findByNumberOfParking(parkingNumber);

        if (parkingSpot == null || parkingSpot.getStatus() == Status.FREE) {
            return false;
        }
        parkingSpot.setStatus(Status.FREE);
        parkingSpot.setUsers(null);
        parkingRepo.save(parkingSpot);

        return true;
    }
}


