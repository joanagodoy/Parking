package com.example.estacionamento.repository;

import com.example.estacionamento.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    public List<Parking> findByPlateOrderByCheckInDesc(String plate);

}