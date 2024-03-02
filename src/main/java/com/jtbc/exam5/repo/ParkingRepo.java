package com.jtbc.exam5.repo;

import com.jtbc.exam5.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepo extends JpaRepository<Parking,Long> {
    Parking findByNumberOfParking(String numberOfParking);
}
