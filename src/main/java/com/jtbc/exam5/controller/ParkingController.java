package com.jtbc.exam5.controller;

import com.jtbc.exam5.dto.*;
import com.jtbc.exam5.entity.Parking;
import com.jtbc.exam5.repo.ParkingRepo;
import com.jtbc.exam5.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-spots")
public class ParkingController {
    private final ParkingService parkingService;

    @PostMapping("/create")
    public ResponseEntity<CreateParkingDTO> createParking(@RequestBody CreateParkingDTO parkingDTO) {
        try {
            CreateParkingDTO createParking = parkingService.createParking(parkingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createParking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<ParkingDTOWithId> getById(@PathVariable Long id) {
        try {
            ParkingDTOWithId parkingDTO = parkingService.getById(id);
            if (parkingDTO != null)
                return new ResponseEntity<>(parkingDTO, HttpStatus.OK);
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут возвращает весь список парковачных мест ")
    @GetMapping("/getAll")
    public ResponseEntity<List<ParkingDTO>> getAll() {
        try {
            List<ParkingDTO> parking = parkingService.getAllParking();
            return new ResponseEntity<>(parking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateParkingDTO> update(@PathVariable Long id, @RequestBody CreateParkingDTO parkingDTO) {
        try {
            CreateParkingDTO parking = parkingService.updateParking(id, parkingDTO);
            return ResponseEntity.ok(parking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            parkingService.deleteParking(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity<String> reserveParkingSpot(@RequestBody ParkingReservationDTO request) {
        boolean result = parkingService.reserveParking(request.getNumberOfParking(), request.getUserId());

        if (result) {
            return ResponseEntity.ok("Parking spot reserved successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to reserve parking spot.");
        }
    }

    @PostMapping("/release-parking")
    public ResponseEntity<String> releaseParkingSpot(@RequestParam ParkingReleaseDTO request) {
        boolean result = parkingService.releaseParking(request.getNumberOfParking());

        if (result) {
            return ResponseEntity.ok("Parking spot released successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to release parking spot.");
        }
    }
}

